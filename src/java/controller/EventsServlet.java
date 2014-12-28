/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Car;
import entity.Event;
import entity.User;
import entity.UserHasEvent;
import entity.UserHasEventPK;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CarFacade;
import session.EventFacade;
import session.UserFacade;
import validate.LoginValidator;

/**
 *
 * @author tomasharkema
 */
@WebServlet(name = "EventsServlet", loadOnStartup = 1, urlPatterns = {"/events", "/events/attend"})
public class EventsServlet extends HttpServlet {
    @EJB
    private EventFacade eventFacade;
    @EJB
    private UserFacade userFacade;
    @EJB
    private CarFacade carFacade;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        switch (userPath) {
            case "/events":{
                serveEvents(request, response);
                break;
            }
            case "/events/attend":{
                attendEvent(request, response);
            }
        }
    }

    private void serveEvents(HttpServletRequest request, HttpServletResponse response) {
        User user = LoginValidator.getInstance().validateUser(request, response, userFacade);
        String eventId = request.getParameter("eventId");
        int eventIdInt;
        if (eventId == null) {
            eventIdInt = -1;
        } else {
            eventIdInt = Integer.parseInt(eventId);
        }

        String url;
        if (eventIdInt == -1) {
            // Has no event
            url = "/WEB-INF/view/events.jsp";
            request.setAttribute("events", eventFacade.findAll());
        } else {
            // Has event
            url = "/WEB-INF/view/event.jsp";
            request.setAttribute("event", eventFacade.find(eventIdInt));
            
            System.out.println("isAttending");
            request.setAttribute("isAttending", user.isAttendingEvent(eventIdInt) != null);
            
        }

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void attendEvent(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String eventId = request.getParameter("eventId");
        String type = request.getParameter("type");
        Event event = eventFacade.find(Integer.parseInt(eventId));
        User user = LoginValidator.getInstance().validateUser(request, response, userFacade);
        
        Car car = null;
        switch (type) {
            case "ikrijzelf":{
                List<Car> carList = user.getCarList();
                System.out.println(carList);
                car = carList.get(0);
                break;
            }
            case "meerijden":{
                int carid = Integer.parseInt(request.getParameter("carid"));
                Car carFind = carFacade.find(carid);
                int places = car.getPlaces();
                if (places > 0) {
                    car = carFind;
                }
            }
        }
        
        if (type.equals("cancel")) {
            UserHasEvent ev = user.isAttendingEvent(Integer.parseInt(eventId));
            List<UserHasEvent> evList = user.getUserHasEventList();
            evList.remove(ev);
            user.setUserHasEventList(evList);
            
            userFacade.edit(user);
        } else {
            List<UserHasEvent> evList = user.getUserHasEventList();
            UserHasEvent chain = new UserHasEvent(user.getIduser(), event.getIdevent());
            chain.setCarId(car);
            chain.setDate(new Date());
            evList.add(chain);
            user.setUserHasEventList(evList);

            userFacade.edit(user);
        }
        response.sendRedirect("/events?eventId=" + eventId);
    }
}

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
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        System.out.println(userPath);
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
        HttpSession session = request.getSession();
        String eventId = request.getParameter("eventId");
        int eventIdInt;
        if (eventId == null) {
            eventIdInt = -1;
        } else {
            eventIdInt = Integer.parseInt(eventId);
        }

        String url;
        if (eventIdInt == -1) {
            url = "/WEB-INF/view/events.jsp";
            request.setAttribute("events", eventFacade.findAll());
        } else {
            url = "/WEB-INF/view/event.jsp";
            request.setAttribute("event", eventFacade.find(eventIdInt));
        }

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void attendEvent(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        String eventId = request.getParameter("eventId");
        String type = request.getParameter("type");
        User loggedinUser = (User)session.getAttribute("loggedinuser");
        int uid = loggedinUser.getIduser();
        
        Event event = eventFacade.find(Integer.parseInt(eventId));
        User user = userFacade.find(uid);
        
        Car car = null;
        if (type.equals("ikrijzelf")) {
            List<Car> carList = user.getCarList();
            System.out.println(carList);
            car = carList.get(0);
        }
        
        List<UserHasEvent> evList = user.getUserHasEventList();
        UserHasEvent chain = new UserHasEvent(uid, event.getIdevent());
        chain.setCarId(car);
        evList.add(chain);
        user.setUserHasEventList(evList);
        
        userFacade.updateUser(user);
        
        response.sendRedirect("/events");
    }
}

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
import java.lang.reflect.Array;
import java.util.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.markdown4j.Markdown4jProcessor;
import session.CarFacade;
import session.EventFacade;
import session.UserFacade;
import validate.LoginValidator;

/**
 *
 * @author tomasharkema
 */
@WebServlet(name = "EventsServlet", loadOnStartup = 1, urlPatterns = {"/events", "/events/attend", "/events/availableCars"})
public class EventsServlet extends HttpServlet {
    @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;
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
                break;
            }
            case "/events/availableCars":{
                availableCars(request, response);
                break;
            }
        }
    }

    private void serveEvents(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User)request.getAttribute("currentUser");
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
            request.setAttribute("events", eventFacade.findAllDescAfterDate());
        } else {
            // Has event
            url = "/WEB-INF/view/event.jsp";
            Event event = eventFacade.find(eventIdInt);
            request.setAttribute("event", event);
            request.setAttribute("attendees", event.getAttendees());
            request.setAttribute("userHasEventList", event.getUserHasEventList());
            request.setAttribute("drivers", event.getAttendedCars());
            String html = new Markdown4jProcessor().process(event.getDescription());
            request.setAttribute("markdownDescription", html);

            if (user != null) {
                // User is not loggedin. Don't let him join.
                request.setAttribute("isAttending", user.isAttendingEvent(eventIdInt) != null);
                request.setAttribute("attendingFriends", event.getAttendingFriends(user));
            }
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
        User user = (User)request.getAttribute("currentUser");
        
        Car car = null;
        switch (type) {
            case "ikrijzelf":{
                List<Car> carList = user.getCarList();
                System.out.println(carList);
                car = carList.get(0);
                break;
            }
            case "meerijden":{
                Integer carid = Integer.parseInt(request.getParameter("carId"));
                Car carFind = carFacade.find(carid);
                int places = carFind.getPlaces();
                if (places > 0) {
                    car = carFind;
                }
                break;
            }
        }
        
        if (type.equals("cancel")) {
            final UserHasEvent ev = user.isAttendingEvent(Integer.parseInt(eventId));
            List<UserHasEvent> evList = user.getUserHasEventList();
            evList.remove(ev);
            user.setUserHasEventList(evList);

            if (ev.getUser().getCarList().contains(ev.getCarId())) {
                List<UserHasEvent> list = ev.getEvent().getUserHasEventList();

                CollectionUtils.filter(list, new Predicate<UserHasEvent>() {
                    @Override
                    public boolean evaluate(UserHasEvent userHasEvent) {
                        return !userHasEvent.getCarId().equals(ev.getCarId());
                    }
                });

                ev.getEvent().setUserHasEventList(list);
                eventFacade.edit(ev.getEvent());
            } else {
                userFacade.edit(user);
            }
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

    // TODO: this call doesn't need to be cached.
    private void availableCars(HttpServletRequest request, HttpServletResponse response) throws IOException{
        JSONObject result = new JSONObject();
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String eventId = request.getParameter("eventId");
        Event event = eventFacade.find(Integer.parseInt(eventId));
        ArrayList<Car> carList = event.getAttendedCars();
        JSONArray carArray = new JSONArray();

        for (Car car : carList) {
            Car refreshedCar = carFacade.find(car.getRegistration());
            JSONObject obj = new JSONObject();
            obj.put("id", refreshedCar.getRegistration());
            obj.put("uid", refreshedCar.getUserIduser().getIduser());
            obj.put("desc", refreshedCar.getBrand() + " " + refreshedCar.getType() + " " + refreshedCar.getColor());
            obj.put("places", refreshedCar.getPlaces());
            carArray.add(obj);
        }

        result.put("cars", carArray);

        try {
            /* TODO output your page here. You may use following sample code. */
            out.println(result.toJSONString());
        } finally {
            out.close();
        }
    }
}

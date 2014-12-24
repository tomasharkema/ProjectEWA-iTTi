/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import entity.Event;
import entity.Location;
import entity.LocationHasEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.EventFacade;
import session.LocationFacade;
import session.LocationHasEventFacade;

/**
 *
 * @author sanderlooijenga
 */
@WebServlet(name = "ControllerServlet",
            loadOnStartup = 1,
            urlPatterns = {"/admin/events",
                           "/admin/events/add",
                           "/admin/events/edit",
                           "/admin/events/delete"})
public class EventServlet extends HttpServlet {

    @EJB
    private EventFacade eventFacade;
    
    @EJB
    private LocationFacade locationFacade;
    
    @EJB
    private LocationHasEventFacade locationHasEventFacade;

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String userPath = request.getServletPath();

        // if admin page is requested
        if (userPath.equals("/admin/events")) {
            userPath = "/events/index";

            request.setAttribute("events", eventFacade.findAll());

        // if cart page is requested
        } else if (userPath.equals("/admin/events/add")) {
            userPath = "/events/add";
            
            request.setAttribute("locations", locationFacade.findAll());
        //
        } else if (userPath.equals("/admin/events/edit")) {
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
//                userPath = "/events/index";

                response.sendRedirect("/admin/events");
            } else {
                Event event = eventFacade.find(Integer.parseInt(id));
                if (event == null) {
//                    userPath = "/events/index";

                    response.sendRedirect("/admin/events");
                } else {
                    userPath = "/events/edit";

                    request.setAttribute("event", event);
                    
                    request.setAttribute("locations", locationFacade.findAll());
                }
            }
        } else if (userPath.equals("/admin/events/delete")) {
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
            } else {
                Event event = eventFacade.find(Integer.parseInt(id));
                eventFacade.remove(event);
                response.sendRedirect("http://localhost:8080/admin/events");
            }
        }

        // use RequestDispatcher to forward request internally
        String url = "/admin/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String userPath = request.getServletPath();

        // if addToCart action is called
        if (userPath.equals("/admin/events/add")) {
            // TODO: Implement add product to cart action
            System.out.println("req: " + request.getParameter("eventName"));
            createEvent(request);

//            userPath = "/events/index";

            response.sendRedirect("/admin/events");
        // if updateCart action is called
        } else if (userPath.equals("/admin/events/edit")) {
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
//                userPath = "/events/index";

                response.sendRedirect("/admin/events");
            } else {
                Event event = eventFacade.find(Integer.parseInt(id));
                if (event == null) {
                    userPath = "/events/edit?id=" + id;
                } else {
                    updateEvent(request, event);

//                    userPath = "/events/index";
                    response.sendRedirect("/admin/events");
                }
            }
        }

        // use RequestDispatcher to forward request internally
        String url = "/admin/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * TODO: check if attributes differ before setting them?
     *
     * @param request
     * @param event
     * @return
     */
    private Event updateEvent (HttpServletRequest request, Event event) {
        String name = request.getParameter("eventName");
        String description = request.getParameter("description");
        String eventLogo = request.getParameter("eventLogo");
//        String location = request.getParameter("eventLocation");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = formatter.parse(request.getParameter("eventDate"));
        } catch (ParseException ex) {
            Logger.getLogger(EventServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        event.setEventName(name);
        event.setEventDate(date);
        event.setDescription(description);
        // TODO: Improve the Logo edit functionality.
        event.setEventLogo(eventLogo);
        eventFacade.edit(event);

        return event;
    }

    /**
     * begin date - date
     * end date - date
     * description - text
     * active (/visible) - tinyint: 0 || 1
     * @param request
     * @return
     */
    private Event createEvent (HttpServletRequest request) {
        String name = request.getParameter("eventName"),
                description = request.getParameter("description"),
                eventLogo = request.getParameter("eventLogo"),
                locationId = request.getParameter("location");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = formatter.parse(request.getParameter("eventDate"));
        } catch (ParseException ex) {
            Logger.getLogger(EventServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        Event event = new Event();
        event.setEventName(name);
        event.setEventDate(date);
        // TODO: Improve the Logo edit functionality.
        event.setDescription(description);
        event.setEventLogo(eventLogo);

        eventFacade.create(event);
        
        Location location = locationFacade.find(Integer.parseInt(locationId));
        
        LocationHasEvent locationHasEvent = new LocationHasEvent();
        locationHasEvent.setLocation(location);
        locationHasEvent.setEvent(event);
        locationHasEvent.setEventDate(date);
        
        locationHasEventFacade.create(locationHasEvent);

        return event;
    }

}
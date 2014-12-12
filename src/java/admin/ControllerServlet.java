/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import entity.Event;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.EventFacade;

/**
 *
 * @author sanderlooijenga
 */
@WebServlet(name = "ControllerServlet",
            loadOnStartup = 1,
            urlPatterns = {"/admin/events",
                           "/admin/events/add",
                           "/admin/events/edit"})
public class ControllerServlet extends HttpServlet {

    @EJB
    private EventFacade eventFacade;

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

        System.out.println("admin controller, path: " + userPath);

        // if admin page is requested
        System.out.println(Arrays.toString(userPath.split("/")));
        if (userPath.equals("/admin/events")) {
            // TODO: Implement category request
            userPath = "/events/index";
        // if cart page is requested
        } else if (userPath.equals("/admin/events/add")) {
            userPath = "/events/add";
        } else if (userPath.equals("/admin/events/edit")) {
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                userPath = "/events/index";
            } else {
                Event event = eventFacade.find(Integer.parseInt(id));
                if (event == null) {
                    userPath = "/events/index";
                } else {
                    userPath = "/events/edit";
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
            userPath = "/events/index";
        // if updateCart action is called
        }

        // use RequestDispatcher to forward request internally
        String url = "/admin/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Event createEvent (HttpServletRequest request) {
        String name = request.getParameter("eventName");
        String location = request.getParameter("eventLocation");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;

        try {
            date = formatter.parse(request.getParameter("eventDate"));
        } catch (ParseException ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        Event event = new Event();
        event.setEventName(name);
        event.setEventLocation(location);
        event.setEvenDate(date);
//        event.setEventLogo(eventLogo);

        eventFacade.create(event);

        return event;
    }

}
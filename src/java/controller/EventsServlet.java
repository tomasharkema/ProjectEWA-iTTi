/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.EventFacade;

/**
 *
 * @author tomasharkema
 */
@WebServlet(name = "EventsServlet", loadOnStartup = 1, urlPatterns = {"/events"})
public class EventsServlet extends HttpServlet {
    @EJB
    private EventFacade eventFacade;

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
        
        HttpSession session = request.getSession();
        String eventId = (String)request.getParameter("eventId");
        String url;
        if (eventId == null) {
            url = "/WEB-INF/view/events.jsp";
            request.setAttribute("events", eventFacade.findAll());
        } else {
            url = "/WEB-INF/view/event.jsp";
            User findUser = new User(Integer.parseInt(eventId));
            request.setAttribute("event", eventFacade.find(eventId));
        }
        
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

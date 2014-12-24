/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import entity.Location;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.LocationFacade;

/**
 *
 * @author sanderlooijenga
 */
@WebServlet(name = "LocationServlet",
        loadOnStartup = 1,
        urlPatterns = {"/admin/locations",
            "/admin/locations/add",
            "/admin/locations/edit",
            "/admin/locations/delete"})
public class LocationServlet extends HttpServlet {
    
    @EJB
    private LocationFacade locationFacade;

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

        // if category page is requested
        if (userPath.equals("/admin/locations")) {
            userPath = "/locations/index";

            request.setAttribute("locations", locationFacade.findAll());

        // if cart page is requested
        } else if (userPath.equals("/admin/locations/add")) {
            
            userPath = "/locations/add";

        // if checkout page is requested
        } else if (userPath.equals("/admin/locations/edit")) {
            String id = request.getParameter("id");
            
            if (id == null || id.isEmpty()) {
                response.sendRedirect("/admin/locations");
            } else {
                Location location = locationFacade.find(Integer.parseInt(id));
                if (location == null) {
                    response.sendRedirect("/admin/loctions");
                } else {
                    userPath = "/locations/edit";

                    request.setAttribute("location", location);
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
        
        if (userPath.equals("/admin/locations/add")) {
            
            createLocation(request);

            response.sendRedirect("/admin/locations");
            
        } else if (userPath.equals("/admin/locations/edit")) {
            
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                response.sendRedirect("/admin/locations");
            } else {
                Location location = locationFacade.find(Integer.parseInt(id));
                if (location == null) {
                    userPath = "/events/edit?id=" + id;
                } else {
                    updateLocation(request, location);
                    
                    response.sendRedirect("/admin/locations");
                }
            }
            
        }

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private Location createLocation (HttpServletRequest request) {
        String name = request.getParameter("locationname"),
                address = request.getParameter("address"),
                city = request.getParameter("city"),
                picture = request.getParameter("locationpicture");
        
        Location location = new Location();
        location.setLocationname(name);
        location.setAddress(address);
        location.setCity(city);
        location.setLocationpicture(picture);
        
        locationFacade.create(location);
        
        return location;
    }
    
    private Location updateLocation (HttpServletRequest request, Location location) {
        String name = request.getParameter("locationname"),
                address = request.getParameter("address"),
                city = request.getParameter("city"),
                picture = request.getParameter("locationpicture");
        
        location.setLocationname(name);
        location.setAddress(address);
        location.setCity(city);
        location.setLocationpicture(picture);
        
        locationFacade.edit(location);
        
        return location;
    }

}

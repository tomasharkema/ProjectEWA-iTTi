/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.*;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.in;

import java.util.*;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import searching.Search;
import searching.TimeLineNode;
import searching.dateComparetor;
import session.CarFacade;
import session.UserFacade;
import validate.LoginValidator;

/**
 *
 * @author tomasharkema
 */
@WebServlet(name = "OverviewServlet", loadOnStartup = 1, urlPatterns = {"/overview", "/overview/friends", "/overview/profile", "/overview/changeUser", "/overview/updateCar", "/overview/addCar", "/overview/events"})
public class OverviewServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;
    @EJB
    private Search search;
    @EJB
    private CarFacade carFacade;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        String url = "/WEB-INF/view" + userPath + ".jsp";
        //make users available in the overview.jsp
        request.setAttribute("users", userFacade.findAll());
        
        switch(userPath) {
            case "/overview":{
                handleIndex(request, response);
                break;
            }
            case "/overview/friends":{
                handleFriends(request, response);
                break;
            }
            case "/overview/profile":{
                handleProfile(request, response);
                break;
            }
            case "/overview/events":{
                handleEvents(request, response);
                break;
            }
        }
        
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void handleIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User currentUser = (User) request.getAttribute("currentUser");
        List<TimeLineNode> timeline = search.getTimelineForUser(currentUser);
        
        request.setAttribute("timeline", timeline);
    }
    
    private void handleFriends(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User currentUser = (User)request.getAttribute("currentUser");
        List<Friend> friends = currentUser.getFriends();
        
        request.setAttribute("hasNoFriends", friends.isEmpty());
        request.setAttribute("friends", friends);
    }

    private void handleProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User currentUser = (User)request.getAttribute("currentUser");
    }

    private void handleEvents(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User currentUser = (User)request.getAttribute("currentUser");
        List<UserHasEvent> eventList = currentUser.getUserHasEventList();
        Collections.sort(eventList, new Comparator<UserHasEvent>() {
            @Override
            public int compare(UserHasEvent o1, UserHasEvent o2) {
                return o1.getDate().before(new Date()) ? ((o1.getDate().before(o2.getDate()) ? 1 : -1)) : -1;
            }
        });

        request.setAttribute("events", currentUser.getUserHasEventList());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        switch(userPath) {
            case "/overview/changeUser": {
                handleChangeUser(request, response);
                break;
            }
            case "/overview/updateCar": {
                handleChangeCar(request, response);
                break;
            }
            case "/overview/addCar": {
                handleAddCar(request, response);
                break;
            }
        }
        response.sendRedirect("/overview/profile");
    }

    private void handleChangeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        User currentUser = (User)request.getAttribute("currentUser");
        System.out.println(currentUser);
        currentUser.setName(name);
        currentUser.setEmail(email);

        userFacade.edit(currentUser);
    }

    private void handleChangeCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer cid = Integer.parseInt(request.getParameter("cid"));
        String brand = request.getParameter("brand");
        String type = request.getParameter("type");
        Integer seats = Integer.parseInt(request.getParameter("seats"));
        String color = request.getParameter("color");

        Car car = carFacade.find(cid);

        car.setBrand(brand);
        car.setType(type);
        car.setNumberSeats(seats);
        car.setColor(color);

        carFacade.edit(car);
    }

    private void handleAddCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User currentUser = (User)request.getAttribute("currentUser");
        String brand = request.getParameter("brand");
        String type = request.getParameter("type");
        Integer seats = Integer.parseInt(request.getParameter("seats"));
        String color = request.getParameter("color");

        Car car = new Car(null, brand, color, type, seats);
        car.setUserIduser(currentUser);

        carFacade.save(car);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

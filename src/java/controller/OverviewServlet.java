/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Friend;
import entity.Friends;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import searching.Search;
import searching.TimeLineNode;
import session.UserFacade;
import validate.LoginValidator;

/**
 *
 * @author tomasharkema
 */
@WebServlet(name = "OverviewServlet", loadOnStartup = 1, urlPatterns = {"/overview", "/overview/friends"})
public class OverviewServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;
    @EJB
    private Search search;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        System.out.println(userPath);
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
        }
        
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void handleIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User currentUser = (User)request.getAttribute("currentUser");
        List<TimeLineNode> timeline = search.getTimelineForUser(currentUser);
        
        request.setAttribute("timeline", timeline);
    }
    
    private void handleFriends(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User currentUser = (User)request.getAttribute("currentUser");
        List<Friend> friends = currentUser.getFriendsApproved();
        
        request.setAttribute("hasNoFriends", friends.isEmpty());
        request.setAttribute("friends", friends);
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
        //processRequest(request, response);
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

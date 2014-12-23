/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Friends;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.UserFacade;

/**
 *
 * @author tomasharkema
 */
@WebServlet(name = "OverviewServlet", loadOnStartup = 1, urlPatterns = {"/overview", "/overview/vrienden"})
public class OverviewServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private UserFacade userFacade;
    
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
            case "/overview/vrienden":{
                handleVrienden(request, response);
                break;
            }
        }
        
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void handleIndex(HttpServletRequest request, HttpServletResponse response) {
        ArrayList timeline = new ArrayList();
        
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("title", "Sander is vrienden geworden met z\'n moeder!");
        item.put("time", "10 min geleden");
        item.put("image", "https://scontent-a-ams.xx.fbcdn.net/hphotos-xpa1/v/t1.0-9/1524879_10201996748137841_382722711_n.jpg?oh=7d47ccb4bff22180c9bfdd8d3df7b1c0&oe=54FD6CFB");
        
        timeline.add(item);
        
        request.setAttribute("timeline", timeline);
    }
    
    private void handleVrienden(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User currentUser = userFacade.find(((User)session.getAttribute("loggedinuser")).getIduser());
        //System.out.println(currentUser.getFriendsCollection());
        //Set friends = currentUser.getFriends();
        
        //request.setAttribute("hasNoFriends", friends.isEmpty());
        //request.setAttribute("friends", friends);
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.User;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;
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
@WebServlet(name = "LoginServlet",
        loadOnStartup = 1,
        urlPatterns = {
            "/login",
            "/logout"
        })
public class LoginServlet extends HttpServlet {
    
    @EJB
    private UserFacade userFacade;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {
//            out.close();
//        }
//    }
    
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
        switch(userPath) {
            case "/logout":{
                handleLogout(request, response);
                break;
            }
        }
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
        request.setCharacterEncoding("UTF-8");
        System.out.println(request.getParameterMap().toString());
        
        String userPath = request.getServletPath();
        System.out.println(userPath);
        switch(userPath) {
            case "/login":{
                handleLogin(request, response);
                break;
            }
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        User loggedinUser = (User)session.getAttribute("loggedinuser");
        JSONObject result = new JSONObject();
        
        if (loggedinUser == null) {
            loggedinUser = userFacade.findByFbid(new BigInteger(request.getParameter("id")));
            
            if (loggedinUser == null) {
                loggedinUser = createNewUser(request);
                result.put("created_new_user", true);
            } else {
                result.put("created_new_user", false);
            }
        } else {
            result.put("created_new_user", false);
        }
        
        result.put("loggedin", true);
        session.setAttribute("loggedinuser", loggedinUser);
        session.setAttribute("isloggedin", 1);
            
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println(result.toJSONString());
        } finally {
            out.close();
        }
    }
    
    private User createNewUser(HttpServletRequest request) {
        BigInteger fbid = new BigInteger(request.getParameter("id"));
        String name = (String)request.getParameter("name");
        String email = (String)request.getParameter("email");
        String gender = (String)request.getParameter("gender");
        String town = (String)request.getParameter("location[name]");
        String avatar = (String)request.getParameter("avatar");
        
        User user = new User();
        user.setFbid(fbid);
        user.setName(name);
        user.setEmail(email);
        user.setGender(gender.equals("male") ? "m" : "f");
        user.setTown(town);
        user.setUserAvatar(avatar);
        userFacade.addUser(user);
        return user;
    }
    
    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {   
        HttpSession session = request.getSession();
        session.setAttribute("loggedinuser", null);
        session.setAttribute("isloggedin", 0);
        System.out.println("LOGOUT");
        response.sendRedirect("/Dryves/");
    }
}

package controller;

import entity.User;
import searching.Search;
import session.UserFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomas on 04-01-15.
 */
@WebServlet(name = "UserServlet", loadOnStartup = 1, urlPatterns = {"/user", "/user/friend"})
public class UserServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;
    @EJB
    private Search search;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userPath = request.getServletPath();
        System.out.println(userPath);
        String url = "/WEB-INF/view" + userPath + ".jsp";

        switch (userPath) {
            case "/user":{
                handleUser(request, response);
                break;
            }
            case "/user/friend":{
                handleFriend(request, response);
                response.sendRedirect("/user?userId=" + request.getParameter("uid"));
                break;
            }
        }

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleUser(HttpServletRequest request, HttpServletResponse response) {
        User currentUser = (User)request.getAttribute("currentUser");
        Integer uid = Integer.parseInt(request.getParameter("userId"));
        User user = userFacade.find(uid);
        request.setAttribute("user", user);

        request.setAttribute("friendRelation", currentUser.getRelation(user));
        request.setAttribute("userTimeline", search.getTimelineForFriend(user));
        request.setAttribute("events", user.getUserHasEventList());
    }

    private void handleFriend(HttpServletRequest request, HttpServletResponse response) {
        User currentUser = (User)request.getAttribute("currentUser");
        Integer uid = Integer.parseInt(request.getParameter("uid"));
        User user = userFacade.find(uid);

        String action = request.getParameter("action");
        switch (action) {
            case "add":{
                userFacade.requestFriend(currentUser, user);
                break;
            }
            case "cancel":{
                userFacade.cancelFriendship(currentUser, user);
                break;
            }
            case "approve":{
                userFacade.approveFriendship(currentUser, user);
            }
        }
    }
}

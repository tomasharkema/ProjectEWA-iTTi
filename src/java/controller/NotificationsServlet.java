package controller;

import entity.User;
import searching.Notifications;
import searching.Notification;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by tomas on 10-01-15.
 */
@WebServlet(name = "NotificationsServlet", urlPatterns = {"/notifications"})
public class NotificationsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getAttribute("currentUser");
        List<Notification> list = Notifications.getNotifications(user);

        PrintWriter out = response.getWriter();

        try {
            out.println(list);
        } finally {
            out.close();
        }
    }
}

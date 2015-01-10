package controller;

import entity.User;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import searching.NotificationList;
import searching.Notifications;
import searching.Notification;
import utils.ListUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
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
        NotificationList list = Notifications.getNotifications(user);

        PrintWriter out = response.getWriter();

        try {
            out.println(list.getArray().toJSONString());
        } finally {
            out.close();
        }
    }
}

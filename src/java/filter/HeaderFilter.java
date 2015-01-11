package filter;

import entity.User;
import searching.Notification;
import searching.Notifications;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by tomas on 11-01-15.
 */
@WebFilter(filterName = "HeaderFilter")
public class HeaderFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        User user = (User)req.getAttribute("currentUser");

        if (user != null) {
            request.setAttribute("notifications", Notifications.getNotifications(user).getList());
        }
        chain.doFilter(request, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

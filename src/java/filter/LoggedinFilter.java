package filter;

import entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.URIParameter;

/**
 * Created by tomas on 10-01-15.
 * This filter prevents users to access pages that are only for users.
 */
public class LoggedinFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        User user = (User)req.getAttribute("currentUser");
        System.out.println("LoggedinFilter: " + user);
        if (user == null) {
            try {
                // TODO: try preserve the session by re-logging-in.
                res.sendRedirect("/login.jsp?ret=" + ((HttpServletRequest) request).getRequestURL());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return;
        }
        chain.doFilter(req, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

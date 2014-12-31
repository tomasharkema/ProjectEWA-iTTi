package validate;

import entity.User;
import session.UserFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by tomas on 27-12-14.
 */
public class LoginValidator {

    static public void loginUser(HttpServletRequest req, User user) {
        HttpSession session = req.getSession();
        session.setAttribute("userId", user.getIduser());
    }

    static public User validateUser(HttpServletRequest req, UserFacade userFacade) {
        HttpSession session = req.getSession();
        Integer uid = (Integer)session.getAttribute("userId");
        User user = null;
        if (uid != null) {
            user = userFacade.find(uid);
        }

        req.setAttribute("currentUser", user);
        req.setAttribute("isLoggedin", user != null);
        return user;
    }

    static public User validateUser(HttpServletRequest req, HttpServletResponse res, UserFacade userFacade, boolean redirectIfNotLoggedin) throws IOException {
        User u = validateUser(req, userFacade);
        if (u == null && redirectIfNotLoggedin) {
            res.sendRedirect("/");
        }
        return u;
    }

    static public void logoutUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("userId", null);
    }

}

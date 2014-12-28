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

/**
 * Created by tomas on 27-12-14.
 */
public class LoginValidator {
    
    private static LoginValidator instance = null;
    protected LoginValidator() {
       // Exists only to defeat instantiation.
    }

    public static LoginValidator getInstance() {
       if(instance == null) {
          instance = new LoginValidator();
       }
       return instance;
    }

    public void loginUser(HttpServletRequest req, HttpServletResponse res, User user) {
        HttpSession session = req.getSession();
        session.setAttribute("userId", user.getIduser());
    }

    public User validateUser(HttpServletRequest req, HttpServletResponse res, UserFacade userFacade) {
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

    public void logoutUser(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession();
        session.setAttribute("userId", null);
    }

}

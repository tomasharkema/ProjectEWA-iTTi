/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Car;
import entity.Event;
import entity.User;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import searching.TimeLine;

/**
 *
 * @author Repr
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public User findByFbid(BigInteger fbid) {
        Query query = em.createNamedQuery("User.findByFbid", User.class);
        query.setParameter("fbid", fbid);
        User user;
        try {
            user = (User)query.getSingleResult();
        } catch (NoResultException ex) {
            user = null;
        }
        return user;
    }

    public void addUser(User user) {
        em.persist(user);
    }

    public List findAll() {
        Query query = em.createNamedQuery("User.findAll");
        List<User> result = query.getResultList();
        return result;
    }
    public List<User> findByNAme(String name) {
        TypedQuery query = em.createNamedQuery("User.findByName", User.class);
        List<User> result = query.getResultList();
        return result;
    }

    public List<User> findFriendsbyNameASC(int userId) {
        TypedQuery query = em.createNamedQuery("User.findFriendsbyNameASC", User.class);
        query.setParameter("iduser", userId);
        List<User> result = query.getResultList();
        return result;
    }

    public List<User> findFriendsbyNameDSC(int userId) {
        TypedQuery query = em.createNamedQuery("User.findFriendsbyNameASC", User.class);
        query.setParameter("iduser", userId);
        List<User> result = query.getResultList();
        Collections.reverse(result);
        return result;
    }

    public List<TimeLine> findFriendsbyDateASC(int userId) {
        TypedQuery query = em.createNamedQuery("User.findFriendsbyDateASC", TimeLine.class);
        query.setParameter("iduser", userId);
        List<TimeLine> result = query.getResultList();
        return result;
    }

    public List<User> findFriendsbyDateDSC(int userId) {
        TypedQuery query = em.createNamedQuery("User.findFriendsbyDateASC", User.class);
        query.setParameter("iduser", userId);
        List<User> result = query.getResultList();
        Collections.reverse(result);
        return result;
    }

    public List<Event> findAttendingEvents(int userId) {
        TypedQuery query = em.createNamedQuery("User.findAttendingEvents", Event.class);
        query.setParameter("iduser", userId);
        List<Event> result = query.getResultList();
        return result;
    }

    public List<Car> findCars(int userId) {
        TypedQuery query = em.createNamedQuery("User.findCars", Car.class);
        query.setParameter("iduser", userId);
        List<Car> result = query.getResultList();
        return result;
    }

    public List<Event> findFriendEvents(int userId) {
        TypedQuery query = em.createNamedQuery("User.findFriendEvents", Event.class);
        query.setParameter("iduser", userId);
        List<Event> result = query.getResultList();
        return result;
    }
}

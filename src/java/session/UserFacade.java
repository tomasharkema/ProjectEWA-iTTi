/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.*;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Date;
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
    
    public void updateUser(User user) {
        super.edit(user);
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

    public List<TimeLine> findFriendsbyDateASC(User userId) {
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

    public boolean requestFriend(User currentUser, User inventee) {
        Friends.FriendRelation relation = currentUser.getRelation(inventee);
        if (relation != Friends.FriendRelation.NoFriends) {
            return false;
        }

        Friends f = new Friends(currentUser.getIduser(), inventee.getIduser());
        f.setDate(new Date());
        f.setApproved(false);

        List<Friends> list = currentUser.getFriendsList();
        list.add(f);

        currentUser.setFriendsList(list);
        edit(currentUser);
        return true;
    }

    public boolean cancelFriendship(User currentUser, User inventee) {
        Friends.FriendRelation relation = currentUser.getRelation(inventee);
        if (relation == Friends.FriendRelation.NoFriends) {
            return false;
        }

        Friend f = currentUser.getFriendRelation(inventee);

        List<Friends> list = currentUser.getFriendsList();
        list.remove(f.getChain());
        currentUser.setFriendsList(list);

        List<Friends> list1 = currentUser.getFriendsList1();
        list1.remove(f.getChain());
        currentUser.setFriendsList1(list1);
        return true;
    }

    public boolean approveFriendship(User currentUser, User inventee) {
        Friend relation = currentUser.getFriendRelation(inventee);

        if (relation.getRelation() == Friends.FriendRelation.NotConfirmed) {
            Friends f = relation.getChain();
            f.setDate(new Date());
            f.setApproved(true);

            List<Friends> list = currentUser.getFriendsList1();
            list.remove(f);
            list.add(f);
            currentUser.setFriendsList1(list);
            edit(currentUser);
            return true;
        }

        return false;
    }
}

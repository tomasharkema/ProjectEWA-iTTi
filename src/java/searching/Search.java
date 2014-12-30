/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searching;

import entity.Event;
import entity.Friends;
import entity.Location;
import entity.User;
import entity.UserHasEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import session.UserFacade;

/**
 *
 * @author Repr
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Search {
    @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;
    
    @EJB
    private UserFacade userFacade;
    
    
    /*
     Method used to search in the following database tables: Users, Events, Locations
     Will search with wildcards in front and behind the given string
    
     @Return: returns an ArrayList with 3 indexes. I(0) will contain a ArrayList with User objects, 
     I(1) will contain a ArrayList with Event objects, I(2) will contain a ArrayList with Location objects.
     */
    public List<List> wildSearch(String searchString) {
        List<List> results = new ArrayList(2);
        List<User> userResults;
        List<Event> eventResults;
        List<Location> locationResults;

        TypedQuery userQuery = em.createNamedQuery("User.findByName", User.class);
        userQuery.setParameter("iduser", "%" + searchString + "%");
        userResults = userQuery.getResultList();
        results.add(userResults);

        TypedQuery eventQuery = em.createNamedQuery("Event.findByEventname", Event.class);
        eventQuery.setParameter("eventName", "%" + searchString + "%");
        eventResults = eventQuery.getResultList();
        results.add(eventResults);

        TypedQuery locationQuery = em.createNamedQuery("location.findByLocationname", Location.class);
        eventQuery.setParameter("locationName", "%" + searchString + "%");
        locationResults = locationQuery.getResultList();
        results.add(locationResults);

        return results;
    }
    /*
    Method to find events and friendships sorted by date. Both events and friendships will be wrapped in the TimeLine interface and returned as a TimeLineNode.
    Lists will be sorted by date, 
    @Return: List<TimeLineNode> 
     */

    public List<TimeLineNode> timeLineSearch(User userId) {
        // Make list for various results. results is to combine friendUpdates and attendingUpdates
        List<TimeLineNode> results = new ArrayList();
        List<TimeLineNode> friendUpdates;
        List<TimeLineNode> attendingUpdates;
        //calling methods to get the updates
        friendUpdates = friendUpdates(userId);
        attendingUpdates = attendingUpdates(userId);
        //adding all results from friends and events into a single List
        results.addAll(friendUpdates);
        results.addAll(attendingUpdates);
        //Sort all results by date
        Collections.sort(results, new dateComparetor());

        return results;
    }
    
    /*
     Method called from timeLinesearch method to find all updates made by friends. returns a list with TimeLineNodes that should be considered unsorted
     @return List<TimeLineNode>. all nodes consist of 2 users.
     */
    private List<TimeLineNode> friendUpdates(int userId) {
        List<TimeLineNode> returnList = new ArrayList<>();

        List<TimeLineNode> yourFriends = findYourFriends(userId);
        for (TimeLineNode node : yourFriends) {
            int friendId = node.getTwo().getId();
            List<TimeLineNode> temp = friendUpdates(friendId);

            for (TimeLineNode temp1 : temp) {
                returnList.add(temp1);
            }
        }
        return returnList;
    }

    private List<TimeLineNode> findYourFriends(int userId) {
        //make list to save your own friends too
        List<TimeLineNode> yourFriends = new ArrayList();
        //temp list to safe your friends for query
        List<Friends> localFriendUpdates;
        //find yourself
        TypedQuery findYourself = em.createNamedQuery("User.findByIduser", TimeLine.class);
        findYourself.setParameter("iduser", userId);
        TimeLine currentUser = (TimeLine) findYourself.getSingleResult();

        //make a list of your own friends
        TypedQuery friendQuery = em.createNamedQuery("Friends.findByUserIduser", Friends.class);
        friendQuery.setParameter("iduser", userId);
        localFriendUpdates = friendQuery.getResultList();
        for (Friends localFriendUpdate : localFriendUpdates) {
            TypedQuery findUser = em.createNamedQuery("User.findByIduser", TimeLine.class);
            findUser.setParameter("iduser", localFriendUpdate.getUser1());
            TimeLine friend = (TimeLine) findUser.getSingleResult();
            TimeLineNode node = new TimeLineNode();
            node.setOne(currentUser);
            node.setTwo(friend);
            node.setDate(localFriendUpdate.getDate());
            node.findMergeLine();
            yourFriends.add(node);
        }
        return yourFriends;
    }
    
    /*
    Method called from timeLinesearch method to find all updates made by friends. returns a list with TimeLineNodes that should be considered unsorted
    @return List<TimeLineNode>. all nodes consist of 1 user and 1 event.
    */
    private List<TimeLineNode> attendingUpdates(User user) {
        List<TimeLine> findFriends;
        TypedQuery friendQuery = em.createNamedQuery("User.findFriendsbyDateASC", TimeLine.class);
        friendQuery.setParameter("iduser", user);
        findFriends = friendQuery.getResultList();

        //find yourself
        TypedQuery findYourself = em.createNamedQuery("User.findByIduser", TimeLine.class);
        findYourself.setParameter("iduser", user);
        TimeLine currentUser = (TimeLine) findYourself.getSingleResult();

        List<TimeLineNode> returnList = new ArrayList<>();


        for (TimeLineNode yourFriend : user.getFriends()) {
            int friendId = yourFriend.getTwo().getId();
            //make list of all UserHasEvents
            List<UserHasEvent> userHasEvent;
            TypedQuery findUsersWithEvent = em.createNamedQuery("UserHasEvent.findByUserIduser", UserHasEvent.class);
            findUsersWithEvent.setParameter("userIduser", friendId);
            userHasEvent = findUsersWithEvent.getResultList();
            for (UserHasEvent userHasEvent1 : userHasEvent) {
                TimeLineNode node = new TimeLineNode();
                node.setOne(currentUser);
                node.setTwo(userHasEvent1.getEvent());
                node.setDate(userHasEvent1.getDate());
                node.findMergeLine();
                returnList.add(node);
            }
        }

        return returnList;
    }
}

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
import entity.UserHasEventAtLocation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Repr
 */
public class Search {

    private EntityManager em;

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

    public List<TimeLineNode> timeLineSearch(int userId) {
        List<TimeLineNode> results = new ArrayList();
        List<TimeLineNode> friendUpdates;
        List<TimeLineNode> attendingUpdates;

        friendUpdates = friendUpdates(userId);
        attendingUpdates = attendingUpdates(userId);
        results.addAll(friendUpdates);
        results.addAll(attendingUpdates);

        Collections.sort(results, new dateComparetor());
        return results;
    }
    
    /*
    Method called from timeLinesearch method to find all updates made by friends. returns a list with TimeLineNodes that should be considered unsorted
    @return List<TimeLineNode>. all nodes consist of 2 users.
    */
    private List<TimeLineNode> friendUpdates(int userId) {
        List<TimeLine> localFriendUpdates;
        TypedQuery friendQuery = em.createNamedQuery("User.findFriendsbyDateASC", TimeLine.class);
        friendQuery.setParameter("iduser", userId);
        localFriendUpdates = friendQuery.getResultList();

        List<TimeLineNode> friendUpdates = new ArrayList();

        for (TimeLine friendUpdate : localFriendUpdates) {
            TypedQuery findUpdates = em.createNamedQuery("User.findFriendsbyDateASC", TimeLine.class);
            findUpdates.setParameter("iduser", friendUpdate.getId());
            List<TimeLine> temp = findUpdates.getResultList();
            for (TimeLine temp1 : temp) {
                TimeLineNode node = new TimeLineNode();
                node.setOne(friendUpdate);
                node.setTwo(temp1);
                TypedQuery findDate = em.createNamedQuery("Friends.findDateWithUsers", Friends.class);
                findDate.setParameter("userIduser", node.getOne().getId()).setParameter("userIduser1", node.getTwo().getId());
                Friends friendShip = (Friends) findDate.getSingleResult();
                node.setDate(friendShip.getDate());
                node.findMergeLine();
                friendUpdates.add(node);
            }
        }
        Collections.reverse(friendUpdates);
        return friendUpdates;
    }
    
    /*
    Method called from timeLinesearch method to find all updates made by friends. returns a list with TimeLineNodes that should be considered unsorted
    @return List<TimeLineNode>. all nodes consist of 1 user and 1 event.
    */
    private List<TimeLineNode> attendingUpdates(int userId) {
        List<TimeLine> findFriends;
        TypedQuery friendQuery = em.createNamedQuery("User.findFriendsbyDateASC", TimeLine.class);
        friendQuery.setParameter("iduser", userId);
        findFriends = friendQuery.getResultList();

        List<TimeLineNode> eventUpdates = new ArrayList<>();

        for (TimeLine temp1 : findFriends) {
            TypedQuery eventUpdate = em.createNamedQuery("UserHasEventAtLocation.findByUserIduser", UserHasEventAtLocation.class);
            eventUpdate.setParameter("iduser", temp1.getId());
            List<UserHasEventAtLocation> temp = eventUpdate.getResultList();
            for (UserHasEventAtLocation linkObject : temp) {
                TimeLineNode node = new TimeLineNode();
                node.setOne(temp1);
                node.setTwo(linkObject.getLocationHasEvent().getEvent());
                node.setDate(linkObject.getSubscriptiondate());
                node.findMergeLine();
                eventUpdates.add(node);
            }
        }
        Collections.reverse(eventUpdates);
        return eventUpdates;
    }
}

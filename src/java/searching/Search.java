/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searching;

import entity.*;

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
    public List<TimeLineNode> getTimelineForUser(User _user) {
        List<TimeLineNode> returnList = new ArrayList<>();
        User user = userFacade.find(_user.getId());
        returnList.addAll(attendingUpdates(user));
        for (Friend friend : user.getFriends()) {
            returnList.addAll(findYourFriends(friend.getUser()));
            returnList.addAll(attendingUpdates(friend.getUser()));
        }

        Collections.sort(returnList, new dateComparetor());
        
        return returnList;
    }

    public List<TimeLineNode> getTimelineForFriend(User friend) {
        List<TimeLineNode> returnList = new ArrayList<>();

        returnList.addAll(attendingUpdates(friend));
        returnList.addAll(findYourFriends(friend));

        Collections.sort(returnList, new dateComparetor());

        return returnList;
    }

    private List<TimeLineNode> findYourFriends(User user) {
        //make list to save your own friends too
        List<TimeLineNode> yourFriends = new ArrayList();
        //temp list to safe your friends for query
        List<Friend> localFriendUpdates = user.getFriendsApproved();
        //find yourself
        TimeLine currentUser = (TimeLine) user;

        for (Friend localFriendUpdate : localFriendUpdates) {
            TimeLine friend = (TimeLine)localFriendUpdate.getUser();
            TimeLineNode node = new TimeLineNode();
            node.setOne(currentUser);
            node.setTwo(friend);
            node.setDate(localFriendUpdate.getChain().getDate());
            node.findMergeLine();
            yourFriends.add(node);
        }
        return yourFriends;
    }
    
    /*
    Method called from timeLinesearch method to find all updates .made by friends. returns a list with TimeLineNodes that should be considered unsorted
    @return List<TimeLineNode>. all nodes consist of 1 user and 1 event.
    @param User. 
    */
    private List<TimeLineNode> attendingUpdates(User user) {

        List<TimeLineNode> returnList = new ArrayList<>();

            //make list of all UserHasEvents
            List<UserHasEvent> userHasEvent;
            userHasEvent = user.getUserHasEventList();
            for (UserHasEvent userHasEvent1 : userHasEvent) {
                TimeLineNode node = new TimeLineNode();
                node.setOne(user);
                node.setTwo(userHasEvent1.getEvent());
                node.setDate(userHasEvent1.getDate());
                node.findMergeLine();
                returnList.add(node);
            }
        
        return returnList;
    }
}

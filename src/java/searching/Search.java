/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searching;

import entity.Event;
import entity.Location;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Repr
 */
public class Search {
    private List<List> results;
    private List<User> userResults;
    private List<Event> eventResults;
    private List<Location> locationResults;
    private EntityManager em;
    
    
    /*
    Method used to search in the following database tables: Users, Events, Locations
    Will search with wildcards in front and behind the given string
    
    @Return: returns an ArrayList with 3 indexes. I(0) will contain a ArrayList with User objects, 
    I(1) will contain a ArrayList with Event objects, I(2) will contain a ArrayList with Location objects.
    */
    public List<List> wildSearch(String searchString){
        results = new ArrayList(2);
        
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
    
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Event;
import entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Repr
 */
@Stateless
public class EventFacade extends AbstractFacade<Event> {

    @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventFacade() {
        super(Event.class);
    }

    public List<User> findAttending(int eventId) {
        TypedQuery query = em.createNamedQuery("User.findAttending", User.class);
        query.setParameter("idevent", eventId);
        List<User> result = query.getResultList();
        return result;
    }

    public List<Event> findAllDateASC() {
        TypedQuery query = em.createNamedQuery("Event.findByDateASC", Event.class);
        List<Event> result = query.getResultList();
        return result;
    }

    public List<Event> findByQuery(String q) {
        TypedQuery query = em.createNamedQuery("Event.findNameLike", Event.class);
        query.setParameter("name", "%"+q+"%");
        List<Event> result = query.getResultList();
        return result;
    }
}

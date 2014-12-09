/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.User;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    public User findByFbid(Integer fbid){
        Query query = em.createNamedQuery("User.findByFbid", User.class);
        query.setParameter("fbid", fbid);
        Collection result = query.getResultList();
        Object[] resultArray = result.toArray();
        return (User)resultArray[0];
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Repr
 */
@Entity
@Table(name = "user_has_event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserHasEvent.findAll", query = "SELECT u FROM UserHasEvent u"),
    @NamedQuery(name = "UserHasEvent.findByUserIduser", query = "SELECT u FROM UserHasEvent u WHERE u.userHasEventPK.userIduser = :userIduser"),
    @NamedQuery(name = "UserHasEvent.findByEventIdevent", query = "SELECT u FROM UserHasEvent u WHERE u.userHasEventPK.eventIdevent = :eventIdevent")})
public class UserHasEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserHasEventPK userHasEventPK;
    @JoinColumn(name = "event_idevent", referencedColumnName = "idevennt", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Event event;
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "car_registration", referencedColumnName = "registration")
    @ManyToOne(optional = false)
    private Car carRegistration;

    public UserHasEvent() {
    }

    public UserHasEvent(UserHasEventPK userHasEventPK) {
        this.userHasEventPK = userHasEventPK;
    }

    public UserHasEvent(int userIduser, int eventIdevent) {
        this.userHasEventPK = new UserHasEventPK(userIduser, eventIdevent);
    }

    public UserHasEventPK getUserHasEventPK() {
        return userHasEventPK;
    }

    public void setUserHasEventPK(UserHasEventPK userHasEventPK) {
        this.userHasEventPK = userHasEventPK;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCarRegistration() {
        return carRegistration;
    }

    public void setCarRegistration(Car carRegistration) {
        this.carRegistration = carRegistration;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userHasEventPK != null ? userHasEventPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasEvent)) {
            return false;
        }
        UserHasEvent other = (UserHasEvent) object;
        if ((this.userHasEventPK == null && other.userHasEventPK != null) || (this.userHasEventPK != null && !this.userHasEventPK.equals(other.userHasEventPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserHasEvent[ userHasEventPK=" + userHasEventPK + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import searching.Notification;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tomas
 */
@Entity
@Table(name = "user_has_event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserHasEvent.findAll", query = "SELECT u FROM UserHasEvent u"),
    @NamedQuery(name = "UserHasEvent.findByUserIduser", query = "SELECT u FROM UserHasEvent u WHERE u.userHasEventPK.userIduser = :userIduser"),
    @NamedQuery(name = "UserHasEvent.findByEventIdevent", query = "SELECT u FROM UserHasEvent u WHERE u.userHasEventPK.eventIdevent = :eventIdevent"),
    @NamedQuery(name = "UserHasEvent.findByDate", query = "SELECT u FROM UserHasEvent u WHERE u.date = :date")})
public class UserHasEvent implements Serializable, Notification {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserHasEventPK userHasEventPK;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "event_idevent", referencedColumnName = "idevent", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Event event;
    @JoinColumn(name = "car_id", referencedColumnName = "registration")
    @ManyToOne
    private Car carId;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Car getCarId() {
        return carId;
    }

    public void setCarId(Car carId) {
        this.carId = carId;
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

    @Override
    public String getTitle() {
        return getUser().getName();
    }

    @Override
    public Date getFiredDate() {
        return date;
    }

    @Override
    public String getImage() {
        return getUser().getUserAvatar();
    }

    @Override
    public String getDesc() {
        return  "drives with you to " + getEvent().getName();
    }

    @Override
    public boolean shouldShow() {
        return true;
    }

    @Override
    public Object getPrimaryObject() {
        return getEvent();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import searching.TimeLine;

/**
 *
 * @author Repr
 */
@Entity
@Table(name = "user_has_event_at_location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserHasEventAtLocation.findAll", query = "SELECT u FROM UserHasEventAtLocation u"),
    @NamedQuery(name = "UserHasEventAtLocation.findByUserIduser", query = "SELECT u FROM UserHasEventAtLocation u WHERE u.userHasEventAtLocationPK.userIduser = :userIduser"),
    @NamedQuery(name = "UserHasEventAtLocation.findByLocationHasEventLocationIdlocation", query = "SELECT u FROM UserHasEventAtLocation u WHERE u.userHasEventAtLocationPK.locationHasEventLocationIdlocation = :locationHasEventLocationIdlocation"),
    @NamedQuery(name = "UserHasEventAtLocation.findByLocationHasEventEventIdevennt", query = "SELECT u FROM UserHasEventAtLocation u WHERE u.userHasEventAtLocationPK.locationHasEventEventIdevennt = :locationHasEventEventIdevennt"),
    @NamedQuery(name = "UserHasEventAtLocation.findBySubscriptiondate", query = "SELECT u FROM UserHasEventAtLocation u WHERE u.subscriptiondate = :subscriptiondate")})

public class UserHasEventAtLocation implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserHasEventAtLocationPK userHasEventAtLocationPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subscriptiondate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subscriptiondate;
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumns({
        @JoinColumn(name = "location_has_event_location_idlocation", referencedColumnName = "location_idlocation", insertable = false, updatable = false),
        @JoinColumn(name = "location_has_event_event_idevent", referencedColumnName = "event_idevent", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private LocationHasEvent locationHasEvent;
    @JoinColumn(name = "car_registration", referencedColumnName = "registration")
    @ManyToOne(optional = false)
    private Car carRegistration;

    public UserHasEventAtLocation() {
    }

    public UserHasEventAtLocation(UserHasEventAtLocationPK userHasEventAtLocationPK) {
        this.userHasEventAtLocationPK = userHasEventAtLocationPK;
    }

    public UserHasEventAtLocation(UserHasEventAtLocationPK userHasEventAtLocationPK, Date subscriptiondate) {
        this.userHasEventAtLocationPK = userHasEventAtLocationPK;
        this.subscriptiondate = subscriptiondate;
    }

    public UserHasEventAtLocation(int userIduser, int locationHasEventLocationIdlocation, int locationHasEventEventIdevennt) {
        this.userHasEventAtLocationPK = new UserHasEventAtLocationPK(userIduser, locationHasEventLocationIdlocation, locationHasEventEventIdevennt);
    }

    public UserHasEventAtLocationPK getUserHasEventAtLocationPK() {
        return userHasEventAtLocationPK;
    }

    public void setUserHasEventAtLocationPK(UserHasEventAtLocationPK userHasEventAtLocationPK) {
        this.userHasEventAtLocationPK = userHasEventAtLocationPK;
    }

    public Date getSubscriptiondate() {
        return subscriptiondate;
    }

    public void setSubscriptiondate(Date subscriptiondate) {
        this.subscriptiondate = subscriptiondate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocationHasEvent getLocationHasEvent() {
        return locationHasEvent;
    }

    public void setLocationHasEvent(LocationHasEvent locationHasEvent) {
        this.locationHasEvent = locationHasEvent;
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
        hash += (userHasEventAtLocationPK != null ? userHasEventAtLocationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasEventAtLocation)) {
            return false;
        }
        UserHasEventAtLocation other = (UserHasEventAtLocation) object;
        if ((this.userHasEventAtLocationPK == null && other.userHasEventAtLocationPK != null) || (this.userHasEventAtLocationPK != null && !this.userHasEventAtLocationPK.equals(other.userHasEventAtLocationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserHasEventAtLocation[ userHasEventAtLocationPK=" + userHasEventAtLocationPK + " ]";
    }

    
    
}

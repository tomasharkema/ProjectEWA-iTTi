/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Repr
 */
@Entity
@Table(name = "location_has_event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocationHasEvent.findAll", query = "SELECT l FROM LocationHasEvent l"),
    @NamedQuery(name = "LocationHasEvent.findByLocationIdlocation", query = "SELECT l FROM LocationHasEvent l WHERE l.locationHasEventPK.locationIdlocation = :locationIdlocation"),
    @NamedQuery(name = "LocationHasEvent.findByEventIdevennt", query = "SELECT l FROM LocationHasEvent l WHERE l.locationHasEventPK.eventIdevennt = :eventIdevennt"),
    @NamedQuery(name = "LocationHasEvent.findByEventDate", query = "SELECT l FROM LocationHasEvent l WHERE l.eventDate = :eventDate")})
public class LocationHasEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LocationHasEventPK locationHasEventPK;
    @Column(name = "eventDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationHasEvent")
    private Collection<UserHasEventAtLocation> userHasEventAtLocationCollection;
    @JoinColumn(name = "location_idlocation", referencedColumnName = "idlocation", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Location location;
    @JoinColumn(name = "event_idevent", referencedColumnName = "idevent", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Event event;

    public LocationHasEvent() {
    }

    public LocationHasEvent(LocationHasEventPK locationHasEventPK) {
        this.locationHasEventPK = locationHasEventPK;
    }

    public LocationHasEvent(int locationIdlocation, int eventIdevennt) {
        this.locationHasEventPK = new LocationHasEventPK(locationIdlocation, eventIdevennt);
    }

    public LocationHasEventPK getLocationHasEventPK() {
        return locationHasEventPK;
    }

    public void setLocationHasEventPK(LocationHasEventPK locationHasEventPK) {
        this.locationHasEventPK = locationHasEventPK;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @XmlTransient
    public Collection<UserHasEventAtLocation> getUserHasEventAtLocationCollection() {
        return userHasEventAtLocationCollection;
    }

    public void setUserHasEventAtLocationCollection(Collection<UserHasEventAtLocation> userHasEventAtLocationCollection) {
        this.userHasEventAtLocationCollection = userHasEventAtLocationCollection;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationHasEventPK != null ? locationHasEventPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocationHasEvent)) {
            return false;
        }
        LocationHasEvent other = (LocationHasEvent) object;
        if ((this.locationHasEventPK == null && other.locationHasEventPK != null) || (this.locationHasEventPK != null && !this.locationHasEventPK.equals(other.locationHasEventPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LocationHasEvent[ locationHasEventPK=" + locationHasEventPK + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Repr
 */
@Embeddable
public class LocationHasEventPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "location_idlocation")
    private int locationIdlocation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "event_idevennt")
    private int eventIdevennt;

    public LocationHasEventPK() {
    }

    public LocationHasEventPK(int locationIdlocation, int eventIdevennt) {
        this.locationIdlocation = locationIdlocation;
        this.eventIdevennt = eventIdevennt;
    }

    public int getLocationIdlocation() {
        return locationIdlocation;
    }

    public void setLocationIdlocation(int locationIdlocation) {
        this.locationIdlocation = locationIdlocation;
    }

    public int getEventIdevennt() {
        return eventIdevennt;
    }

    public void setEventIdevennt(int eventIdevennt) {
        this.eventIdevennt = eventIdevennt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) locationIdlocation;
        hash += (int) eventIdevennt;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocationHasEventPK)) {
            return false;
        }
        LocationHasEventPK other = (LocationHasEventPK) object;
        if (this.locationIdlocation != other.locationIdlocation) {
            return false;
        }
        if (this.eventIdevennt != other.eventIdevennt) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LocationHasEventPK[ locationIdlocation=" + locationIdlocation + ", eventIdevennt=" + eventIdevennt + " ]";
    }
    
}

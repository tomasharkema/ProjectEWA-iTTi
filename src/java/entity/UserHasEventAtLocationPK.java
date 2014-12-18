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
public class UserHasEventAtLocationPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_iduser")
    private int userIduser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "location_has_event_location_idlocation")
    private int locationHasEventLocationIdlocation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "location_has_event_event_idevent")
    private int locationHasEventEventIdevennt;

    public UserHasEventAtLocationPK() {
    }

    public UserHasEventAtLocationPK(int userIduser, int locationHasEventLocationIdlocation, int locationHasEventEventIdevennt) {
        this.userIduser = userIduser;
        this.locationHasEventLocationIdlocation = locationHasEventLocationIdlocation;
        this.locationHasEventEventIdevennt = locationHasEventEventIdevennt;
    }

    public int getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(int userIduser) {
        this.userIduser = userIduser;
    }

    public int getLocationHasEventLocationIdlocation() {
        return locationHasEventLocationIdlocation;
    }

    public void setLocationHasEventLocationIdlocation(int locationHasEventLocationIdlocation) {
        this.locationHasEventLocationIdlocation = locationHasEventLocationIdlocation;
    }

    public int getLocationHasEventEventIdevennt() {
        return locationHasEventEventIdevennt;
    }

    public void setLocationHasEventEventIdevennt(int locationHasEventEventIdevennt) {
        this.locationHasEventEventIdevennt = locationHasEventEventIdevennt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userIduser;
        hash += (int) locationHasEventLocationIdlocation;
        hash += (int) locationHasEventEventIdevennt;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasEventAtLocationPK)) {
            return false;
        }
        UserHasEventAtLocationPK other = (UserHasEventAtLocationPK) object;
        if (this.userIduser != other.userIduser) {
            return false;
        }
        if (this.locationHasEventLocationIdlocation != other.locationHasEventLocationIdlocation) {
            return false;
        }
        if (this.locationHasEventEventIdevennt != other.locationHasEventEventIdevennt) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserHasEventAtLocationPK[ userIduser=" + userIduser + ", locationHasEventLocationIdlocation=" + locationHasEventLocationIdlocation + ", locationHasEventEventIdevennt=" + locationHasEventEventIdevennt + " ]";
    }
    
}

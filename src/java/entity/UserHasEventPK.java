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
 * @author tomas
 */
@Embeddable
public class UserHasEventPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_iduser")
    private int userIduser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "event_idevent")
    private int eventIdevent;

    public UserHasEventPK() {
    }

    public UserHasEventPK(int userIduser, int eventIdevent) {
        this.userIduser = userIduser;
        this.eventIdevent = eventIdevent;
    }

    public int getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(int userIduser) {
        this.userIduser = userIduser;
    }

    public int getEventIdevent() {
        return eventIdevent;
    }

    public void setEventIdevent(int eventIdevent) {
        this.eventIdevent = eventIdevent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userIduser;
        hash += (int) eventIdevent;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasEventPK)) {
            return false;
        }
        UserHasEventPK other = (UserHasEventPK) object;
        if (this.userIduser != other.userIduser) {
            return false;
        }
        if (this.eventIdevent != other.eventIdevent) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserHasEventPK[ userIduser=" + userIduser + ", eventIdevent=" + eventIdevent + " ]";
    }
    
}

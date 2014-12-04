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
public class UserHasEvenementPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_iduser")
    private int userIduser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "evenement_idevenement")
    private int evenementIdevenement;

    public UserHasEvenementPK() {
    }

    public UserHasEvenementPK(int userIduser, int evenementIdevenement) {
        this.userIduser = userIduser;
        this.evenementIdevenement = evenementIdevenement;
    }

    public int getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(int userIduser) {
        this.userIduser = userIduser;
    }

    public int getEvenementIdevenement() {
        return evenementIdevenement;
    }

    public void setEvenementIdevenement(int evenementIdevenement) {
        this.evenementIdevenement = evenementIdevenement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userIduser;
        hash += (int) evenementIdevenement;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasEvenementPK)) {
            return false;
        }
        UserHasEvenementPK other = (UserHasEvenementPK) object;
        if (this.userIduser != other.userIduser) {
            return false;
        }
        if (this.evenementIdevenement != other.evenementIdevenement) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserHasEvenementPK[ userIduser=" + userIduser + ", evenementIdevenement=" + evenementIdevenement + " ]";
    }
    
}

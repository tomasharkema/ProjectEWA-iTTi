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
public class FriendsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_iduser")
    private int userIduser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_iduser1")
    private int userIduser1;

    public FriendsPK() {
    }

    public FriendsPK(int userIduser, int userIduser1) {
        this.userIduser = userIduser;
        this.userIduser1 = userIduser1;
    }

    public int getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(int userIduser) {
        this.userIduser = userIduser;
    }

    public int getUserIduser1() {
        return userIduser1;
    }

    public void setUserIduser1(int userIduser1) {
        this.userIduser1 = userIduser1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userIduser;
        hash += (int) userIduser1;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendsPK)) {
            return false;
        }
        FriendsPK other = (FriendsPK) object;
        if (this.userIduser != other.userIduser) {
            return false;
        }
        if (this.userIduser1 != other.userIduser1) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FriendsPK[ userIduser=" + userIduser + ", userIduser1=" + userIduser1 + " ]";
    }
    
}

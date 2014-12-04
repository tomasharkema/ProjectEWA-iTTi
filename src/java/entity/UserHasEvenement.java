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
@Table(name = "user_has_evenement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserHasEvenement.findAll", query = "SELECT u FROM UserHasEvenement u"),
    @NamedQuery(name = "UserHasEvenement.findByUserIduser", query = "SELECT u FROM UserHasEvenement u WHERE u.userHasEvenementPK.userIduser = :userIduser"),
    @NamedQuery(name = "UserHasEvenement.findByEvenementIdevenement", query = "SELECT u FROM UserHasEvenement u WHERE u.userHasEvenementPK.evenementIdevenement = :evenementIdevenement")})
public class UserHasEvenement implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserHasEvenementPK userHasEvenementPK;
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "evenement_idevenement", referencedColumnName = "idevenement", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Evenement evenement;
    @JoinColumn(name = "Voertuig_kenteken", referencedColumnName = "kenteken")
    @ManyToOne(optional = false)
    private Voertuig voertuigkenteken;

    public UserHasEvenement() {
    }

    public UserHasEvenement(UserHasEvenementPK userHasEvenementPK) {
        this.userHasEvenementPK = userHasEvenementPK;
    }

    public UserHasEvenement(int userIduser, int evenementIdevenement) {
        this.userHasEvenementPK = new UserHasEvenementPK(userIduser, evenementIdevenement);
    }

    public UserHasEvenementPK getUserHasEvenementPK() {
        return userHasEvenementPK;
    }

    public void setUserHasEvenementPK(UserHasEvenementPK userHasEvenementPK) {
        this.userHasEvenementPK = userHasEvenementPK;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Voertuig getVoertuigkenteken() {
        return voertuigkenteken;
    }

    public void setVoertuigkenteken(Voertuig voertuigkenteken) {
        this.voertuigkenteken = voertuigkenteken;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userHasEvenementPK != null ? userHasEvenementPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasEvenement)) {
            return false;
        }
        UserHasEvenement other = (UserHasEvenement) object;
        if ((this.userHasEvenementPK == null && other.userHasEvenementPK != null) || (this.userHasEvenementPK != null && !this.userHasEvenementPK.equals(other.userHasEvenementPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserHasEvenement[ userHasEvenementPK=" + userHasEvenementPK + " ]";
    }
    
}

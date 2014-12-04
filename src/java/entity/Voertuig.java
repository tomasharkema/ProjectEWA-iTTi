/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Repr
 */
@Entity
@Table(name = "voertuig")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voertuig.findAll", query = "SELECT v FROM Voertuig v"),
    @NamedQuery(name = "Voertuig.findByKenteken", query = "SELECT v FROM Voertuig v WHERE v.kenteken = :kenteken"),
    @NamedQuery(name = "Voertuig.findByMerk", query = "SELECT v FROM Voertuig v WHERE v.merk = :merk"),
    @NamedQuery(name = "Voertuig.findByKleur", query = "SELECT v FROM Voertuig v WHERE v.kleur = :kleur"),
    @NamedQuery(name = "Voertuig.findByType", query = "SELECT v FROM Voertuig v WHERE v.type = :type"),
    @NamedQuery(name = "Voertuig.findByAantalZitplaatsen", query = "SELECT v FROM Voertuig v WHERE v.aantalZitplaatsen = :aantalZitplaatsen")})
public class Voertuig implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kenteken")
    private Integer kenteken;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "merk")
    private String merk;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "kleur")
    private String kleur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aantalZitplaatsen")
    private int aantalZitplaatsen;
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User userIduser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voertuigkenteken")
    private Collection<UserHasEvenement> userHasEvenementCollection;

    public Voertuig() {
    }

    public Voertuig(Integer kenteken) {
        this.kenteken = kenteken;
    }

    public Voertuig(Integer kenteken, String merk, String kleur, String type, int aantalZitplaatsen) {
        this.kenteken = kenteken;
        this.merk = merk;
        this.kleur = kleur;
        this.type = type;
        this.aantalZitplaatsen = aantalZitplaatsen;
    }

    public Integer getKenteken() {
        return kenteken;
    }

    public void setKenteken(Integer kenteken) {
        this.kenteken = kenteken;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAantalZitplaatsen() {
        return aantalZitplaatsen;
    }

    public void setAantalZitplaatsen(int aantalZitplaatsen) {
        this.aantalZitplaatsen = aantalZitplaatsen;
    }

    public User getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(User userIduser) {
        this.userIduser = userIduser;
    }

    @XmlTransient
    public Collection<UserHasEvenement> getUserHasEvenementCollection() {
        return userHasEvenementCollection;
    }

    public void setUserHasEvenementCollection(Collection<UserHasEvenement> userHasEvenementCollection) {
        this.userHasEvenementCollection = userHasEvenementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kenteken != null ? kenteken.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voertuig)) {
            return false;
        }
        Voertuig other = (Voertuig) object;
        if ((this.kenteken == null && other.kenteken != null) || (this.kenteken != null && !this.kenteken.equals(other.kenteken))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Voertuig[ kenteken=" + kenteken + " ]";
    }
    
}

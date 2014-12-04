/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Repr
 */
@Entity
@Table(name = "evenement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evenement.findAll", query = "SELECT e FROM Evenement e"),
    @NamedQuery(name = "Evenement.findByIdevenement", query = "SELECT e FROM Evenement e WHERE e.idevenement = :idevenement"),
    @NamedQuery(name = "Evenement.findByEvenementNaam", query = "SELECT e FROM Evenement e WHERE e.evenementNaam = :evenementNaam"),
    @NamedQuery(name = "Evenement.findByEvenementLocatie", query = "SELECT e FROM Evenement e WHERE e.evenementLocatie = :evenementLocatie"),
    @NamedQuery(name = "Evenement.findByEvenementDatum", query = "SELECT e FROM Evenement e WHERE e.evenementDatum = :evenementDatum")})
public class Evenement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevenement")
    private Integer idevenement;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "evenementNaam")
    private String evenementNaam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "evenementLocatie")
    private String evenementLocatie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "evenementDatum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date evenementDatum;
    @Lob
    @Column(name = "evenementLogo")
    private byte[] evenementLogo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evenement")
    private Collection<UserHasEvenement> userHasEvenementCollection;

    public Evenement() {
    }

    public Evenement(Integer idevenement) {
        this.idevenement = idevenement;
    }

    public Evenement(Integer idevenement, String evenementNaam, String evenementLocatie, Date evenementDatum) {
        this.idevenement = idevenement;
        this.evenementNaam = evenementNaam;
        this.evenementLocatie = evenementLocatie;
        this.evenementDatum = evenementDatum;
    }

    public Integer getIdevenement() {
        return idevenement;
    }

    public void setIdevenement(Integer idevenement) {
        this.idevenement = idevenement;
    }

    public String getEvenementNaam() {
        return evenementNaam;
    }

    public void setEvenementNaam(String evenementNaam) {
        this.evenementNaam = evenementNaam;
    }

    public String getEvenementLocatie() {
        return evenementLocatie;
    }

    public void setEvenementLocatie(String evenementLocatie) {
        this.evenementLocatie = evenementLocatie;
    }

    public Date getEvenementDatum() {
        return evenementDatum;
    }

    public void setEvenementDatum(Date evenementDatum) {
        this.evenementDatum = evenementDatum;
    }

    public byte[] getEvenementLogo() {
        return evenementLogo;
    }

    public void setEvenementLogo(byte[] evenementLogo) {
        this.evenementLogo = evenementLogo;
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
        hash += (idevenement != null ? idevenement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.idevenement == null && other.idevenement != null) || (this.idevenement != null && !this.idevenement.equals(other.idevenement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Evenement[ idevenement=" + idevenement + " ]";
    }
    
}

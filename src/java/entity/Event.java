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
@Table(name = "event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),
    @NamedQuery(name = "Event.findByIdevennt", query = "SELECT e FROM Event e WHERE e.idevennt = :idevennt"),
    @NamedQuery(name = "Event.findByEventName", query = "SELECT e FROM Event e WHERE e.eventName = :eventName"),
    @NamedQuery(name = "Event.findByEventLocation", query = "SELECT e FROM Event e WHERE e.eventLocation = :eventLocation"),
    @NamedQuery(name = "Event.findByEvenDate", query = "SELECT e FROM Event e WHERE e.evenDate = :evenDate")})
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevennt")
    private Integer idevennt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "eventName")
    private String eventName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "eventLocation")
    private String eventLocation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "evenDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date evenDate;
    @Lob
    @Column(name = "eventLogo")
    private byte[] eventLogo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Collection<UserHasEvent> userHasEventCollection;

    public Event() {
    }

    public Event(Integer idevennt) {
        this.idevennt = idevennt;
    }

    public Event(Integer idevennt, String eventName, String eventLocation, Date evenDate) {
        this.idevennt = idevennt;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.evenDate = evenDate;
    }

    public Integer getIdevennt() {
        return idevennt;
    }

    public void setIdevennt(Integer idevennt) {
        this.idevennt = idevennt;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public Date getEvenDate() {
        return evenDate;
    }

    public void setEvenDate(Date evenDate) {
        this.evenDate = evenDate;
    }

    public byte[] getEventLogo() {
        return eventLogo;
    }

    public void setEventLogo(byte[] eventLogo) {
        this.eventLogo = eventLogo;
    }

    @XmlTransient
    public Collection<UserHasEvent> getUserHasEventCollection() {
        return userHasEventCollection;
    }

    public void setUserHasEventCollection(Collection<UserHasEvent> userHasEventCollection) {
        this.userHasEventCollection = userHasEventCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevennt != null ? idevennt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.idevennt == null && other.idevennt != null) || (this.idevennt != null && !this.idevennt.equals(other.idevennt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Event[ idevennt=" + idevennt + " ]";
    }


    
}

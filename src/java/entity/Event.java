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
    @NamedQuery(name = "Event.findByEvenDate", query = "SELECT e FROM Event e WHERE e.evenDate = :evenDate"),
    @NamedQuery(name = "Event.findByEventname", query = "SELECT e FROM Event e WHERE e.eventname = :eventname"),
    @NamedQuery(name = "Event.findAttending", query = "select u FROM User u JOIN UserHasEventAtLocation us ON u.iduser = us.user_iduser JOIN Event e ON us.location_has_event_event_idevennt = e.idevennt WHERE e.idevennt = :idevent")})
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevennt")
    private Integer idevennt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "evenDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date evenDate;
    @Lob
    @Size(max = 65535)
    @Column(name = "eventLogo")
    private String eventLogo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "eventname")
    private String eventname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private Collection<LocationHasEvent> locationHasEventCollection;

    public Event() {
    }

    public Event(Integer idevennt) {
        this.idevennt = idevennt;
    }

    public Event(Integer idevennt, Date evenDate, String eventname) {
        this.idevennt = idevennt;
        this.evenDate = evenDate;
        this.eventname = eventname;
    }

    public Integer getIdevennt() {
        return idevennt;
    }

    public void setIdevennt(Integer idevennt) {
        this.idevennt = idevennt;
    }

    public Date getEvenDate() {
        return evenDate;
    }

    public void setEvenDate(Date evenDate) {
        this.evenDate = evenDate;
    }

    public String getEventLogo() {
        return eventLogo;
    }

    public void setEventLogo(String eventLogo) {
        this.eventLogo = eventLogo;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    @XmlTransient
    public Collection<LocationHasEvent> getLocationHasEventCollection() {
        return locationHasEventCollection;
    }

    public void setLocationHasEventCollection(Collection<LocationHasEvent> locationHasEventCollection) {
        this.locationHasEventCollection = locationHasEventCollection;
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

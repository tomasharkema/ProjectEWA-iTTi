/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tomas
 */
@Entity
@Table(name = "location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.findByIdlocation", query = "SELECT l FROM Location l WHERE l.idlocation = :idlocation"),
    @NamedQuery(name = "Location.findByCity", query = "SELECT l FROM Location l WHERE l.city = :city"),
    @NamedQuery(name = "Location.findByAddress", query = "SELECT l FROM Location l WHERE l.address = :address"),
    @NamedQuery(name = "Location.findByLocationname", query = "SELECT l FROM Location l WHERE l.locationname = :locationname")})
public class Location implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlocation")
    private Integer idlocation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "city")
    private String city;
    @Size(max = 45)
    @Column(name = "address")
    private String address;
    @Lob
    @Size(max = 65535)
    @Column(name = "locationpicture")
    private String locationpicture;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "locationname")
    private String locationname;
    @OneToMany(mappedBy = "locationid")
    private List<Event> eventList;

    public Location() {
    }

    public Location(Integer idlocation) {
        this.idlocation = idlocation;
    }

    public Location(Integer idlocation, String city, String locationname) {
        this.idlocation = idlocation;
        this.city = city;
        this.locationname = locationname;
    }

    public Integer getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(Integer idlocation) {
        this.idlocation = idlocation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationpicture() {
        return locationpicture;
    }

    public void setLocationpicture(String locationpicture) {
        this.locationpicture = locationpicture;
    }
    
    public String getLocationpictureUrl() {
        String locationPicture = this.getLocationpicture();
        return locationPicture.substring(locationPicture.indexOf("/uploads"), -1);
    }

    public String getLocationname() {
        return locationname;
    }

    public void setLocationname(String locationname) {
        this.locationname = locationname;
    }

    @XmlTransient
    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlocation != null ? idlocation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.idlocation == null && other.idlocation != null) || (this.idlocation != null && !this.idlocation.equals(other.idlocation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getLocationname() + " ("+getAddress()+" "+getCity()+")";
    }
    
}

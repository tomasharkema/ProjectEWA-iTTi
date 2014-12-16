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
@Table(name = "car")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c"),
    @NamedQuery(name = "Car.findByRegistration", query = "SELECT c FROM Car c WHERE c.registration = :registration"),
    @NamedQuery(name = "Car.findByBrand", query = "SELECT c FROM Car c WHERE c.brand = :brand"),
    @NamedQuery(name = "Car.findByColor", query = "SELECT c FROM Car c WHERE c.color = :color"),
    @NamedQuery(name = "Car.findByType", query = "SELECT c FROM Car c WHERE c.type = :type"),
    @NamedQuery(name = "Car.findByNumberSeats", query = "SELECT c FROM Car c WHERE c.numberSeats = :numberSeats")})
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "registration")
    private Integer registration;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "brand")
    private String brand;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "color")
    private String color;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numberSeats")
    private int numberSeats;
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User userIduser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carRegistration")
    private Collection<UserHasEvent> userHasEventCollection;

    public Car() {
    }

    public Car(Integer registration) {
        this.registration = registration;
    }

    public Car(Integer registration, String brand, String color, String type, int numberSeats) {
        this.registration = registration;
        this.brand = brand;
        this.color = color;
        this.type = type;
        this.numberSeats = numberSeats;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberSeats() {
        return numberSeats;
    }

    public void setNumberSeats(int numberSeats) {
        this.numberSeats = numberSeats;
    }

    public User getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(User userIduser) {
        this.userIduser = userIduser;
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
        hash += (registration != null ? registration.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.registration == null && other.registration != null) || (this.registration != null && !this.registration.equals(other.registration))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Car[ registration=" + registration + " ]";
    }


    
}

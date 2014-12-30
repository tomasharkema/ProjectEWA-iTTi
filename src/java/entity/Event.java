/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.plaf.synth.SynthStyle;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import searching.TimeLine;

/**
 *
 * @author Repr
 */
@Entity
@Table(name = "event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),
    @NamedQuery(name = "Event.findByIdevennt", query = "SELECT e FROM Event e WHERE e.idevent = :idevent"),
    @NamedQuery(name = "Event.findByEvenDate", query = "SELECT e FROM Event e WHERE e.eventDate = :eventDate"),
    @NamedQuery(name = "Event.findByEventname", query = "SELECT e FROM Event e WHERE e.eventName = :eventName"),
    @NamedQuery(name = "Event.findAttending", query = "select u FROM User u JOIN UserHasEvent us ON u.iduser = us.user_iduser JOIN Event e ON us.location_has_event_event_idevent = e.idevent WHERE e.idevent = :idevent")})
public class Event implements Serializable, TimeLine {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevent")
    private Integer idevent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eventDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    @Lob
    @Size(max = 65535)
    @Column(name = "eventLogo")
    private String eventLogo;
    @Lob
    @Size(max = 65535)
    @Column(name = "eventWall")
    private String eventWall;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "eventName")
    private String eventName;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "fbevent")
    private String fbEvent;
    @JoinColumn(name = "locationid", referencedColumnName = "idlocation")
    @ManyToOne
    private Location locationid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<UserHasEvent> userHasEventList;

    public Event() {
    }

    public Event(Integer idevent) {
        this.idevent = idevent;
    }

    public Event(Integer idevent, Date evenDate, String eventname) {
        this.idevent = idevent;
        this.eventDate = evenDate;
        this.eventName = eventname;
    }

    public Integer getIdevent() {
        return idevent;
    }

    public void setIdevent(Integer idevent) {
        this.idevent = idevent;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date evenDate) {
        this.eventDate = evenDate;
    }

    public String getEventLogo() {
        return eventLogo;
    }

    public void setEventLogo(String eventLogo) {
        this.eventLogo = eventLogo;
    }

    public String getEventWall() {
        return eventWall;
    }

    public void setEventWall(String eventWall) {
        this.eventWall = eventWall;
    }
    
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }

    public void setLocationid(Location locationid) {
        this.locationid = locationid;
    }

    @XmlTransient
    public List<UserHasEvent> getUserHasEventList() {
        return userHasEventList;
    }

    public void setUserHasEventList(List<UserHasEvent> userHasEventList) {
        this.userHasEventList = userHasEventList;
    }

    public String getFbEvent() {
        return fbEvent;
    }

    public void setFbEvent(String fbEvent) {
        this.fbEvent = fbEvent;
    }

    public Location getLocationid() {
        return locationid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevent != null ? idevent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.idevent == null && other.idevent != null) || (this.idevent != null && !this.idevent.equals(other.idevent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Event[ idevent=" + idevent + " ]";
    }

    public ArrayList<Car> getAttendedCars() {

        ArrayList<Car> carList = new ArrayList<>();
        for (UserHasEvent userHasEvent : getUserHasEventList()) {
            if (userHasEvent.getCarId().getUserIduser().getIduser().equals(userHasEvent.getUser().getIduser())) {
                Car car = userHasEvent.getCarId();
                carList.add(car);
            }
        }

        return carList;
    }

    public ArrayList<User> getAttendees() {
        ArrayList<User> users = new ArrayList<>();
        for (UserHasEvent userHasEvent : getUserHasEventList()) {
            User user = userHasEvent.getUser();
            users.add(user);
        }
        System.out.println(getUserHasEventList());
        System.out.println(users);
        return users;
    }

    public ArrayList<User> getAttendingFriends(User user) {
        ArrayList<User> friendsAttending = new ArrayList<>();
        List<Friends> friends = user.getFriendsList();
        List<Friends> friends1 = user.getFriendsList1();
        for (User userAttends : getAttendees()) {
            for (Friends friend : friends) {
                if (userAttends.equals(friend.getUser()) && !user.equals(friend.getUser())) {
                    friendsAttending.add(friend.getUser());
                } else if (userAttends.equals(friend.getUser1()) && !user.equals(friend.getUser1())) {
                    friendsAttending.add(friend.getUser1());
                }
            }
            for (Friends friend : friends1) {
                if (userAttends.equals(friend.getUser()) && !user.equals(friend.getUser())) {
                    friendsAttending.add(friend.getUser());
                } else if (userAttends.equals(friend.getUser1()) && !user.equals(friend.getUser1())) {
                    friendsAttending.add(friend.getUser1());
                }
            }
        }
        return friendsAttending;
    }
    
    @Override
    public String getName() {
        return this.getEventName();
    }

    @Override
    public String getPicture() {
        return this.getEventLogo();
    }

    @Override
    public int getId() {
     return this.getIdevent();
    }
}

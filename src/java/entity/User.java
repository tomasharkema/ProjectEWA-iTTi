/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import searching.TimeLine;

/**
 *
 * @author tomas
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIduser", query = "SELECT u FROM User u WHERE u.iduser = :iduser"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByTown", query = "SELECT u FROM User u WHERE u.town = :town"),
    @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender"),
    @NamedQuery(name = "User.findByAddress", query = "SELECT u FROM User u WHERE u.address = :address"),
    @NamedQuery(name = "User.findByZipcode", query = "SELECT u FROM User u WHERE u.zipcode = :zipcode"),
    @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByFbid", query = "SELECT u FROM User u WHERE u.fbid = :fbid"),
    @NamedQuery(name = "User.findByAdmin", query = "SELECT u FROM User u WHERE u.admin = :admin"),
    @NamedQuery(name = "User.findFriendsbyNameASC", query = "Select u FROM User u JOIN Friends f ON f.user_iduser1 = u.iduser WHERE f.user = :iduser ORDER BY u.name ASC"),
    @NamedQuery(name = "User.findFriendsbyDateASC", query = "Select u FROM User u JOIN Friends f ON f.user_iduser1 = u.iduser WHERE f.user = :iduser ORDER BY f.date ASC"),
    @NamedQuery(name = "User.findAttendingEvents", query = "SELECT e from Event e JOIN UserHasEvent u on e.idevennt = u.location_has_event_location_idlocation Join User us ON u.user_iduser = us.iduser WHERE us.iduser = :iduser"),
    @NamedQuery(name = "User.findCars", query = "SELECT c FROM Car c JOIN User u on u.iduser = c.user_iduser where u.iduser = :iduser"),
    @NamedQuery(name = "User.findFriendEvents", query = "Select e FROM Event e JOIN UserHasEvent uhe ON e.idevennt = uhe.location_has_event_event_idevennt JOIN User u ON uhe.user_iduser = u.iduser JOIN Friends f ON u.iduser = f.user_iduser JOIN User yourFriend ON f.user_iduser1 = u.iduser WHERE yourFriend.iduser = :iduser"),
    @NamedQuery(name = "User.findAttendingFriends", query = "SELECT u FROM User u JOIN Friends f ON f.user_iduser = u.iduser JOIN UserHasEvent uhe ON uhe.user_iduser = u.iduser WHERE f.user1 = :iduser  ORDER BY uhe.date ASC")})

public class User implements Serializable, TimeLine, PermaLinkable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Integer iduser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "town")
    private String town;
    @Lob
    @Size(max = 65535)
    @Column(name = "userAvatar")
    private String userAvatar;
    @Size(max = 1)
    @Column(name = "gender")
    private String gender;
    @Size(max = 45)
    @Column(name = "address")
    private String address;
    @Size(max = 6)
    @Column(name = "zipcode")
    private String zipcode;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 11)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Column(name = "fbid")
    private BigInteger fbid;
    @Column(name = "admin")
    private Boolean admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userIduser", orphanRemoval=true)
    private List<Car> carList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval=true)
    private List<Friends> friendsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user1", orphanRemoval=true)
    private List<Friends> friendsList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval=true)
    private List<UserHasEvent> userHasEventList;

    public User() {
    }

    public User(Integer iduser) {
        this.iduser = iduser;
    }

    public User(Integer iduser, String name, String town, String email) {
        this.iduser = iduser;
        this.name = name;
        this.town = town;
        this.email = email;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getFbid() {
        return fbid;
    }

    public void setFbid(BigInteger fbid) {
        this.fbid = fbid;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @XmlTransient
    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    @XmlTransient
    public List<Friends> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<Friends> friendsList) {
        this.friendsList = friendsList;
    }

    public List<Friends> getFriendsList1() {
        return friendsList1;
    }

    public void setFriendsList1(List<Friends> friendsList1) {
        this.friendsList1 = friendsList1;
    }

    @XmlTransient
    public List<UserHasEvent> getUserHasEventList() {
        return userHasEventList;
    }

    public void setUserHasEventList(List<UserHasEvent> userHasEventList) {
        this.userHasEventList = userHasEventList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduser != null ? iduser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.iduser == null && other.iduser != null) || (this.iduser != null && !this.iduser.equals(other.iduser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.User[ iduser=" + iduser + " ]";
    }

    @Override
    public String getPicture() {
        return this.getUserAvatar();
    }

    @Override
    public int getId() {
    return this.getIduser();
    }

    public UserHasEvent isAttendingEvent(int eventId) {
        List<UserHasEvent> userHasEventList = getUserHasEventList();
        UserHasEvent isAttending = null;
        
        if (userHasEventList.size() == 0) return null;
        
        for(UserHasEvent ev : userHasEventList) {
            // For some reason, getEvent() returns null, but it actually exists. Fallback on manual check.
            if (ev.getEvent() == null) {
                if (ev.getUserHasEventPK().getEventIdevent() == eventId) {
                    isAttending = ev;
                }
            } else if (ev.getEvent().getIdevent() == eventId) {
                isAttending = ev;
            }
        }
        return isAttending;
    }

    public boolean isDriving(Event ev) {
        boolean ret = false;
        for (UserHasEvent e : userHasEventList) {
            for (Car c : carList) {
                if (c.equals(e.getCarId())) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    public List<Friend> getFriends() {
        List<Friend> list = new ArrayList<>();
        for (Friends friends : friendsList) {
            if (!friends.getUser().equals(this)) {
                list.add(new Friend(friends.getUser(), friends.getApproved() ? Friends.FriendRelation.Friends : Friends.FriendRelation.NotConfirmed, friends));
            } else if (!friends.getUser1().equals(this)) {
                list.add(new Friend(friends.getUser1(), friends.getApproved() ? Friends.FriendRelation.Friends : Friends.FriendRelation.Pending, friends));
            }
        }
        for (Friends friends : friendsList1) {
            if (!friends.getUser().equals(this)) {
                list.add(new Friend(friends.getUser(), (friends.getApproved() ? Friends.FriendRelation.Friends : Friends.FriendRelation.NotConfirmed), friends));
            } else if (!friends.getUser1().equals(this)) {
                list.add(new Friend(friends.getUser1(), friends.getApproved() ? Friends.FriendRelation.Friends : Friends.FriendRelation.Pending, friends));
            }
        }

        return list;
    }

    public List<Friend> getFriendsApproved() {
        Predicate<Friend> friendPredicate = new Predicate<Friend>() {
            @Override
            public boolean evaluate(Friend friend) {
                return friend.getRelation() == Friends.FriendRelation.Friends;
            }
        };

        List<Friend> list = getFriends();
        CollectionUtils.filter(list, friendPredicate);
        return list;
    }

    public Friend getFriendRelation(User friend) {
        Friends ret = null;
        Friends.FriendRelation relation = Friends.FriendRelation.NoFriends;
        for (Friends fr : getFriendsList()) {
            if (fr.getUser().equals(friend)) {
                ret = fr;
                relation = fr.getApproved() ? Friends.FriendRelation.Friends : Friends.FriendRelation.NotConfirmed;
            }
            if (fr.getUser1().equals(friend)) {
                ret = fr;
                relation = fr.getApproved() ? Friends.FriendRelation.Friends : Friends.FriendRelation.Pending;
            }
        }
        for (Friends fr : getFriendsList1()) {
            if (fr.getUser().equals(friend)) {
                ret = fr;
                relation = fr.getApproved() ? Friends.FriendRelation.Friends : Friends.FriendRelation.NotConfirmed;
            }
            if (fr.getUser1().equals(friend)) {
                ret = fr;
                relation = fr.getApproved() ? Friends.FriendRelation.Friends : Friends.FriendRelation.Pending;
            }
        }

        // oh, I wish for tuples.
        if (ret != null) {
            return new Friend(friend, relation, ret);
        }
        return null;
    }

    public Friends.FriendRelation getRelation(User friend) {
        Friend f = friend.getFriendRelation(friend);
        if (f == null) {
            return Friends.FriendRelation.NoFriends;
        }
        return f.getRelation();
    }

    public List<UserHasEvent> getDrivingEvents() {
        List<UserHasEvent> list = new ArrayList<>(getUserHasEventList());

        CollectionUtils.filter(list, new Predicate<UserHasEvent>() {
            @Override
            public boolean evaluate(UserHasEvent userHasEvent) {
                return isDriving(userHasEvent.getEvent());
            }
        });

        return list;
    }

    @Override
    public String getPermaLink(){
        return "/user?userId="+getIduser();
    }
}

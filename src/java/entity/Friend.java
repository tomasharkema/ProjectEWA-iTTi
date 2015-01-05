package entity;

import java.util.Date;

/**
 * Created by tomas on 31-12-14.
 */
public class Friend {

    public User user;
    public Friends.FriendRelation relation;
    public Friends chain;

    public Friend(User user, Friends.FriendRelation relation, Friends chain) {
        this.user = user;
        this.relation = relation;
        this.chain = chain;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Friends.FriendRelation getRelation() {
        return relation;
    }

    public void setRelation(Friends.FriendRelation relation) {
        this.relation = relation;
    }

    public Friends getChain() {
        return chain;
    }

    public void setChain(Friends chain) {
        this.chain = chain;
    }
}

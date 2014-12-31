package entity;

import java.util.Date;

/**
 * Created by tomas on 31-12-14.
 */
public class Friend {

    public Date since;
    public User user;

    public Friend(User user, Date since) {
        this.user = user;
        this.since = since;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

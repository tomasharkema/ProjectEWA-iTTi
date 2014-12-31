package entity;

import java.util.Date;

/**
 * Created by tomas on 31-12-14.
 */
public class Friend extends User {

    public Friend(User u) {
        super(u);
    }

    public Date since;

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }
}

package searching;

import java.util.Date;

/**
 * Created by tomas on 10-01-15.
 */
public interface Notification {

    public String getTitle();
    public Date getFiredDate();
    public String getAvatar();
    public String getDesc();
    public boolean shouldShow();

    public Object getPrimaryObject();

}

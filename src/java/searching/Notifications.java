package searching;

import entity.User;
import entity.UserHasEvent;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomas on 10-01-15.
 */
public class Notifications {

    static public List<Notification> getNotifications(User user) {
        List<Notification> notificationList = new ArrayList<>();
        List<UserHasEvent> userHasEventList = user.getUserHasEventList();

        notificationList.addAll(CollectionUtils.collect(userHasEventList, new Transformer<UserHasEvent, Notification>() {
            @Override
            public Notification transform(UserHasEvent userHasEvent) {
                return userHasEvent;
            }
        }));

        List<Notification> userHasEventNot = ListUtils.castList(userHasEventList);
        notificationList.addAll(userHasEventNot);



        return  notificationList;
    }

}

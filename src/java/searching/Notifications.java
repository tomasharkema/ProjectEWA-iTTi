package searching;

import entity.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import session.UserFacade;
import utils.ListUtils;

import javax.ejb.EJB;
import java.util.*;

/**
 * Created by tomas on 10-01-15.
 */
public class Notifications {
    static public NotificationList getNotifications(User user) {
        List<Notification> notificationList = new ArrayList<>();

        notificationList.addAll(getPassagiersNotifications(user));
        notificationList.addAll(getFriendsNotifications(user));

        CollectionUtils.filter(notificationList, Notification::shouldShow);

        Collections.sort(notificationList, (o1, o2) -> o2.getFiredDate().after(o1.getFiredDate()) ? 1 : -1);

        return new NotificationList(notificationList);
    }

    static private List<Notification> getPassagiersNotifications(User user) {
        List<UserHasEvent> userDrivingEvents = user.getDrivingEvents();
        List<List<UserHasEvent>> userPassagiersCollection = new ArrayList<>();
        // TODO: make it a flatMap
        CollectionUtils.collect(userDrivingEvents, (uhe) -> {
            Car car = uhe.getCarId();
            Event event = uhe.getEvent();

            List<UserHasEvent> carEvents = new ArrayList<>(car.getUserHasEventList());

            CollectionUtils.filter(carEvents, (ehc) -> !ehc.getUser().equals(uhe.getUser()));
            CollectionUtils.filter(carEvents, (ehc) -> ehc.getEvent().equals(event));

            return carEvents;
        }, userPassagiersCollection);
        return ListUtils.castList(ListUtils.flatten(userPassagiersCollection));
    }

    static private List<Notification> getFriendsNotifications(User user) {
        List<Friend> friends = user.getFriends();
        return ListUtils.castList(friends);
    }
}

;
package searching;

import entity.PermaLinkable;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class NotificationList {
    private List<Notification> list;

    public NotificationList(List<Notification> list) {
        this.list = list;
    }

    public List<Notification> getList() {
        return list;
    }

    public JSONArray getArray() {
        List<JSONObject> listJSON = new ArrayList<>();
        CollectionUtils.collect(list, new Transformer<Notification, JSONObject>() {
            @Override
            public JSONObject transform(Notification notification) {
                JSONObject obj = new JSONObject();
                obj.put("title", notification.getTitle());
                obj.put("desc", notification.getDesc());
                obj.put("time", notification.getFiredDate().getTime());
                obj.put("avatar", notification.getImage());

                if (notification.getPrimaryObject() instanceof PermaLinkable) {
                    obj.put("link", ((PermaLinkable)notification.getPrimaryObject()).getPermaLink());
                }
                return obj;
            }
        }, listJSON);

        return ListUtils.listToArray(listJSON);
    }

}

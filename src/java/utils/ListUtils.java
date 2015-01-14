package utils;

import com.oracle.javafx.jmx.json.JSONException;
import entity.User;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import searching.JSONSerializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tomas on 10-01-15.
 */
public class ListUtils {
    static public <B, A extends B> List<B> castList(List<A> list) {
        return (List<B>)CollectionUtils.collect(list, new Transformer<A, B>() {
            @Override
            public B transform(A a) {
                return a;
            }
        });
    }

    static public <A> List<A> flatten(List<List<A>> list) {
        List <A> ret = new ArrayList<>();

        for (List<A> l : list) {
            for (A obj : l) {
                ret.add(obj);
            }
        }

        return ret;
    }

    static public JSONArray listToJSONArray(List<JSONObject> list) {
        JSONArray ret = new JSONArray();
        for (JSONObject obj : list) {
            ret.add(obj);
        }
        return ret;
    }

    static public <T extends JSONSerializable> JSONArray listToArray(List<T> list) {
        return list.stream()
                .map(T::getJSONObject)
                .reduce(new JSONArray(),
                        (jsonArray, jsonObject) -> {
                    jsonArray.add(jsonObject);
                    return jsonArray;
                }, null);
    }

}


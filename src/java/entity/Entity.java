package entity;

import org.json.simple.JSONObject;

import java.lang.reflect.Field;

/**
 * Created by tomas on 12-01-15.
 */
public class Entity {

    public Field[] getJSONObjectParsed() {
        Field[] fields = this.getClass().getDeclaredFields();

        return fields;
    }

}

package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.List;

public abstract class ToJSON implements toJsonInterface {

    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String key = field.getName().toLowerCase();
                Object value = field.get(this);

                if (value instanceof List<?>) {
                    JSONArray arr = new JSONArray();
                    for (Object item : (List<?>) value) {
                        if (item instanceof toJsonInterface) {
                            arr.put(((toJsonInterface) item).toJSON());
                        } else {
                            arr.put(item);
                        }
                    }
                    obj.put(key, arr);
                } else if (value instanceof toJsonInterface) {
                    obj.put(key, ((toJsonInterface) value).toJSON());
                } else {
                    obj.put(key, value);
                }
            } catch (Exception e) {
                System.err.println("Fehler beim Serialisieren: " + e.getMessage());
            }
        }

        return obj;
    }
}

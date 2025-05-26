package service;


import model.JSONConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public class JSONservice {
    private final String jsonFilePath;
    private JSONObject jsonData;

    public JSONservice() {
        this.jsonFilePath = JSONConfig.JSON_FILE_PATH;
        loadJSON();
        initSchoolCounter();
    }

    /// Lädt die JSON Datei intern und ermöglicht Zugriff mit weiterem Datenhandling
    public void loadJSON(){
        try(InputStream is = new FileInputStream(jsonFilePath)){
            JSONTokener tokener = new JSONTokener(is);
            jsonData = new JSONObject(tokener);
        } catch (Exception e) {
            System.err.println("JSON konnte nicht geladen werden" + e.getMessage());
        }
    }

    /// Gibt das aktuell geladenen JSON Object zurück.
    public JSONObject getJSONData(){
        return jsonData;
    }

    /// Speichert die JSON Datei
    public void saveJSON(){
        try(FileWriter file = new FileWriter(jsonFilePath)){
            file.write(jsonData.toString(4));
        }catch (Exception e){
            System.err.println("JSON konnte nicht gespeichert werden!"+e.getMessage());
        }
    }

    /// Methode, welche vom ToJsonInterface benutzt wird, um die Daten mit Keys in der JSON zu speichern.
    /// AI.
    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        Field[] fields = this.getClass().getDeclaredFields();

        for(Field field : fields){
            field.setAccessible(true);
            try{
                String jsonKey = field.getName().toLowerCase();
                Object value = field.get(this);

                if(value instanceof List<?>){
                    JSONArray jsonArray = new JSONArray();
                    for(Object item : (List<?>)value){
                        if(item != null){
                            jsonArray.put(item.getClass().getMethod("toJSON").invoke(item));
                        }else{
                            jsonArray.put((Object) null);
                        }
                    }
                    obj.put(jsonKey, jsonArray);
                }else{
                    obj.put(jsonKey, value);
                }
            }catch (Exception e){
                System.err.println("JSON konnte nicht umgewandelt werden!"+e.getMessage());
            }
        }
        return obj;
    }

    /// Zählt die Schuleinträge hoch und initiert so den Counter auf den richtigen Wert.
    private void initSchoolCounter(){
        int max = 0;
        Iterator<String> keys = jsonData.keys();
        while(keys.hasNext()){
            String key = keys.next();
            if(key.startsWith("school")){
                try{
                    int number = Integer.parseInt(key.replace("school",""));
                    if(number > max){
                        max = number;
                    }
                } catch (NumberFormatException ignored){}
            }
        }
        JSONConfig.initCounter(max + 1);
    }

}

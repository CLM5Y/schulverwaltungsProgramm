package model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JSONConfig {

    public static  String JSON_FILE_PATH = "src/resources/school.json";
    private static int COUNTER = 1; // zählt hoch
    private String name;


    public JSONConfig(String prename) {
        this.name = prename + COUNTER;
        COUNTER++;
    }

    public static void initCounter(int value) {
        COUNTER = value;
    }
    //Wieso auch immer Lombok das nicht bereitstellt.
    public static String getJsonFilePath(){
        return JSON_FILE_PATH;
    }

    public enum JSONKeys {
        ROOMS("rooms"),
        TEACHER("teachers"),
        CLASSES("classes"),
        SCHOOL("school"),
        STUDENTS("students"),
        NAME("name"),
        SCHOOLNAME("schoolname"),
        CLASS("class"),
        ROOMNUMBER("roomnumber"),
        ROOMAREA("roomarea"),
        STUDENTCAPACITY("studentcapacity"),
        FIRSTNAME("firstname"),
        LASTNAME("lastname");

        private final String key;

        JSONKeys(String key) {
            this.key = key;
        }

        public String key() {
            return key;
        }
    }

}

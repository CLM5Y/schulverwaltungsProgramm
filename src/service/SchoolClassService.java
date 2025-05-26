package service;

import model.JSONConfig;
import model.School;
import model.SchoolClass;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class SchoolClassService {
    static JSONservice service = new JSONservice();
    static Scanner sc = new Scanner(System.in);

    public static void createSchoolClass(){
        JSONObject data = service.getJSONData();
        String eingabe = "";

        System.out.println("Sie möchten also Klassen erstellen...");
        System.out.println("Sie können das Erstellen einer Klasse mit 'exit' beenden...");
        Sleep.sleep(3000);

        String schoolKey = SchoolService.showSchools();

        do {
            System.out.println("Bitte geben Sie nun den Namen der Klasse ein:");
            String name = sc.nextLine();

            int studentCapacity;
            while(true){
                System.out.println("Bitte geben Sie nun die maximale Anzahl von Schülern der Klasse ein: ");
                try{
                    studentCapacity = Integer.parseInt(sc.nextLine());
                    break;
                }catch (NumberFormatException e){
                    System.out.println("Bitte geben Sie eine gültige Zahl ein!");
                }
            }
            System.out.println("Okay! Ich habe alle benötigten Daten...");
            Sleep.sleep(500);
            System.out.println("Erstelle Klasse...");
            Sleep.sleep(500);
            SchoolClass schoolclass = new SchoolClass(name, studentCapacity);

            System.out.println("Speichere nun die Klasse...");
            JSONObject school = data.getJSONObject(schoolKey);

            JSONArray klassen = school.optJSONArray(JSONConfig.JSONKeys.CLASSES.key());
            if(klassen == null){
                klassen = new JSONArray();
                school.put(JSONConfig.JSONKeys.CLASSES.key(), klassen);
            }
            klassen.put(schoolclass.toJSON());
            data.put(schoolKey, school);

            service.saveJSON();
            Sleep.sleep(500);
            System.out.println("Erfolgreich gespeichert!");
            System.out.println("Zum beenden schreiben Sie 'exit', zum Fortfahren drücken Sie bitte ENTER");
            System.out.println("Weitere Klassen erstellen?");

            eingabe = sc.nextLine();
        }while (!eingabe.equalsIgnoreCase("exit"));
    }

    public static String showClasses(String schoolKey) {
        JSONObject data = service.getJSONData();
        JSONObject school = data.optJSONObject(schoolKey);
        List<String> classNames = new ArrayList<>();

        if (school == null) {
            System.out.println("Ungültiger Schulschlüssel.");
            return null;
        }

        JSONArray classesArray = school.optJSONArray(JSONConfig.JSONKeys.CLASSES.key());
        if (classesArray == null || classesArray.isEmpty()) {
            System.out.println("Diese Schule hat noch keine Klassen.");
            return null;
        }

        System.out.println("Folgende Klassen sind an der Schule '" + school.optString(JSONConfig.JSONKeys.SCHOOLNAME.key()) + "' verfügbar:");

        for (int i = 0; i < classesArray.length(); i++) {
            JSONObject classObj = classesArray.getJSONObject(i);
            String className = classObj.optString(JSONConfig.JSONKeys.NAME.key());
            if (className != null && !className.isEmpty()) {
                System.out.println(className);
                classNames.add(className);
            }
        }

        String wantedClass;
        do {
            System.out.println("Bitte geben Sie nun die gewünschte Klasse mit dem Namen an (oder 'exit' zum Abbrechen)...");
            wantedClass = sc.nextLine();
            if (wantedClass.equalsIgnoreCase("exit")) return null;

            for (int i = 0; i < classesArray.length(); i++) {
                JSONObject classObj = classesArray.getJSONObject(i);
                if (wantedClass.equalsIgnoreCase(classObj.optString(JSONConfig.JSONKeys.NAME.key()))) {
                    System.out.println("Gut, " + wantedClass + " also!");
                    return wantedClass;
                }
            }

            System.out.println("Klasse nicht gefunden. Bitte erneut versuchen.");
        } while (true);
    }

}

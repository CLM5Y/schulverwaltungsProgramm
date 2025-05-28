package service;

import model.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/// Besitzt alle Funktionen zum Anlegen/Bearbeiten/Hinzufügen der Schule
public class SchoolService {
    static JSONservice service = JSONservice.getInstance();
    static Scanner sc = new Scanner(System.in);

    /// Findet eine Schule anhand ihres Namens aus der JSON Datei.
    public static String findSchoolKeyByName(String schoolName){
        JSONObject data = service.getJSONData();
        Iterator<String> keys = data.keys();

        while(keys.hasNext()){
            String key = keys.next();
            JSONObject school = data.getJSONObject(key);
            if (school != null && schoolName.equalsIgnoreCase(school.optString("schoolname"))){
                return key;
            }
        }
        return null;
    }

    /// Erstellt eine Rückgabe Liste aller Schulen aus der JSON
    public static List<String> getAllSchoolNames(){
        List<String> schoolNames = new ArrayList<>();
        JSONObject data = service.getJSONData();

        Iterator<String> keys = data.keys();
        while(keys.hasNext()){
            String key = keys.next();
            JSONObject school = data.getJSONObject(key);
            if(school != null){
                String name = school.optString("schoolname");
                if(!name.isEmpty()){
                    schoolNames.add(name);
                }else{
                    System.out.println("schoolname nicht gefunden in JSON.");
                }
            }
        }
        return schoolNames;
    }
    public static String showSchools(){
        String wantedSchoolName;
        String schoolKey;
        List<String> schoolnames;

        System.out.println("Sie erhalten nun eine Übersicht der Schulen...");

        schoolnames = SchoolService.getAllSchoolNames();
        if(!schoolnames.isEmpty()){
            for(String schoolname : schoolnames){
                if(schoolname != null && !schoolname.isEmpty()){
                    System.out.println(schoolname);
                }
            }
        }else{
            System.out.println("Es sind noch keine Schulen vorhanden!");
            return null;
        }
        do {
            System.out.println("Bitte geben Sie nun die gewünschte Schule mit Namen an (oder 'exit' zum Abbrechen)...");
            wantedSchoolName = sc.nextLine();
            if (wantedSchoolName.equalsIgnoreCase("exit")) return null;

            schoolKey = SchoolService.findSchoolKeyByName(wantedSchoolName);
            if (schoolKey == null) {
                System.out.println("Schule mit dem Namen '" + wantedSchoolName + "' existiert nicht. Bitte erneut versuchen.");
            }
        } while (schoolKey == null);

        System.out.println("Gut, " + wantedSchoolName + " also!");
        return schoolKey;
    }

    /// Erstellt eine Schule
    public static void createSchool(){
        service.loadJSON();
        JSONObject data = service.getJSONData();

        System.out.println("Wie ist der Name der Schule?");
        String name = sc.nextLine();
        System.out.println("Wie viele Klassen kann die Schule fassen?");
        int maxCapacityClass = Integer.parseInt(sc.nextLine());
        School school = new School(name, maxCapacityClass);

        JSONConfig schoolConfig = new JSONConfig(JSONConfig.JSONKeys.SCHOOL.key());
        String schoolId = schoolConfig.getName();

        JSONObject newSchoolJSON = school.toJSON();

        data.put(schoolId, newSchoolJSON);

        service.saveJSON();

        System.out.println("Schule erfolgreich erstellt!");

    }
}

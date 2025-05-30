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
    static JSONservice service = JSONservice.getInstance();
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
    public static void showAllClasses() {
        JSONObject data = service.getJSONData();
        String schoolKey = SchoolService.showSchools();
        JSONObject school = data.getJSONObject(schoolKey);

        System.out.println("Ich zeige Ihnen nun alle Klassen der Schule: " + school.getString(JSONConfig.JSONKeys.SCHOOLNAME.key()));

        JSONArray classes = school.optJSONArray(JSONConfig.JSONKeys.CLASSES.key());
        if (classes == null || classes.length() == 0) {
            System.out.println("Keine Klassen gefunden.");
            Sleep.sleep(1000);
            return;
        }

        for (int i = 0; i < classes.length(); i++) {
            JSONObject klasse = classes.getJSONObject(i);

            int lehrerAnzahl = 0;
            if (klasse.has(JSONConfig.JSONKeys.TEACHERS.key()) && !klasse.isNull(JSONConfig.JSONKeys.TEACHERS.key())) {
                lehrerAnzahl = klasse.getJSONArray(JSONConfig.JSONKeys.TEACHERS.key()).length();
            }

            int schülerAnzahl = 0;
            if (klasse.has(JSONConfig.JSONKeys.STUDENTS.key()) && !klasse.isNull(JSONConfig.JSONKeys.STUDENTS.key())) {
                schülerAnzahl = klasse.getJSONArray(JSONConfig.JSONKeys.STUDENTS.key()).length();
            }

            String klassenName = klasse.getString(JSONConfig.JSONKeys.NAME.key());
            int maxSchüler = klasse.optInt(JSONConfig.JSONKeys.STUDENTCAPACITY.key(), 0);

            System.out.print("Klasse: " + klassenName + " ");
            System.out.print("Maximale Schüler: " + schülerAnzahl + "/" + maxSchüler + " ");

            if (lehrerAnzahl > 0) {
                System.out.print("Lehreranzahl: " + lehrerAnzahl);
            }

            System.out.println();
        }

        Sleep.sleep(1500);
    }

    public static void deleteClass(){

        System.out.println("Gut sie möchten also eine Klasse komplett löschen...");
        Sleep.sleep(500);

        JSONObject data = service.getJSONData();
        String schoolKey = SchoolService.showSchools();
        JSONObject school = data.getJSONObject(schoolKey);
        JSONArray classes = school.optJSONArray(JSONConfig.JSONKeys.CLASSES.key());
        String classNameToDelete = SchoolClassService.showClasses(schoolKey);


        int indexToDelete = -1;
        for (int i = 0; i < classes.length(); i++) {
            JSONObject schoolClass = classes.getJSONObject(i);
            if (schoolClass.getString(JSONConfig.JSONKeys.NAME.key()).equalsIgnoreCase(classNameToDelete)) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            System.out.println("Klasse nicht gefunden.");
            return;
        }

        System.out.println("Sind Sie sicher, dass Sie die Klasse '" + classNameToDelete + "' inklusive Lehrer und Schüler löschen möchten? (ja/nein)");
        String confirmation = sc.nextLine();
        if (!confirmation.equalsIgnoreCase("ja")) {
            System.out.println("Löschvorgang abgebrochen.");
            return;
        }
        classes.remove(indexToDelete);
        System.out.println("Klasse erfolgreich gelöscht!");
        service.saveJSON();

    }

}

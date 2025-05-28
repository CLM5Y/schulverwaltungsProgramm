package service;

import model.JSONConfig;
import model.Subject;
import model.Teacher;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TeacherService {
    static JSONservice service = new JSONservice();
    static Scanner sc = new Scanner(System.in);


    public static void createTeacher() {
        JSONObject data = service.getJSONData();
        List<Subject> subjects = new ArrayList<>();

        String schoolKey = SchoolService.showSchools();
        if (schoolKey == null) return;

        System.out.println("Wählen Sie nun eine Klasse aus...");
        String className = SchoolClassService.showClasses(schoolKey);
        if (className == null) return;

        System.out.println("Sie fügen nun einen Lehrer der Klasse '" + className + "' hinzu...");

        System.out.println("Bitte geben Sie nun den Vornamen des Lehrers ein:");
        String vorname = sc.nextLine();
        System.out.println("Bitte geben Sie nun den Nachnamen ein:");
        String nachname = sc.nextLine();
        System.out.println("Bitte geben Sie nun das Alter des Lehrers ein:");
        int alter = Integer.parseInt(sc.nextLine());

        System.out.println("Bitte geben Sie nun nacheinander die Fächer des Lehrers ein.");
        System.out.println("Zum Beenden der Eingabe benutzen Sie bitte den Begriff 'stop'");
        while (true) {
            String subjecteingabe = sc.nextLine();
            if (subjecteingabe.equalsIgnoreCase("stop")) break;
            subjects.add(new Subject(subjecteingabe));
        }

        System.out.println("Erstelle Lehrer...");
        Sleep.sleep(500);
        Teacher teacher = new Teacher(vorname, nachname, alter, subjects);

        System.out.println("Speichere nun den Lehrer...");

        JSONObject school = data.getJSONObject(schoolKey);

        JSONArray classesArray = school.optJSONArray("classes");
        if (classesArray != null) {
            for (int i = 0; i < classesArray.length(); i++) {
                JSONObject klasse = classesArray.getJSONObject(i);
                if (klasse.getString("name").equals(className)) {
                    JSONArray teacherArray = klasse.optJSONArray("teacher");
                    if (teacherArray == null) {
                        teacherArray = new JSONArray();
                        klasse.put("teacher", teacherArray);
                    }
                    teacherArray.put(teacher.toJSON());
                    break;
                }
            }
        } else {
            System.out.println("Keine Klassen in der Schule gefunden!");
        }


        data.put(schoolKey, school);
        service.saveJSON();

        Sleep.sleep(500);
        System.out.println("Erfolgreich gespeichert!");
    }


    public static void removeTeacherFromClass() {
        JSONObject data = service.getJSONData();
        String schoolKey = SchoolService.showSchools();

        JSONObject school = data.getJSONObject(schoolKey);
        JSONArray classes = school.optJSONArray(JSONConfig.JSONKeys.CLASSES.key());

        if (classes == null || classes.isEmpty()) {
            System.out.println("Keine Klassen vorhanden.");
            return;
        }

        System.out.println("Folgende Klassen stehen zur Auswahl:");
        for (int i = 0; i < classes.length(); i++) {
            JSONObject schoolClass = classes.getJSONObject(i);
            System.out.println("Klasse: " + schoolClass.getString(JSONConfig.JSONKeys.NAME.key()));
        }

        System.out.println("Bitte geben Sie den Namen der Klasse ein:");
        String classInput = sc.nextLine();

        JSONObject selectedClass = null;
        for (int i = 0; i < classes.length(); i++) {
            JSONObject schoolClass = classes.getJSONObject(i);
            if (schoolClass.getString(JSONConfig.JSONKeys.NAME.key()).equalsIgnoreCase(classInput)) {
                selectedClass = schoolClass;
                break;
            }
        }

        if (selectedClass == null) {
            System.out.println("Klasse nicht gefunden.");
            return;
        }

        JSONArray teachers = selectedClass.optJSONArray(JSONConfig.JSONKeys.TEACHER.key());
        if (teachers == null || teachers.isEmpty()) {
            System.out.println("Keine Lehrer in dieser Klasse vorhanden.");
            return;
        }

        System.out.println("Lehrer in der Klasse:");
        for (int i = 0; i < teachers.length(); i++) {
            JSONObject teacher = teachers.getJSONObject(i);
            System.out.println("Lehrer: " + teacher.getString(JSONConfig.JSONKeys.FIRSTNAME.key()) + " " + teacher.getString(JSONConfig.JSONKeys.LASTNAME.key()));
        }

        System.out.println("Bitte geben Sie den Vornamen des Lehrers ein:");
        String firstName = sc.nextLine().strip();
        System.out.println("Bitte geben Sie den Nachnamen des Lehrers ein:");
        String lastName = sc.nextLine().strip();

        int indexToRemove = -1;
        for (int i = 0; i < teachers.length(); i++) {
            JSONObject teacher = teachers.getJSONObject(i);
            if (teacher.getString(JSONConfig.JSONKeys.FIRSTNAME.key()).equalsIgnoreCase(firstName) &&
                    teacher.getString(JSONConfig.JSONKeys.LASTNAME.key()).equalsIgnoreCase(lastName)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            System.out.println("Lehrer nicht gefunden.");
            return;
        }

        System.out.println("Möchten Sie " + firstName + " " + lastName + " wirklich aus der Klasse entfernen? (ja/nein)");
        String confirmation = sc.nextLine();
        if (confirmation.equalsIgnoreCase("ja")) {
            teachers.remove(indexToRemove);
            System.out.println("Lehrer wurde entfernt.");
        } else {
            System.out.println("Vorgang abgebrochen.");
        }

        service.saveJSON();
        Sleep.sleep(1500);
    }

}

package service;

import model.JSONConfig;
import model.Student;
import model.Subject;
import model.Teacher;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    static JSONservice service = new JSONservice();
    static Scanner sc = new Scanner(System.in);

    public static void createStudent() {
        JSONObject data = service.getJSONData();
        List<Subject> subjects = new ArrayList<>();

        String schoolKey = SchoolService.showSchools();
        if (schoolKey == null) return;

        System.out.println("Wählen Sie nun eine Klasse aus...");
        String className = SchoolClassService.showClasses(schoolKey);
        if (className == null) return;

        System.out.println("Sie fügen nun einen Schüler der Klasse '" + className + "' hinzu...");

        System.out.println("Bitte geben Sie nun den Vornamen des Schülers ein:");
        String vorname = sc.nextLine();
        System.out.println("Bitte geben Sie nun den Nachnamen ein:");
        String nachname = sc.nextLine();
        System.out.println("Bitte geben Sie nun das Alter des Schülers ein:");
        int alter = Integer.parseInt(sc.nextLine());

        System.out.println("Bitte geben Sie nun nacheinander die Lieblingsfächer des Schülers ein.");
        System.out.println("Zum Beenden der Eingabe benutzen Sie bitte den Begriff 'stop'");
        while (true) {
            String subjecteingabe = sc.nextLine();
            if (subjecteingabe.equalsIgnoreCase("stop")) break;
            subjects.add(new Subject(subjecteingabe));
        }

        System.out.println("Erstelle Schüler...");
        Sleep.sleep(500);
        Student student = new Student(vorname, nachname, alter, subjects);

        System.out.println("Speichere nun den Schüler...");
        JSONObject school = data.getJSONObject(schoolKey);

        JSONArray studentArray = school.optJSONArray(JSONConfig.JSONKeys.STUDENTS.key());
        if (studentArray == null) {
            studentArray = new JSONArray();
            school.put(JSONConfig.JSONKeys.STUDENTS.key(), studentArray);
        }
        studentArray.put(student.toJSON());
        data.put(schoolKey, school);
        service.saveJSON();

        Sleep.sleep(500);
        System.out.println("Erfolgreich gespeichert!");
    }

    public static void removeStudentFromClass() {
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

        JSONArray students = selectedClass.optJSONArray(JSONConfig.JSONKeys.STUDENTS.key());
        if (students == null || students.isEmpty()) {
            System.out.println("Keine Schüler in dieser Klasse vorhanden.");
            return;
        }
        for(int i = 0; i < students.length(); i++) {
            JSONObject student = students.getJSONObject(i);
            System.out.println("Student: " + student.getString(JSONConfig.JSONKeys.FIRSTNAME.key()) + " " +
                    student.getString(JSONConfig.JSONKeys.LASTNAME.key()));
        }

        System.out.println("Bitte geben Sie den Vornamen des Schülers ein:");
        String firstName = sc.nextLine();
        System.out.println("Bitte geben Sie den Nachnamen des Schülers ein:");
        String lastName = sc.nextLine();

        int indexToRemove = -1;
        for (int i = 0; i < students.length(); i++) {
            JSONObject student = students.getJSONObject(i);
            if (student.getString(JSONConfig.JSONKeys.FIRSTNAME.key()).equalsIgnoreCase(firstName) &&
                    student.getString(JSONConfig.JSONKeys.LASTNAME.key()).equalsIgnoreCase(lastName)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            System.out.println("Schüler nicht gefunden.");
            return;
        }

        System.out.println("Möchten Sie " + firstName + " " + lastName + " wirklich aus der Klasse entfernen? (ja/nein)");
        String confirmation = sc.nextLine();
        if (confirmation.equalsIgnoreCase("ja")) {
            students.remove(indexToRemove);
            System.out.println("Schüler wurde entfernt.");
        } else {
            System.out.println("Vorgang abgebrochen.");
        }

        service.saveJSON();
        Sleep.sleep(1500);
    }

}

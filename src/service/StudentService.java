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
}

package service;

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
        Sleep.sleep(1500);
        Teacher teacher = new Teacher(vorname, nachname, alter, subjects);

        System.out.println("Speichere nun den Lehrer...");
        JSONObject school = data.getJSONObject(schoolKey);

        JSONArray lehrerArray = school.optJSONArray("teacher");
        if (lehrerArray == null) {
            lehrerArray = new JSONArray();
            school.put("teacher", lehrerArray);
        }
        lehrerArray.put(teacher.toJSON());
        data.put(schoolKey, school);
        service.saveJSON();

        Sleep.sleep(1500);
        System.out.println("Erfolgreich gespeichert!");
    }

}

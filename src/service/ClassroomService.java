package service;

import model.Classroom;
import model.JSONConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Scanner;

public class ClassroomService {
    static JSONservice service = new JSONservice();
    static Scanner sc = new Scanner(System.in);

    /// Methode zum erstellen eines Klassenzimmers. Erwartet keinen Wert und besitzt keinen Rückgabewert.
    /// Fügt den Raum einer vorhandenen Schule aus dem JSON Objekt hinzu....
    public static void createRoom() {
        JSONObject data = service.getJSONData();
        String eingabe = "";
        String schoolKey;

        System.out.println("Sie möchten also ein Klassenzimmer erstellen...");
        System.out.println("Sie können das Erstellen eines Klassenzimmers mit 'exit' beenden!");
        Sleep.sleep(3000);
        schoolKey = SchoolService.showSchools();

        do {
            int raumnummer;
            while (true) {
                System.out.println("Geben Sie nun bitte die Raumnummer ein:");
                try {
                    raumnummer = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Bitte geben Sie eine gültige Zahl ein.");
                }
            }

            float roomArea;
            while (true) {
                System.out.println("Bitte geben Sie nun die Größe in m² vom Raum an:");
                try {
                    roomArea = Float.parseFloat(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Bitte geben Sie eine gültige Zahl ein! z.B. (10.5)");
                }
            }

            System.out.println("Okay! Ich habe alle benötigten Daten...");
            Sleep.sleep(500);
            System.out.println("Erstelle Klassenzimmer...");
            Sleep.sleep(500);

            Classroom classroom = new Classroom(raumnummer, roomArea);

            System.out.println("Speichere nun das Klassenzimmer...");
            JSONObject school = data.getJSONObject(schoolKey);

            JSONArray rooms = school.optJSONArray(JSONConfig.JSONKeys.ROOMS.key());
            if (rooms == null) {
                rooms = new JSONArray();
                school.put(JSONConfig.JSONKeys.ROOMS.key(), rooms);
            }

            rooms.put(classroom.toJSON());
            data.put(schoolKey, school);

            service.saveJSON();
            Sleep.sleep(500);
            System.out.println("Erfolgreich gespeichert!");
            System.out.println("Zum Beenden schreiben Sie 'exit', zum Fortfahren drücken sie einfach Enter.");
            System.out.println("Weitere Klassenräume erstellen?");


            eingabe = sc.nextLine();
        } while (!eingabe.equalsIgnoreCase("exit"));
    }

}


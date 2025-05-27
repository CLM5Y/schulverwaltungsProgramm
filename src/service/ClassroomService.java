package service;

import model.Classroom;
import model.JSONConfig;
import org.json.JSONArray;
import org.json.JSONObject;

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
        Sleep.sleep(1000);
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

    /// Funktion zum Belgen von Räumen...
    public static void occupiesTheRoom() {
        JSONObject data = service.getJSONData();
        String schoolKey;
        String eingabe;
        JSONArray classes;
        boolean classExists = false;

        System.out.println("Sie möchten also einen Raum reservieren...");
        schoolKey = SchoolService.showSchools();
        JSONObject school = data.getJSONObject(schoolKey);
        JSONArray rooms = school.optJSONArray(JSONConfig.JSONKeys.ROOMS.key());

        System.out.println("Ich werde Ihnen nun die Zimmerliste ausgeben...");

        if (rooms != null && !rooms.isEmpty()) {
            for (int i = 0; i < rooms.length(); i++) {
                JSONObject room = rooms.getJSONObject(i);
                System.out.println("Zimmernummer: " + room.getInt(JSONConfig.JSONKeys.ROOMNUMBER.key()));
            }
        } else {
            System.out.println("Es sind keine Räume vorhanden.");
            return;
        }


        System.out.println("Bitte geben Sie nun den Raum ein, welchen Sie belegen wollen!");
        eingabe = sc.nextLine();

        JSONObject selectedRoom = null;
        for (int i = 0; i < rooms.length(); i++) {
            JSONObject room = rooms.getJSONObject(i);
            if (room.get(JSONConfig.JSONKeys.ROOMNUMBER.key()).toString().equals(eingabe)) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Der Raum wurde nicht gefunden!");
            return;
        }

        if (selectedRoom.has(JSONConfig.JSONKeys.CLASS.key()) && !selectedRoom.isNull(JSONConfig.JSONKeys.CLASS.key())) {
            System.out.println("Der Raum ist leider schon belegt mit der Klasse: " + selectedRoom.getString(JSONConfig.JSONKeys.CLASS.key()));
            Sleep.sleep(1500);
            return;
        }

        System.out.println("Ich zeige Ihnen nun alle Klassen der Schule: " + school.getString(JSONConfig.JSONKeys.SCHOOLNAME.key()));
        classes = school.optJSONArray(JSONConfig.JSONKeys.CLASSES.key());
        for (int j = 0; j < classes.length(); j++) {
            JSONObject schoolclass = classes.getJSONObject(j);
            System.out.println(schoolclass.getString(JSONConfig.JSONKeys.NAME.key()));
        }

        System.out.println("Bitte geben Sie die Klasse ein, die den Raum belegen soll:");
        eingabe = sc.nextLine();

        for (int i = 0; i < classes.length(); i++) {
            JSONObject schoolclass = classes.getJSONObject(i);
            String className = schoolclass.getString(JSONConfig.JSONKeys.NAME.key());

            if (className.equalsIgnoreCase(eingabe)) {
                classExists = true;

                System.out.println("Der Raum wird mit der Klasse belegt...");
                selectedRoom.put(JSONConfig.JSONKeys.CLASS.key(), className);
                System.out.println("Raum erfolgreich belegt.");
                Sleep.sleep(1500);
                break;
            }
        }

        if (!classExists) {
            System.out.println("Die Klasse gibt es nicht!");
        }
        service.saveJSON();
    }

    public static void unOccupiesTheRoom() {
        JSONObject data = service.getJSONData();
        String schoolKey;
        String eingabe;

        System.out.println("Sie möchten also die Belegung eines Raums aufheben...");
        schoolKey = SchoolService.showSchools();
        JSONObject school = data.getJSONObject(schoolKey);
        JSONArray rooms = school.optJSONArray(JSONConfig.JSONKeys.ROOMS.key());

        if (rooms != null && !rooms.isEmpty()) {
        for (int i = 0; i < rooms.length(); i++) {
            JSONObject room = rooms.getJSONObject(i);
            if (room.has(JSONConfig.JSONKeys.CLASS.key()) && !room.isNull(JSONConfig.JSONKeys.CLASS.key())) {
                System.out.println("Ich werde Ihnen nun die Zimmerliste der belegten Räume ausgeben...");
                System.out.println("Raumnummer: " +
                        room.getInt(JSONConfig.JSONKeys.ROOMNUMBER.key()) + " " +
                        "Belegt von: " + room.getString(JSONConfig.JSONKeys.CLASS.key())
                );
            }else{
                System.out.println("Keine belegten Räume vorhanden.");
                Sleep.sleep(500);
                return;
            }
        }
        }else{
            System.out.println("Es sind noch keine Räume vorhanden.");
            return;
        }

        System.out.println("Bitte geben Sie nun den Raum ein, welchen Sie die Belegung aufheben wollen...");
        eingabe = sc.nextLine();

        JSONObject selectedRoom = null;
        for (int i = 0; i < rooms.length(); i++) {
            JSONObject room = rooms.getJSONObject(i);
            if (room.get(JSONConfig.JSONKeys.ROOMNUMBER.key()).toString().equals(eingabe)) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Der Raum wurde nicht gefunden!");
            return;
        }

        if (eingabe.equals(String.valueOf(selectedRoom.getInt(JSONConfig.JSONKeys.ROOMNUMBER.key())))){
            System.out.println("Die Belegung des Raums " + selectedRoom.getInt(JSONConfig.JSONKeys.ROOMNUMBER.key()) + " wird aufgehoben...");
            selectedRoom.remove(JSONConfig.JSONKeys.CLASS.key());
            System.out.println("Raumbelegung erfolgreich aufgehoben!");
            Sleep.sleep(1500);
        }

        service.saveJSON();
    }

    public static void showAllRooms(){
        JSONObject data = service.getJSONData();
        String schoolKey;

        System.out.println("Sie möchten also alle Räume sehen...");
        schoolKey = SchoolService.showSchools();
        JSONObject school = data.getJSONObject(schoolKey);
        JSONArray rooms = school.optJSONArray(JSONConfig.JSONKeys.ROOMS.key());

        if (rooms != null && !rooms.isEmpty()) {
            System.out.println("Ich werde Ihnen nun die Zimmerliste der belegten Räume ausgeben...");
            for (int i = 0; i < rooms.length(); i++) {
                JSONObject room = rooms.getJSONObject(i);
                if (room.has(JSONConfig.JSONKeys.CLASS.key()) && !room.isNull(JSONConfig.JSONKeys.CLASS.key())) {
                    System.out.println("Zimmernummer: " +
                            room.getInt(JSONConfig.JSONKeys.ROOMNUMBER.key()) + " " +
                            "Raumgröße: " + room.getFloat(JSONConfig.JSONKeys.ROOMAREA.key()) + "m²" + " " +
                            "Belegt von: " + room.getString(JSONConfig.JSONKeys.CLASS.key()));
                }else{
                    System.out.println("Zimmernummer: " + room.getInt(JSONConfig.JSONKeys.ROOMNUMBER.key()) + " "
                    + "Größe: " + room.getFloat(JSONConfig.JSONKeys.ROOMAREA.key()) + "m²"
                    );
                }
            }
        }else{
            System.out.println("Es sind noch keine Räume vorhanden.");
        }
        Sleep.sleep(2000);
    }

}


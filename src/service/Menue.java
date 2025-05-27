package service;

import java.util.Scanner;

public class Menue {
    ///Diese Funktion ruft ein Menue für die Ausgabe auf.
    public static void showMenue(){
        Scanner scanner = new Scanner(System.in);
        int auswahl;

        System.out.println("Herzlich Willkommen zum Schulverwaltungsprogramm!");
        System.out.println("Es stehen folgende Möglichkeiten zur Auswahl:");
        System.out.println("""
                \u2022 Schule erstellen (1)
                \u2022 Klassenzimmer erstellen (2)
                \u2022 Klasse erstellen (3)
                \u2022 Lehrer zu einer Klasse hinzufügen (4)
                \u2022 Schüler zu einer Klasse hinzufügen (5)
                \u2022 Belegen eines Raums mit einer Klasse (6)
                \u2022 Belegung eines Raumes aufheben (7)
                \u2022 Gesamte Raumbelegung ausgeben (8)
                \u2022 Alle Klasseninformationen anzeigen (9)
                \u2022 Entfernen eines Schülers aus der Klasse (10)
                \u2022 Entfernen eines Lehrers aus der Klasse (11)
                \u2022 Klasse komplett löschen (12)
                \u2022 Anwendung beenden (13)
                """);
        System.out.println("Geben Sie nun bitte Ihre Auswahl ein: >>");
        try {
            auswahl = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Bitte eine Zahl eingeben.");
            Sleep.sleep(500);
            return;
        }
        switch (auswahl){
            case 1:{
                // Hier Schule erstellen
                System.out.println("Schule erstellen, Alles klar!");
                Sleep.sleep(500);
                SchoolService.createSchool();
                break;
            }
            case 2:{
                //Hier Klassenzimmer erstellen
                ClassroomService.createRoom();
                break;
            }
            case 3:{
                //Hier Klasse erstellen
                SchoolClassService.createSchoolClass();
                break;
            }
            case 4:{
                // Hier Lehrer einer Klasse hinzufügen
                TeacherService.createTeacher();
                break;
            }
            case 5:{
                // Hier Schüler einer Klasse hinzufügen
                StudentService.createStudent();
                break;
            }
            case 6:{
                // Hier Belegung eines Raumes mit einer Klasse#
                ClassroomService.occupieTheRoom();
                break;
            }
            case 7:{
                // Hier Belegung eines Raumes aufheben
                break;
            }
            case 8:{
                // Hier Gesamte Raumbelegung aufheben
                break;
            }
            case 9:{
                // Hier Alle Klasseninformationen anzeigen
                break;
            }
            case 10:{
                // Hier einen Schüler aus der Klasse entfernen
                break;
            }
            case 11:{
                // Hier einen Schüler aus der Klasse entfernen
                break;
            }
            case 12:{
                // Hier Klasse komplett löschen
                break;
            }
            case 13:{
                // Hier Anwendung löschen
                break;
            }
            default:{
                System.out.println("Bitte gültige Auswahl treffen!");
                break;
            }
        }


    }
    /// Ausgabe des Namens der Anwendung in ASCII Art
    public static void asciiArt(){
        System.out.println("""
                
                   _   _   _   _   _   _   _   _   _   _   _   _   _   _   _     _   _   _ \s
                  / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\   / \\ / \\ / \\\s
                 ( S | c | h | u | l | v | e | r | w | a | l | t | u | n | g ) ( 1 | . | 0 )
                  \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/   \\_/ \\_/ \\_/\s
                by clm5y
                """);
    }
}

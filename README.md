Schulverwaltungsprogramm von CLM5Y

## Stories
### SCHOOL-01 - Nutzermenü aufbauen
Als Nutzer möchte ich ein Nutzermenü erhalten. In diesem Nutzermenü sollen diverse Menüpunkt angezeigt werden.
Nach jeder Aktion soll das Nutzermenü wieder angezeigt werden.
Das Menü soll folgende Menüpunkte haben:
* Schule erstellen (1) -DONE
* Klassenzimmer erstellen (2) -DONE
* Klasse erstellen (3) -DONE
* Lehrer zu einer Klasse hinzufügen (4) -DONE
* Schüler zu einer Klasse hinzufügen (5) -DONE
* Belegen eines Raums mit einer Klasse (6)
* Belegung eines Raumes aufheben (7)
* Gesamte Raumbelegung ausgeben (8)
* Alle Klasseninformationen anzeigen (9)
* Entfernen eines Schülers aus der Klasse (10)
* Entfernen eines Lehrers aus der Klasse (11)
* Klasse komplett löschen (12)
* Anwendung beenden (13)

Diese Menüpunkte sollen erstmal nur ausgeben "Aktion x wurde ausgewählt".
Ausnahme ist hier der Menüpunkt "Anwendung beenden", dieser soll die Anwendung zusätzlich beenden.

#### Akzeptanzkriterien
* Anwendung startet fehlerfrei
* Anwendung gibt das gegebene Nutzermenü aus
* Menüpunkt über Eingabe auswählbar
* Ausgabe der gewählten Aktion
* Menüpunkt "Anwendung beenden" lässt das Programm enden

### SCHOOL-02 - Datenmodell implementieren
Als technischer PO möchte ich, dass das Datenmodell erstellt wird, damit ich später auf dieses Modell zurückgreifen kann.
Das Datenmodell besteht aus folgenden Klassen und Eigenschaften

Schüler:
* Name
* Vorname
* Alter
* Lieblingsfächer

Lehrer
* Name
* Vorname
* Alter
* Lehrende Fächer

Klasse:
* Name
* maximale Schülerkapazität
* Lehrer
* Schüler

Zimmer:
* Raumnummer
* Raumgröße (in m^2)
* Klasse (Falls Raum aktuell belegt ansonsten nicht gesetzt)

Schule:
* Name
* maximale Klassenkapazität
* Klassen
* Zimmer

#### Akzeptanzkriterien
* Alle gegebenen Klassen sind erstellt
* Alle notwendigen Eigenschaften sind vorhanden

### SCHOOL-03 - Schule erstellen
Als Nutzer möchte ich als erstes eine Schule erstellen können.
Dafür möchte ich über den Menüpunkt "Schule erstellen" eine Eingabemöglichkeit bekommen.
Ich möchte, dass man den Namen der Schule eingeben kann.
Eine Erfassung der Zimmer und der Klassen findet hier nicht statt.
Die Schule soll nach Erfassung persistent abgespeichert werden.

#### Akzeptanzkriterien
* Menüpunkt "Schule erstellen" zeigt eine Eingabe an
* Der Name der Schule kann eingegeben werden
* Schule ist persistent abgespeichert

### SCHOOL-04 - Zimmer erstellen 
Als Nutzer möchte ich für die Schule ein Zimmer erstellen können.
Dazu möchte ich, nach Auswahl des Menüpunktes "Klassenzimmer erstellen", eine Schule auswählen anhand ihres Namen.
Als Nächstes möchte ich die Werte Raumnummer und Raumgröße erfassen können.
Im Anschluss soll dann dieses Zimmer mit der Verbindung zur Schule persistiert werden.
Die Angabe einer Klasse soll in diesem Schritt noch nicht erfolgen.

#### Akzeptanzkriterien
* Menüpunkt "Klassenzimmer erstellen" führt zu Auswahl einer Schule
  * Auswahl findet über Eingabe des Schulnamens statt
* Nach Auswahl der Schule kann Raumnummer und Raumgröße eingegeben werden
* Nach Eingabe wird Zimmer persistent abgespeichert

### SCHOOL-05 - Klasse erstellen
Als Nutzer möchte ich für die Schule eine Klasse erstellen können.
Die Erstellung der Klasse findet nach Auswahl des Menüpunktes "Klasse erstellen" statt.
Als Erstes muss die Schule ausgewählt werden. (Über den Namen)
Im Anschluss kann dann der Name der Klasse sowie die maximale Schülerkapazität erfasst werden.
Nach der Eingabe der Daten soll die Klasse persistent abgespeichert werden.

#### Akzeptanzkriterien
* Erstellung der Schule nach Auswahl Menüpunkt "Klasse erstellen"
* Auswahl der Schule vor Erfassung der Daten
  * Auswahl über Namen der Schule
* Erfassung der Daten Name und Schülerkapazität
* Persistente Speicherung der Klasse mit Verweis auf die Schule

### SCHOOL-06 - Lehrer zu einer Klasse hinzufügen 
Als Nutzer möchte ich für eine Klasse einen Lehrer hinzufügen können.
Dazu komme ich über den Menüpunkt "Lehrer zu einer Klasse hinzufügen".
Als Erstes muss ich eine Klasse auswählen, dies passiert über den Klassennamen.
Danach kann ich dann die Daten für die Lehrkraft eingeben.
Name, Vorname und Alter werden ganz normal erfasst.
Bei der Erfassung der zu lehrenden Fächer wird nach der Eingabe eines Fachs immer gefragt ob ein weiteres Fach erfasst werden soll.
Nach Zustimmung kann ein weiteres erfasst werden, bei Verneinung wird die Erfassung der Lehrkraft abgeschlossen.
Dazu gehört auch, dass die Lehrkraft persistiert wird mit einem Verweis auf die Klasse.

#### Akzeptanzkriterien
* Erfassung der Lehrkraft über Menüpunkt "Lehrer zu einer Klasse hinzufügen"
* Auswahl der Klasse über den Namen
* Erfassung der Lehrkraftdaten
  * Name, Vorname und Alter können erfasst werden
  * Fächer werden so lange erfasst bis keines mehr zu erfassen ist (Abfrage nach jeder Eingabe)
* Persistierung der Lehrkraft mit Verweis auf die zugehörige Klasse

### SCHOOL-07 - Schüler zu einer Klasse hinzufügen 
Als Nutzer möchte ich für eine Klasse einen Schüler hinzufügen können.
Die Erfassung wird über den Menüpunkt "Schüler zu einer Klasse hinzufügen" eingeleitet.
Zuerst muss die Klasse gewählt werden, in die der Schüler kommen soll, dass geschieht über den Namen der Klasse.
Als nächstes kann ich dann die Daten für den Schüler erfassen. Ebenso wie bei der Lehrkraft wird hier Name, Vorname und Alter normal erfasst.
Bei den Lieblingsfächern wird dann, ähnlich zur Lehrkraft, die Erfassung weiterer Lieblingsfächer über eine Abfrage ermittelt.
Zum Schluss wird dann auch der Schüler persistiert mit dem Verweis auf die Klasse.

#### Akzeptanzkriterien
* Erfassung des Schülers über Menüpunkt "Schüler zu einer Klasse hinzufügen"
* Auswahl der Klasse über den Namen
* Erfassung der Daten Name, Vorname und Alter
* Erfassung der Lieblingsfächer über eine wiederkehrende Abfrage
  * bis keine weiteren Fächer mehr erfasst werden sollen
* Persistente Speicherung des Schülers mit Verweis auf die Klasse

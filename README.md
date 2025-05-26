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
## Stories

### SCHOOL-08 - Raum belegen
Als Nutzer möchte ich die Möglichkeit haben einen Raum mit einer Klasse zu belegen.
Der Raum ist dann gesperrt für andere Klassen.
Dabei soll zuerst der Raum ausgewählt werden, der belegt werden soll. Hier wird dann überprüft ob der Raum noch frei ist.
Als Nächstes wähle ich dann die Klasse aus, die dem Raum zugewiesen werden soll.

#### Akzeptanzkriterien
* Belegung eines Raums über Menüpunkt "Belegen eines Raums mit einer Klasse"
* Auswahl des Raums
  * Prüfung auf Verfügbarkeit
* Auswahl der Klasse
* Persistierung der Verbindung

### SCHOOL-09 - Belegung aufheben
Als Nutzer möchte ich eine Raumbelegung auch wieder aufheben können.
Dafür komme ich über den Menüpunkt "Belegung eines Raums aufheben".
Für das Aufheben der Belegung muss ich lediglich die Raumnummer angeben.
Das Ergebnis ist, dass der Raum wieder belegt werden kann.

#### Akzeptanzkriterien
* Aufhebung über Menüpunkt "Belegung eines Raums aufheben"
* Eingabe der Raumnummer
* Raum wieder frei für eine Belegung

### SCHOOL-10 - Übersicht über Räume
Als Nutzer möchte ich eine Übersicht über die Räume der Schule bekommen.
Die Anzeige möchte ich über den Menüpunkt "Gesamte Raumbelegung ansehen" erhalten.
Dabei soll mir angezeigt werden, welche Räume es gibt.
Zusätzlich soll mir angezeigt werden welche Räume gerade frei sind und welche belegt sind.
Bei belegten Räumen soll dann zusätzlich noch der Klassennamen angezeigt werden

#### Akzeptanzkriterien
* Anzeige über Menüpunkt "Gesamte Raumbelegung ansehen"
* Anzeige aller Räume
* Anzeige ob Raum frei oder belegt ist
  * Wenn belegt, dann Anzeige der Klasse die den Raum belegt

### SCHOOL-11 - Übersicht über Klassen
Als Nutzer möchte ich mir einen Überblick über die Klassen machen können.
Die Anzeige erreiche ich über den Menüpunkt "Alle Klasseninformationen anzeigen"
Die Anzeige besteht aus allen notwendigen Informationen, darunter zählen der Name der Klasse, sowie die Anzahl der Lehrer.
Bei der Anzahl der Schüler soll hier die Anzahl im Vergleich zur maximalen Kapazität angezeigt werden.

#### Akzeptanzkriterien
* Anzeige über den Menüpunkt "Alle Klasseninformationen anzeigen"
* Alle notwendigen Informationen der Klasse werden angezeigt
  * Name, Anzahl der Lehrer sowie Anzahl der Schüler im Vergleich zur Kapazität

### SCHOOL-12 - Entfernen eines Schülers aus einer Klasse
Als Nutzer möchte ich Schüler aus einer Klasse entfernen können, sofern diese nicht mehr Teil der Klasse sind.
Dabei wird das Entfernen über den Menüpunkt "Entfernen eines Schülers aus der Klasse" eingeleitet.
Als Nächstes muss dann die Klasse ausgewählt werden, in der der Schüler ist.
Jetzt muss dann der Schüler gewählt werden, hierzu müssen Name und Vorname eingegeben werden.
Zum Schluss wird nochmal abgefragt, ob dieser Vorgang wirklich durchgeführt werden soll.
Bei Bestätigung wird der Schüler aus der Klasse entfernt.
Bei Verneinung wird der Vorgang abgebrochen.

#### Akzeptanzkriterien
* Entfernen über Menüpunkt "Entfernen eines Schülers aus der Klasse"
* Auswahl der Klasse
* Auswahl des Schülers
  * Eingabe von Name und Vorname
* Abfrage, ob Vorgang durchgeführt werden soll
  * Ja, Schüler wird aus Klasse entfernt
  * Nein, Vorgang wird abgebrochen

### SCHOOL-13 - Entfernen eines Lehrers aus Klasse
Als Nutzer möchte ich Lehrer aus einer Klasse entfernen können, sofern diese nicht mehr Teil der Klasse sind.
Dabei wird das Entfernen über den Menüpunkt "Entfernen eines Lehrer aus der Klasse" eingeleitet.
Als Nächstes muss dann die Klasse ausgewählt werden, in der der Lehrer ist.
Jetzt muss dann der Lehrer gewählt werden, hierzu müssen Name und Vorname eingegeben werden.
Zum Schluss wird nochmal abgefragt, ob dieser Vorgang wirklich durchgeführt werden soll.
Bei Bestätigung wird der Lehrer aus der Klasse entfernt.
Bei Verneinung wird der Vorgang abgebrochen.

#### Akzeptanzkriterien
* Entfernen über Menüpunkt "Entfernen eines Lehrers aus der Klasse"
* Auswahl der Klasse
* Auswahl des Lehrers
  * Eingabe von Name und Vorname
* Abfrage, ob Vorgang durchgeführt werden soll
  * Ja, Lehrer wird aus Klasse entfernt
  * Nein, Vorgang wird abgebrochen

### SCHOOL-14 - Klasse komplett auflösen
Als Nutzer möchte ich die Möglichkeit haben eine Klasse komplett zu entfernen.
Dazu wähle ich den Menüpunkt "Klasse komplett löschen".
Hier muss ich dann die Klasse auswählen, die ich löschen möchten.
Es soll noch einmal abgefragt werden, ob dieser Vorgang durchgeführt werden soll.
Bei Bestätigung wird die Klasse gelöscht sowie alle zugehörigen Lehrer und Schüler.
Bei Verneinung passiert nichts und der Vorgang wird abgebrochen.

#### Akzeptanzkriterien
* Löschen über Menüpunkt "Klasse komplett löschen"
* Auswahl der Klasse, die gelöscht werden soll
* Abfrage ob Löschvorgang durchgeführt werden soll
  * Ja, Klasse, Lehrer und Schüler werden gelöscht
  * Nein, Vorgang wird abgebrochen

package model;

// Definition verschiedener Trainer-Rollen im Verein und
// Jede Rolle enthält eine Beschreibung ihrer Aufgabe.

public enum Rolle {
    CHEFTRAINER("Verantwortlich für das gesamte Team"),
    TAKTIKTRAINER("Fokus auf Spielstrategie und Taktik"),
    ATHLETIKTRAINER("Kondition und Fitness"),
    ANALYST("Datenanalyse und Gegnerbeobachtung"),
    TORWARTTRAINER("Betreuung der Torhüter");

    // Das Feld 'beschreibung' ist final und kann nachträglich nicht geändert werden.
    private final String beschreibung;


    //Konstruktor für jede Rolle mit entsprechender Beschreibung.
    Rolle(String beschreibung) {
        this.beschreibung = beschreibung;
    }


    //Gibt die Beschreibung der Rolle zurück.
    public String getBeschreibung() {return beschreibung;}

    //Gibt den Namen der Rolle zusammen mit der Beschreibung zurück.
    @Override
    public String toString() {
        return name() + " – " + beschreibung;
    }
}
// service/ZuweisungService.java
package service;

import dao.ZuweisungDAO;
import java.time.LocalDate;
import java.util.List;

public class ZuweisungService {

    public static boolean neueZuweisung(int fahrzeugId, int mitarbeiterId, LocalDate von, LocalDate bis, String bemerkung) {
        return ZuweisungDAO.zuweisung_Hinzufuegen(fahrzeugId, mitarbeiterId, von, bis, bemerkung);
    }

    public static List<Object[]> alleZuweisungen() {
        return ZuweisungDAO.load_All_Zuweisungen();
    }

    // Anzahl Zuweizung
    public static int getAnzahlZuweisungen() {
        return ZuweisungDAO.anzahl_Zuweizung();
    }

    public static boolean verfugbar_Contorl(int fahrzeugId, LocalDate von, LocalDate bis) {
        return ZuweisungDAO.fahrzeug_Frei(fahrzeugId, von, bis);
    }


}

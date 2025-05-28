package model;

/**
 * این کلاس نمایانگر وسیله نقلیه نوع Fahrrad (دوچرخه) است.
 * از کلاس abstrakt Fahrzeug ارث‌بری می‌کند و ویژگی‌های خاص دوچرخه‌ها را دربر دارد.
 */
public class Fahrrad extends Fahrzeug {

    // -----------------------------
    // فیلدهای خاص Fahrrad
    // -----------------------------
    private boolean hatKorb;  // آیا سبد دارد؟
    private String typ;       // نوع دوچرخه (مثلاً Citybike, Mountainbike)

    // -----------------------------
    // سازنده پیش‌فرض
    // -----------------------------
    public Fahrrad() {
    }

    // -----------------------------
    // سازنده کامل شامل فیلدهای پدر و فرزند
    // -----------------------------
    public Fahrrad(int id, String marke, String modell, String kennzeichen, String typFahrzeug, int kundeId,
                   boolean hatKorb, String typ) {
        super(id, marke, modell, kennzeichen, typFahrzeug, kundeId);
        this.hatKorb = hatKorb;
        this.typ = typ;
    }

    // -----------------------------
    // Getter و Setter ها
    // -----------------------------
    public boolean isHatKorb() {
        return hatKorb;
    }

    public void setHatKorb(boolean hatKorb) {
        this.hatKorb = hatKorb;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    // -----------------------------
    // پیاده‌سازی متد abstrakt
    // -----------------------------
    @Override
    public String getFahrzeugDetails() {
        return "Fahrrad - " + marke + " " + modell + ", Typ: " + typ +
                ", Kennzeichen: " + kennzeichen + ", Korb: " + (hatKorb ? "Ja" : "Nein");
    }

    // -----------------------------
    // toString
    // -----------------------------
    @Override
    public String toString() {
        return super.toString() + ", Fahrrad{" +
                "hatKorb=" + hatKorb +
                ", typ='" + typ + '\'' +
                '}';
    }
}

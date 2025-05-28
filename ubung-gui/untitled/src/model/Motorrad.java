package model;

/**
 * این کلاس نمایانگر وسیله نقلیه نوع Motorrad (موتورسیکلت) است.
 * از کلاس abstrakt Fahrzeug ارث‌بری می‌کند و دارای ویژگی‌های خاص موتورسیکلت است.
 */
public class Motorrad extends Fahrzeug {

    // -----------------------------
    // فیلدهای خاص Motorrad
    // -----------------------------
    private int hubraum;           // حجم موتور بر حسب سی‌سی (cc)
    private boolean gangschaltung; // آیا دنده دستی دارد؟

    // -----------------------------
    // سازنده پیش‌فرض
    // -----------------------------
    public Motorrad() {
    }

    // -----------------------------
    // سازنده کامل شامل فیلدهای پدر و فرزند
    // -----------------------------
    public Motorrad(int id, String marke, String modell, String kennzeichen, String typ, int kundeId,
                    int hubraum, boolean gangschaltung) {
        super(id, marke, modell, kennzeichen, typ, kundeId);
        this.hubraum = hubraum;
        this.gangschaltung = gangschaltung;
    }

    // -----------------------------
    // Getter و Setter ها
    // -----------------------------
    public int getHubraum() {
        return hubraum;
    }

    public void setHubraum(int hubraum) {
        this.hubraum = hubraum;
    }

    public boolean isGangschaltung() {
        return gangschaltung;
    }

    public void setGangschaltung(boolean gangschaltung) {
        this.gangschaltung = gangschaltung;
    }

    // -----------------------------
    // پیاده‌سازی متد abstrakt
    // -----------------------------
    @Override
    public String getFahrzeugDetails() {
        return "Motorrad - " + marke + " " + modell + ", Kennzeichen: " + kennzeichen +
                ", Hubraum: " + hubraum + "cc, Gangschaltung: " + (gangschaltung ? "Ja" : "Nein");
    }

    // -----------------------------
    // toString
    // -----------------------------
    @Override
    public String toString() {
        return super.toString() + ", Motorrad{" +
                "hubraum=" + hubraum +
                ", gangschaltung=" + gangschaltung +
                '}';
    }
}

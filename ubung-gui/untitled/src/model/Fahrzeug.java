package model;

/**
 * کلاس abstrakt Fahrzeug به‌عنوان کلاس پدر برای همه انواع وسایل نقلیه (PKW, Motorrad, Fahrrad) استفاده می‌شود.
 * این کلاس شامل فیلدهای عمومی و متدهای پایه‌ای است که در همه وسایل نقلیه مشترک هستند.
 */
public abstract class Fahrzeug {

    // -----------------------------
    // فیلدهای عمومی برای همه وسایل نقلیه
    // -----------------------------
    protected int id;
    protected String marke;
    protected String modell;
    protected String kennzeichen;
    protected String typ;      // نوع وسیله نقلیه (PKW, MOTORRAD, FAHRRAD)
    protected int kundeId;     // شناسه مشتری که مالک وسیله است

    // -----------------------------
    // سازنده پیش‌فرض
    // -----------------------------
    public Fahrzeug() {
    }

    // -----------------------------
    // سازنده کامل
    // -----------------------------
    public Fahrzeug(int id, String marke, String modell, String kennzeichen, String typ, int kundeId) {
        this.id = id;
        this.marke = marke;
        this.modell = modell;
        this.kennzeichen = kennzeichen;
        this.typ = typ;
        this.kundeId = kundeId;
    }

    // -----------------------------
    // Getter و Setter ها
    // -----------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getKundeId() {
        return kundeId;
    }

    public void setKundeId(int kundeId) {
        this.kundeId = kundeId;
    }

    // -----------------------------
    // متد abstract (باید در کلاس فرزند پیاده‌سازی شود)
    // -----------------------------
    public abstract String getFahrzeugDetails();

    // -----------------------------
    // toString عمومی
    // -----------------------------
    @Override
    public String toString() {
        return "Fahrzeug{" +
                "id=" + id +
                ", marke='" + marke + '\'' +
                ", modell='" + modell + '\'' +
                ", kennzeichen='" + kennzeichen + '\'' +
                ", typ='" + typ + '\'' +
                ", kundeId=" + kundeId +
                '}';
    }
}

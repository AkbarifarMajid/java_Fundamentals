package model;

/**
 * این کلاس نمایانگر یک مشتری (Kunde) در سیستم است.
 * فیلدهای آن مطابق با ستون‌های جدول kunde در پایگاه داده طراحی شده‌اند.
 */
public class Kunde {

    // -----------------------------
    // فیلدهای خصوصی (مطابق با جدول)
    // -----------------------------
    private int id;
    private String vorname;
    private String nachname;
    private String telefon;
    private String email;

    // -----------------------------
    // سازنده بدون پارامتر (پیش‌فرض)
    // -----------------------------
    public Kunde() {
    }

    // -----------------------------
    // سازنده کامل (با تمام پارامترها)
    // -----------------------------
    public Kunde(int id, String vorname, String nachname, String telefon, String email) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.telefon = telefon;
        this.email = email;
    }

    public Kunde( String vorname, String nachname, String telefon, String email) {

        this.vorname = vorname;
        this.nachname = nachname;
        this.telefon = telefon;
        this.email = email;
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

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // -----------------------------
    // تابع toString برای چاپ شیء
    // -----------------------------
    @Override
    public String toString() {
        return "Kunde{" +
                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

package model;

/**
 * این کلاس نمایانگر وسیله نقلیه نوع PKW (خودروی سواری) است.
 * از کلاس abstrakt Fahrzeug ارث‌بری می‌کند و ویژگی‌های خاص خودروهای سواری را دارد.
 */
public class Pkw extends Fahrzeug {

    // -----------------------------
    // فیلدهای خاص PKW
    // -----------------------------
    private int sitzanzahl;         // تعداد صندلی
    private String kraftstoffart;   // نوع سوخت (مثل Benzin, Diesel, Elektro)

    // -----------------------------
    // سازنده پیش‌فرض
    // -----------------------------
    public Pkw() {
    }

    // -----------------------------
    // سازنده کامل شامل فیلدهای پدر و فرزند
    // -----------------------------
    public Pkw(int id, String marke, String modell, String kennzeichen, String typ, int kundeId,
               int sitzanzahl, String kraftstoffart) {
        super(id, marke, modell, kennzeichen, typ, kundeId); // صدا زدن سازنده پدر
        this.sitzanzahl = sitzanzahl;
        this.kraftstoffart = kraftstoffart;
    }

    // -----------------------------
    // Getter و Setter ها
    // -----------------------------
    public int getSitzanzahl() {
        return sitzanzahl;
    }

    public void setSitzanzahl(int sitzanzahl) {
        this.sitzanzahl = sitzanzahl;
    }

    public String getKraftstoffart() {
        return kraftstoffart;
    }

    public void setKraftstoffart(String kraftstoffart) {
        this.kraftstoffart = kraftstoffart;
    }

    // -----------------------------
    // پیاده‌سازی متد abstrakt
    // -----------------------------
    @Override
    public String getFahrzeugDetails() {
        return "PKW - " + marke + " " + modell + ", Kennzeichen: " + kennzeichen +
                ", Sitze: " + sitzanzahl + ", Kraftstoff: " + kraftstoffart;
    }

    // -----------------------------
    // toString برای نمایش شیء کامل
    // -----------------------------
    @Override
    public String toString() {
        return super.toString() + ", Pkw{" +
                "sitzanzahl=" + sitzanzahl +
                ", kraftstoffart='" + kraftstoffart + '\'' +
                '}';
    }
}

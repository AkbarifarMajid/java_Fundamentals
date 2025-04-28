public class Fahrrad extends Fahrzeug{
    private boolean hatKorb;

    public Fahrrad(int id, String hersteller, String modell, int baujahr, boolean hatKorb) {
        super(id, hersteller, modell, baujahr);
        this.hatKorb = hatKorb;
    }

    //getter und setter

    public boolean isHatKorb() {
        return hatKorb;
    }

    public void setHatKorb(boolean hatKorb) {
        this.hatKorb = hatKorb;
    }

    @Override
    public void anzeigen() {
        System.out.println(toString());
       // System.out.println("Wartungstermine: " + getWartungstermine());
    }

    @Override
    public String toString() {
        return super.toString() + ", Hat Korb: " + (hatKorb ? "Ja" : "Nein");
    }

}

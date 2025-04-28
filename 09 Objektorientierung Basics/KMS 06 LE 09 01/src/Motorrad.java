public class Motorrad extends Fahrzeug{

    private int hubraum;
    private boolean hatGangschaltung;

    public Motorrad(int id, String hersteller, String modell, int baujahr, int hubraum, boolean hatGangschaltung) {
        super(id, hersteller, modell, baujahr);
        this.hubraum = hubraum;
        this.hatGangschaltung = hatGangschaltung;
    }


    //getter und setter

    public int getHubraum() {
        return hubraum;
    }

    public void setHubraum(int hubraum) {
        this.hubraum = hubraum;
    }

    public boolean isHatGangschaltung() {
        return hatGangschaltung;
    }

    public void setHatGangschaltung(boolean hatGangschaltung) {
        this.hatGangschaltung = hatGangschaltung;
    }


    @Override
    public void anzeigen() {
        System.out.println(toString());
        //System.out.println("Wartungstermine: " + getWartungstermine());
    }


    @Override
    public String toString() {
        return super.toString() + ", Hubraum=" + hubraum + " cm3, Gangschaltung=" + (hatGangschaltung ? "Ja" : "Nein");
    }


}

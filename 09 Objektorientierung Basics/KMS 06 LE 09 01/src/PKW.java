public class PKW extends Fahrzeug {
    private int sitzanzahl;

    public PKW(int id, String hersteller, String modell, int baujahr, int sitzanzahl) {
        super(id, hersteller, modell, baujahr);
        this.sitzanzahl = sitzanzahl;
    }


    //Getter und setter
    public int getSitzanzahl() {
        return sitzanzahl;
    }

    public void setSitzanzahl(int sitzanzahl) {
        this.sitzanzahl = sitzanzahl;
    }

    @Override
    public void anzeigen() {
        System.out.println(toString());
        //System.out.println("Wartungstermine: " + getWartungstermine());
    }

    @Override
    public String toString() {
        return super.toString() + ", Sitzanzahl = " + sitzanzahl;

    }
}

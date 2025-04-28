public class LKW extends Fahrzeug{
    private double ladegewicht;

    public LKW(int id, String hersteller, String modell, int baujahr, double ladegewicht) {
        super(id, hersteller, modell, baujahr);
        this.ladegewicht = ladegewicht;
    }

    //getter und setter
    public double getLadegewicht() {
        return ladegewicht;
    }

    public void setLadegewicht(double ladegewicht) {
        this.ladegewicht = ladegewicht;
    }


    @Override
    public void anzeigen() {
        System.out.println(toString());
        //System.out.println("Wartungstermine: " + getWartungstermine());
    }

    @Override
    public String toString() {
        return super.toString() + ", Ladegewicht=" + ladegewicht + " Tonnen";

    }


}

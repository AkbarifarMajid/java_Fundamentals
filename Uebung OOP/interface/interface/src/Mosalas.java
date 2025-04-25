public class Mosalas extends Shaye implements Rechnen{

    private double tul;
    private double arz;

    public Mosalas(String name, int gusheh, double tul, double arz) {
        super(name, gusheh);
        this.tul = tul;
        this.arz = arz;
    }

    public double getTul() {
        return tul;
    }

    public void setTul(double tul) {
        this.tul = tul;
    }

    public double getArz() {
        return arz;
    }

    public void setArz(double arz) {
        this.arz = arz;
    }


    @Override
    public double mohit(double a, double b) {
        return a * b;
    }

    @Override
    public double masahat(double a, double b) {
        return a + b + Math.sqrt(a * a + b * b);
    }
}

public class Dayere extends Shaye implements Rechnen{
    private double shoa;

    public Dayere(String name, int gusheh, double shoa) {
        super(name, gusheh);
        this.shoa = shoa;
    }

    public double getShoa() {
        return shoa;
    }

    public void setShoa(double shoa) {
        this.shoa = shoa;
    }

    @Override
    public double mohit(double a, double b) {
        return Math.PI * a * a;
    }

    @Override
    public double masahat(double a, double b) {
        return 2 * Math.PI * a;
    }
}

public class Main {
    public static void main(String[] args) {
        Mosalas mosal1 = new Mosalas("Mosalas Majid",3,3,2);
        Mostatil motatil1 = new Mostatil("Mostatil Majid",4,5,3);
        Dayere dayere1 = new Dayere("Dayere Majid",0,2.5);

        double area = mosal1.masahat(mosal1.getTul(), mosal1.getArz());
        area = motatil1.masahat(motatil1.getArz(), motatil1.getTul());
        area = dayere1.masahat(dayere1.getShoa(),0);


        Rechnen calc;

        calc = mosal1;
        System.out.println("masahat mosalas "+ calc.masahat(mosal1.getTul(), mosal1.getArz()));
        System.out.println("Mohit mosalas "+ calc.mohit(mosal1.getTul(), mosal1.getArz()));

        calc = mosal1;
        System.out.println("masahat Mostatil "+ calc.masahat(motatil1.getTul(), motatil1.getArz()));
        System.out.println("Mohit Mostatil "+ calc.mohit(motatil1.getTul(), motatil1.getArz()));

        calc = mosal1;
        System.out.println("masahat Dayere "+ calc.masahat(dayere1.getShoa(), 0));
        System.out.println("Mohit Dayere "+ calc.mohit(dayere1.getShoa(),0));

    }

}

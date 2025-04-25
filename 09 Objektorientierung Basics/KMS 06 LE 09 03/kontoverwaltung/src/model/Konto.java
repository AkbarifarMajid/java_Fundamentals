package model;

public class Konto extends KontoBase{

    public Konto(int myNumber, String myName) {
        super(myNumber, myName);
    }

    @Override
    public void geldNenmen(double mony) {
        if(!isValid){
            System.out.println("Dieses Konto ist ungültig. Geld kann nicht abgehoben werden.");
            return;
        }
        if(mony > 0){
            if(mony < myBalance ){
                myBalance -= mony;
                System.out.println("Abhebung war erfolgreich. Neuer Kontostand: " + myBalance);
            }
            else {
                System.out.println("Nicht genug Guthaben für diese Abhebung.");

            }
        }else{
            System.out.println("Der Betrag muss größer als 0 sein.");
        }

    }

    @Override
    public void geldHinzufugen(double mony) {
        if(!isValid){
            System.out.println("Dieses Konto ist ungültig. Geld kann nicht hinzugefügt werden.");
            return;
        }
        if(mony > 0){
            myBalance += mony;
            System.out.println("Ihre neu Balance ist : " + myBalance);
        }else{
            System.out.println("Der Betrag muss größer als 0 sein.");
        }

    }

    @Override
    public String toString() {
        return "Konto-Nr: " + myNumber + ", Name: " + myName + ", Kontostand: " + myBalance;

    }



}

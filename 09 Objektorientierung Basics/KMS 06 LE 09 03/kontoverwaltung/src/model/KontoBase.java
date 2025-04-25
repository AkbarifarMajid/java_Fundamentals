package model;

public abstract class KontoBase {
    protected int myNumber;
    protected String myName;
    protected double myBalance;
    protected boolean isValid;// Kontrol Number und Name

    // constructor
    public KontoBase(int myNumber, String myName) {
        this.isValid = true;

        if (myNumber <= 0) {
            System.out.println("Die Kontonummer ist ungültig. Der Betrag wurde nicht erfasst.");
            this.isValid = false;
        }
        else {this.myNumber = myNumber;}

        if (myName == null || myName.trim().isEmpty()) {
            System.out.println("Der Name wurde nicht eingegeben oder ist ungültig. Ihr Wert kann nicht erfasst werden.");
            this.isValid = false;

        }
        else {this.myName = myName;}


        this.myBalance = 0.0;
    }//End constructor

    //getter und setter
    public int getMyNumber() {return myNumber;}

    public void setMyNumber(int myNumber) {
        if (myNumber > 0) {
            this.myNumber = myNumber;
        } else {
            System.out.println("Die neue Kontonummer ist ungültig.");
            this.isValid = false;
        }
    }


    public String getMyName() {return myName;}

    public void setMyName(String myName) {
        if (myName != null && !myName.trim().isEmpty()) {
            this.myName = myName;
        } else {
            System.out.println("Der neue Name ist ungültig.");
            this.isValid = false;
        }
    }


    public double getMyBalance() {return myBalance;}

    public void setMyBalance(double myBalance) {this.myBalance = myBalance;}

    public boolean isValid() {return isValid;}

    public void setValid(boolean valid) {isValid = valid;}


    // Abstrakte Methoden
    public abstract void geldHinzufugen(double amount);
    public abstract void geldNenmen(double amount);

}

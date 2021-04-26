package com.Dank;

/**
 * Created by nurullah on 4.04.2017.
 */
public class NationalCustomer extends Customer{

    private int licencePlateNumber;
    private String occupation;
    private static int NCCounter = 0;

    public NationalCustomer(int customerID, String name, String surname, int licencePlateNumber, String occupation) {
        super(customerID, name, surname);
        this.licencePlateNumber = licencePlateNumber;
        this.occupation = occupation;
        this.NCCounter++;
    }

    public NationalCustomer() {
        super();
        this.licencePlateNumber = 0;
        this.occupation = null;
        this.NCCounter++;
    }

    public NationalCustomer(NationalCustomer NC) {
        super(NC.getCustomerID(),NC.getName(),NC.getSurname());
        this.licencePlateNumber = NC.licencePlateNumber;
        this.occupation = NC.occupation;
        this.NCCounter++; // Ortalama hesaplamalarında kullanılmak üzere statik sınıf sayacı
    }
    @Override
    public String toString(){
        return super.toString() + " Licence plate number: " + licencePlateNumber+ " Occupation: " + occupation;
    }

    public int getLicencePlateNumber() {
        return licencePlateNumber;
    }

    public void setLicencePlateNumber(int licencePlateNumber) {
        this.licencePlateNumber = licencePlateNumber;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public static int getNCCounter() {
        return NCCounter;
    }

    public static void setNCCounter(int NCCounter) {
        NationalCustomer.NCCounter = NCCounter;
    }
}

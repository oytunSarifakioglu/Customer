package com.Dank;

/**
 * Created by nurullah on 4.04.2017.
 */
public class InternationalCustomer extends Customer {

    private String country;
    private String city;
    private static int ICCounter = 0; // Ortalama hesaplamalarında kullanılmak üzere statik sınıf sayacı

    public InternationalCustomer(int customerID, String name, String surname, String country, String city) {
        super(customerID, name, surname);
        this.country = country;
        this.city = city;
        this.ICCounter++;
    }

    public InternationalCustomer() {
        super();
        this.country = null;
        this.city = null;
        this.ICCounter++;
    }

    public InternationalCustomer(InternationalCustomer IC) {
        super(IC.getCustomerID(),IC.getName(),IC.getSurname());
        this.country = IC.country;
        this.city = IC.city;
        this.ICCounter++;
    }
    @Override
    public String toString(){
        return super.toString() + " Country: " + country + " City: " + city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static int getICCounter() {
        return ICCounter;
    }

    public static void setICCounter(int ICCounter) {
        InternationalCustomer.ICCounter = ICCounter;
    }
}

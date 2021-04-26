package com.Dank;

/**
 * Created by nurullah on 4.04.2017.
 */
public class Customer {
    private int customerID;
    private String name;
    private String surname;
    private static int counter = 0; // Ortalama hesaplamalarında kullanılmak üzere statik sınıf sayacı

    public Customer(int customerID, String name, String surname) {
        this.customerID = customerID;
        this.name = name;
        this.surname = surname;
        this.counter++;
    }
    public Customer() {
        this.customerID = 0;
        this.name = null;
        this.surname = null;
        this.counter++;
    }
    public Customer(Customer customer){
        this.customerID = customer.customerID;
        this.name = customer.name;
        this.surname = customer.surname;
        this.counter++;
    }
    @Override
    public String toString(){
        return "CustomerID: " + customerID + " Name: " + name + " Surname: " + surname;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Customer.counter = counter;
    }
}

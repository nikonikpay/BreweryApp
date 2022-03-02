package com.example.projectbrewers;

public class Brewer {

    private int id;
    private String name;
    private String address;
    private String zipCode;
    private String city;
    private int turnOver;

    public Brewer(String name, String address, String zipCode, String city, int turnOver) {
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.turnOver = turnOver;
    }

    public Brewer(int id, String name, String address, String zipCode, String city, int turnOver) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.turnOver = turnOver;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public int getTurnOver() {
        return turnOver;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTurnOver(int turnOver) {
        this.turnOver = turnOver;
    }

    @Override
    public String toString() {
        return "Brewer{" + "id=" + id + ", name='" + name + '\'' + ", address='" + address + '\'' + ", zipCode='" + zipCode + '\'' + ", city='" + city + '\'' + ", turnOver=" + turnOver + '}';
    }
}

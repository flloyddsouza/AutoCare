package com.example.autoCare.Model;

public class Garage {
    private String name;
    private String address;
    private String lat;
    private String lon;
    private String phoneNumber;

    public Garage(String name, String address, String lat, String lon, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

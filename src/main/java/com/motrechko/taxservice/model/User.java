package com.motrechko.taxservice.model;

import java.util.*;

public class User extends BaseEntity{
    private int entity;
    private long TIN;
    private String city;
    private String street;
    private String numberOfBuilding;

    private List<Company> companies;



    public int getEntity() {
        return entity;
    }

    public void setEntity(int entity) {
        this.entity = entity;
    }



    public long getTIN() {
        return TIN;
    }

    public void setTIN(long TIN) {
        this.TIN = TIN;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumberOfBuilding() {
        return numberOfBuilding;
    }

    public void setNumberOfBuilding(String numberOfBuilding) {
        this.numberOfBuilding = numberOfBuilding;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return entity == user.entity && TIN == user.TIN && Objects.equals(city, user.city) && Objects.equals(street, user.street) && Objects.equals(numberOfBuilding, user.numberOfBuilding) && Objects.equals(companies, user.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), entity, TIN, city, street, numberOfBuilding, companies);
    }

    @Override
    public String toString() {
        return "User{" +
                "entity=" + entity +
                ", TIN=" + TIN +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numberOfBuilding='" + numberOfBuilding + '\'' +
                ", companies=" + companies +
                '}';
    }
}

package com.motrechko.taxservice.model;

import java.util.*;

public class User {
    private int id;
    private String email;
    private String password;
    private int entity;
    private String firstName;
    private String lastName;
    private long TIN;
    private String city;
    private String street;
    private String numberOfBuilding;

    private List<Company> companies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEntity() {
        return entity;
    }

    public void setEntity(int entity) {
        this.entity = entity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        User user = (User) o;
        return id == user.id && entity == user.entity && TIN == user.TIN && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(city, user.city) && Objects.equals(street, user.street) && Objects.equals(numberOfBuilding, user.numberOfBuilding) && Objects.equals(companies, user.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, entity, firstName, lastName, TIN, city, street, numberOfBuilding, companies);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", entity=" + entity +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", TIN=" + TIN +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numberOfBuilding='" + numberOfBuilding + '\'' +
                ", companies=" + companies +
                '}';
    }
}

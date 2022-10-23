package com.epam.motrechko.db.entity;

import java.util.*;

public class User {
    private int id;
    private String email;
    private String password;
    private String entity;
    private String role;
    private String firstName;
    private String lastName;
    private String company;
    private int TIN;
    private String city;
    private String street;
    private String numberOfBuilding;

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

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getTIN() {
        return TIN;
    }

    public void setTIN(int TIN) {
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
        return id == user.id && TIN == user.TIN && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(entity, user.entity) && Objects.equals(role, user.role) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(company, user.company) && Objects.equals(city, user.city) && Objects.equals(street, user.street) && Objects.equals(numberOfBuilding, user.numberOfBuilding);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, entity, role, firstName, lastName, company, TIN, city, street, numberOfBuilding);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", entity='" + entity + '\'' +
                ", role='" + role + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", company='" + company + '\'' +
                ", TIN=" + TIN +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numberOfBuilding='" + numberOfBuilding + '\'' +
                '}';
    }
}

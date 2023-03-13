package com.motrechko.taxservice.model;

import java.util.Objects;

public class Company {
    private int id;
    private User user;
    private int countEmployee;
    private String city;
    private String street;
    private String numberOfBuilding;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCountEmployee() {
        return countEmployee;
    }

    public void setCountEmployee(int countEmployee) {
        this.countEmployee = countEmployee;
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
        Company company = (Company) o;
        return id == company.id && countEmployee == company.countEmployee && Objects.equals(user, company.user) && Objects.equals(city, company.city) && Objects.equals(street, company.street) && Objects.equals(numberOfBuilding, company.numberOfBuilding);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, countEmployee, city, street, numberOfBuilding);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", user=" + user +
                ", countEmployee=" + countEmployee +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numberOfBuilding='" + numberOfBuilding + '\'' +
                '}';
    }
}

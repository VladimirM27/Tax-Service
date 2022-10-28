package com.epam.motrechko.db.entity;

import java.util.Date;

public class UnverifiedReportsView {
    private String type;
    private String firstName;
    private String lastName;
    private String email;
    private long TIN;
    private String city;
    private String street;
    private String numberOfBuilding;
    private Date date;
    private double profitSum;
    private double taxSum;
    private double fine;
    private double penny;
    private String userComment;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getProfitSum() {
        return profitSum;
    }

    public void setProfitSum(double profitSum) {
        this.profitSum = profitSum;
    }

    public double getTaxSum() {
        return taxSum;
    }

    public void setTaxSum(double taxSum) {
        this.taxSum = taxSum;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public double getPenny() {
        return penny;
    }

    public void setPenny(double penny) {
        this.penny = penny;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }
}

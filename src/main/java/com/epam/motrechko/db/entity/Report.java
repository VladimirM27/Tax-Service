package com.epam.motrechko.db.entity;

import com.epam.motrechko.enums.Status;

import java.util.Date;
import java.util.Objects;

public class Report {
    private int idReport;
    private int idUser;
    private int idInspector;
    private int idType;
    private Status status;
    private Date date;

    private double incomeSum;

    private double taxSum;
    private double fine;
    private double penny;
    private java.lang.String commentUser;
    private java.lang.String commentInspector;
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdInspector() {
        return idInspector;
    }

    public void setIdInspector(int idInspector) {
        this.idInspector = idInspector;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getIncomeSum() {
        return incomeSum;
    }

    public void setIncomeSum(double incomeSum) {
        this.incomeSum = incomeSum;
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

    public java.lang.String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(java.lang.String commentUser) {
        this.commentUser = commentUser;
    }

    public java.lang.String getCommentInspector() {
        return commentInspector;
    }

    public void setCommentInspector(java.lang.String commentInspector) {
        this.commentInspector = commentInspector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return idReport == report.idReport && idUser == report.idUser && idInspector == report.idInspector && idType == report.idType && Double.compare(report.incomeSum, incomeSum) == 0 && Double.compare(report.taxSum, taxSum) == 0 && Double.compare(report.fine, fine) == 0 && Double.compare(report.penny, penny) == 0 && Objects.equals(status, report.status) && Objects.equals(date, report.date) && Objects.equals(commentUser, report.commentUser) && Objects.equals(commentInspector, report.commentInspector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReport, idUser, idInspector, idType, status, date, incomeSum, taxSum, fine, penny, commentUser, commentInspector);
    }

    @Override
    public java.lang.String toString() {
        return "Report{" +
                "idReport=" + idReport +
                ", idUser=" + idUser +
                ", idInspector=" + idInspector +
                ", idType=" + idType +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", incomeSum=" + incomeSum +
                ", taxSum=" + taxSum +
                ", fine=" + fine +
                ", penny=" + penny +
                ", commentUser='" + commentUser + '\'' +
                ", commentInspector='" + commentInspector + '\'' +
                '}';
    }



}

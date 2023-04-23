package com.motrechko.taxservice.model;

import java.time.LocalDate;
import java.util.Objects;

public class Report {
    private int idReport;
    private int idUser;
    private int idInspector;
    private ReportType reportType;
    private Status status;
    private LocalDate created;
    private double totalIncome;
    private double totalDeductions;
    private double taxableIncome;
    private double totalTaxOwned;
    private double totalPaid;
    private String commentUser;
    private String commentInspector;


    public void setCreated(LocalDate created) {
        this.created = created;
    }
    public LocalDate getCreated() {
        return created;
    }
    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(double totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double getTotalTaxOwned() {
        return totalTaxOwned;
    }

    public void setTotalTaxOwned(double totalTaxOwned) {
        this.totalTaxOwned = totalTaxOwned;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }

    public String getCommentInspector() {
        return commentInspector;
    }

    public void setCommentInspector(String commentInspector) {
        this.commentInspector = commentInspector;
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

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return idReport == report.idReport && idUser == report.idUser && idInspector == report.idInspector && Double.compare(report.totalIncome, totalIncome) == 0 && Double.compare(report.totalDeductions, totalDeductions) == 0 && Double.compare(report.taxableIncome, taxableIncome) == 0 && Double.compare(report.totalTaxOwned, totalTaxOwned) == 0 && Double.compare(report.totalPaid, totalPaid) == 0 && Objects.equals(reportType, report.reportType) && status == report.status && Objects.equals(created, report.created) && Objects.equals(commentUser, report.commentUser) && Objects.equals(commentInspector, report.commentInspector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReport, idUser, idInspector, reportType, status, created, totalIncome, totalDeductions, taxableIncome, totalTaxOwned, totalPaid, commentUser, commentInspector);
    }

    @Override
    public String toString() {
        return "Report{" +
                "idReport=" + idReport +
                ", idUser=" + idUser +
                ", idInspector=" + idInspector +
                ", reportType=" + reportType.getType() +
                ", status=" + status.getValue()+
                ", created=" + created +
                ", totalIncome=" + totalIncome +
                ", totalDeductions=" + totalDeductions +
                ", taxableIncome=" + taxableIncome +
                ", totalTaxOwned=" + totalTaxOwned +
                ", totalPaid=" + totalPaid +
                ", commentUser='" + commentUser + '\'' +
                ", commentInspector='" + commentInspector + '\'' +
                '}';
    }
}

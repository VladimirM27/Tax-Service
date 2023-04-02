package com.motrechko.taxservice.model;



import java.util.Date;

public class ReportView {
    private int idReport;
    private String inspectorName;
    private String inspectorLastname;
    private ReportType type;
    private Status status;
    private Date date;

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

    public String getInspectorLastname() {
        return inspectorLastname;
    }

    public void setInspectorLastname(String inspectorLastname) {
        this.inspectorLastname = inspectorLastname;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

package com.motrechko.taxservice.model;



import java.util.Date;

public class ReportView {
    private int idReport;
    private String inspectorName;
    private String inspectorLastname;
    private String type;
    private String status;
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

    public void setInspectorName(java.lang.String inspectorName) {
        this.inspectorName = inspectorName;
    }

    public java.lang.String getInspectorLastname() {
        return inspectorLastname;
    }

    public void setInspectorLastname(java.lang.String inspectorLastname) {
        this.inspectorLastname = inspectorLastname;
    }

    public java.lang.String getType() {
        return type;
    }

    public void setType(java.lang.String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

package com.motrechko.taxservice.model;

import java.util.Objects;

public class ReportType {
    private int reportTypeId;
    private String type;
    private String entity;

    public int getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(int reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportType that = (ReportType) o;
        return reportTypeId == that.reportTypeId && Objects.equals(type, that.type) && Objects.equals(entity, that.entity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportTypeId, type, entity);
    }

    @Override
    public String toString() {
        return "ReportType{" +
                "reportTypeId=" + reportTypeId +
                ", type='" + type + '\'' +
                ", entity='" + entity + '\'' +
                '}';
    }
}

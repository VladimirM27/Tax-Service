package com.motrechko.taxservice.dao.queries;

public class ReportTypeQueries {
    private ReportTypeQueries(){}
    public static final String SELECT_TYPE_BY_ID = "SELECT * FROM report_type WHERE idreportType = ?";
    public static final String SELECT_TYPE_BY_NAME = "SELECT * FROM report_type WHERE type = ?";
    public static final String GET_ALL_TYPES_BY_INDIVIDUAL = "SELECT * FROM report_type WHERE entity = ?";
}

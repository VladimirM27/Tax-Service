package com.motrechko.taxservice.dao.queries;

public final class ReportQueries {

    private ReportQueries(){}
    public static final String DELETE_REPORT = "DELETE FROM report WHERE idReport = ?";
    public static final String SELECT_REPORT_BY_ID = "SELECT * FROM report WHERE idReport = ?";
    public static final String INSERT_REPORT = "INSERT INTO `report`" +
            "(`idUser`," +
            "`idInspector`," +
            "`idType`," +
            "`status`," +
            "`created`," +
            "`total_income`," +
            "`total_deductions`," +
            "`taxable_income`," +
            "`total_tax_owned`," +
            "`total_paid`," +
            "`commentUser`," +
            "`commentInspector`)" +
            "VALUES" +
            "(?,?,?,?,?,?,?,?,?,?,?,?);";

    public static final String SELECT_REPORTVIEW_BY_USER = "SELECT report.idReport, inspector.first_name AS inspectorName, inspector.last_name AS inspectorLastName, " +
            "report_type.type, status.status, report.created AS date " +
            "FROM report " +
            "JOIN inspector ON inspector.idinspector = report.idInspector " +
            "JOIN report_type ON report_type.idreportType = report.idType " +
            "JOIN status ON status.idstatus = report.idStatus where idUser = ?";

    public static final String UPDATE_REPORT = "UPDATE `tax_service_2`.`report` " +
            "SET " +
            "`idUser` = ?," +
            "`idInspector` = ?," +
            "`idType` = ?," +
            "`status` = ?," +
            "`created` = ?," +
            "`total_income` = ?," +
            "`total_deductions` = ?," +
            "`taxable_income` = ?," +
            "`total_tax_owned` = ?," +
            "`total_paid` = ?," +
            "`commentUser` = ?," +
            "`commentInspector` = ?" +
            "WHERE `idReport` = ?;";

}


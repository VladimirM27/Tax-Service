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

    public static final String SELECT_REPORTVIEW_BY_USER = "SELECT r.idReport, COALESCE(i.first_name, '') AS inspectorFirstName, COALESCE(i.last_name, '') AS inspectorLastName, rt.type, s.status, r.created AS date " +
            "FROM report AS r " +
            "LEFT JOIN inspector AS i ON i.idinspector = r.idInspector " +
            "JOIN report_type AS rt ON rt.idreportType = r.idType " +
            "JOIN status AS s ON s.status = r.status " +
            "WHERE r.idUser = ?";

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


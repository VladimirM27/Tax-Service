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
            "WHERE r.idUser = ? LIMIT ?,?";

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

    public static final String COUNT_BY_USERID = "SELECT count(*) as countRow FROM report WHERE idUser = ?";
    public static final String COUNT_BY_STATUS = "SELECT count(*) as countRow FROM report WHERE status LIKE ?";

    public static final String SELECT_UNVERIFIED_REPORT_BY_INSPECTOR_AND_STATUS = "SELECT  r.idReport,  rt.type, s.status,r.created AS date,u.first_name, u.last_name " +
            "FROM report AS r " +
            "JOIN report_type AS rt ON rt.idreportType = r.idType " +
            "JOIN user AS u ON r.idUser = u.idUser " +
            "JOIN status AS s ON s.status = r.status " +
            "WHERE (idInspector IS NULL OR idInspector = ?) AND  (s.status LIKE ?) LIMIT ?,?";

    public static final String COUNT_UNVERIFIED_REPORT_BY_INSPECTOR_AND_STATUS = "SELECT  COUNT( r.idReport ) as \"result\" FROM report as r " +
            "WHERE (idInspector IS NULL OR idInspector = ?) AND  (r.status LIKE ?)";

}


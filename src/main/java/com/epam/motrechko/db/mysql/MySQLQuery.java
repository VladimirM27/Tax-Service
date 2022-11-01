package com.epam.motrechko.db.mysql;

public final class MySQLQuery {
    private MySQLQuery(){}
    public static final String SELECT_ALL_USERS = "SELECT * FROM users";
    public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email LIKE ?";

    public static final String INSERT_INTO_USER = "INSERT INTO users(`email`,`password`,`entity`,`role`,`firstName`,`lastName`," +
            "`company`,`TIN`,`City`,`Street`,`NumberOfBuilding`)VALUES(?,?,?,?,?,?,?,?,?,?,?)";

    public static final String INSERT_INTO_REPORTS = "INSERT INTO `tax`.`report`(`idUser`,`idType`,`status`,`date`,`profitSum`,`taxSum`,`fine`,`penny`,`commentUser`)" +
            "VALUES(?,?,?,?,?,?,?,?,?)";

    public static final String SELECT_REPORTS_BY_USER = "SELECT idReport,tax.users.firstName AS \"inspector name\",tax.users.lastName AS \"inspector lastname\",tax.reporttype.type,status,date \n" +
            "FROM tax.report  JOIN tax.reporttype ON tax.report.idType = tax.reporttype.idreportType LEFT JOIN tax.users ON tax.report.idInspector = tax.users.idUsers where idUser = ?";

    public static final String SELECT_ALL_UNVERIFIED_REPORTS = "SELECT  idReport,  type, status,date,users.firstName,users.lastName " +
            " FROM tax.report  JOIN reporttype ON tax.report.idType = tax.reporttype.idreportType  JOIN users  " +
            "ON tax.report.idUser = tax.users.idUsers WHERE (idInspector IS NULL OR idInspector = ?) AND  (status LIKE \"SUBMITTED\")";

    public static final String SELECT_USERS_UNVERIFIED_REPORTS ="SELECT tax.reporttype.type, tax.users.firstName, tax.users.lastName, tax.users.email, tax.users.TIN, tax.users.city, tax.users.street, \n" +
            "tax.users.NumberOfBuilding, tax.report.date , tax.report.profitSum, tax.report.taxSum, tax.report.fine , tax.report.penny, tax.report.commentUser\n" +
            "FROM tax.report JOIN tax.reporttype ON tax.report.idType = tax.reporttype.idreportType JOIN tax.users ON tax.report.idUser = tax.users.idUsers  \n" +
            "WHERE tax.report.idReport = ?";

    public static final String UPDATE_REPORT_INSPECTOR = "UPDATE `tax`.`report` SET `status` = ?, `commentInspector` = ?,`idInspector` = ?  WHERE `idReport` = ?;";
    public static final String SELECT_REPORT_BY_ID = "SELECT * FROM report WHERE idReport = ?";

    public static final String UPDATE_REPORT_USER ="UPDATE `tax`.`report` SET `idType` = ?,`status` = ?,`date` = ?" +
            ",`profitSum` = ?,`taxSum` = ?,`fine` = ?,`penny` = ?,`commentUser` = ?" +
            " WHERE `idReport` = ?";
}




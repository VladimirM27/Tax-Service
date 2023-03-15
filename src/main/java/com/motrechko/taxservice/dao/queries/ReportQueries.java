package com.motrechko.taxservice.dao.queries;

public final class ReportQueries {

    public static final String INSERT_REPORT = "INSERT INTO `report`" +
            "(`idUser`," +
            "`idInspector`," +
            "`idType`," +
            "`idStatus`," +
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
}

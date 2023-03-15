package com.motrechko.taxservice.dao.queries;

public final class InspectorQueries {
    public static final String GET_INSPECTOR_BY_EMAIL = "SELECT * FROM inspector WHERE email LIKE ?";
    public static final String DELETE_BY_ID = "DELETE FROM inspector WHERE idinspector = ?";

    private InspectorQueries() {}

    public static final String INSERT_INSPECTOR = "INSERT INTO `inspector`(`first_name`,`last_name`,`email`,`password`) VALUES (?,?,?,?);";
}

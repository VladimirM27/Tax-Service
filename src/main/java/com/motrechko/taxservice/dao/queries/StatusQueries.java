package com.motrechko.taxservice.dao.queries;

public final class StatusQueries {
    public static final String DELETE_BY_VALUE = "DELETE FROM status WHERE status = ?";
    public static final String CREATE = "INSERT INTO `status` (`status`) VALUES(?);";
    public static final String GET_STATUS_BY_VALUE = "SELECT * FROM status WHERE status LIKE ?";
    public static final String GET_STATUS_BY_ID = "SELECT * FROM status WHERE idstatus LIKE ?";

    private StatusQueries() {}

}

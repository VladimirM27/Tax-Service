package com.epam.motrechko.db.mysql;

public final class MySQLQuery {
    public static final String SELECT_ALL_USERS = "SELECT * FROM users";
    public static final String SELECT_USER_BY_LOGIN= "SELECT * FROM users WHERE login LIKE ?";

}




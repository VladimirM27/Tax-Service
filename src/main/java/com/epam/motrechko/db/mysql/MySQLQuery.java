package com.epam.motrechko.db.mysql;

public final class MySQLQuery {
    public static final String SELECT_ALL_USERS = "SELECT * FROM users";
    public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email LIKE ?";

    public static final String INSERT_INTO_USER = "INSERT INTO `tax`.`users`(`email`,`password`,`entity`,`role`,`firstName`,`lastName`)VALUES(?,?,?,?,?)";

}




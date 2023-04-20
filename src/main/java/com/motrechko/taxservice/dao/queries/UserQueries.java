package com.motrechko.taxservice.dao.queries;

public final class UserQueries {
    private UserQueries(){}

    public static final String INSERT_USER = "INSERT INTO `user`" +
            "(`email`,`password`,`entity`," +
            "`first_name`,`last_name`,`TIN`," +
            "`city`,`street`,`number_of_building`)VALUES(?,?,?,?,?,?,?,?,?);";

    public static final String UPDATE_USER = "UPDATE `user`" +
            "SET" +
            "`email` = ?," +
            "`password` = ?," +
            "`entity` = ?," +
            "`first_name` = ?," +
            "`last_name` = ?," +
            "`TIN` = ?," +
            "`city` = ?," +
            "`street` = ?," +
            "`number_of_building` = ?" +
            "WHERE `idUser` = ?;";

    public static final String DELETE_BY_ID = "DELETE FROM `user` WHERE idUser = ?;";
    public static final String GET_ALL_USERS = "SELECT * FROM user";
    public static final String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email LIKE ?";
    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE idUser = ?";

}

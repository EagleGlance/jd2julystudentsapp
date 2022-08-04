package com.noirix.repository;

import com.noirix.domain.User;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public static final String POSTRGES_DRIVER_NAME = "org.postgresql.Driver";
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:";
    public static final int DATABASE_PORT = 5432;
    public static final String DATABASE_NAME = "/jd2";
    public static final String DATABASE_LOGIN = "postgres";
    public static final String DATABASE_PASSWORD = "root";
    //public static final String DATABASE_PASSWORD = "postgres";

    private static final String ID = "id";
    private static final String NAME = "user_name";
    private static final String SURNAME = "surname";
    private static final String BIRTH_DATE = "birth";
    private static final String CREATED = "creation_date";
    private static final String CHANGED = "modification_date";
    private static final String WEIGHT = "weight";

    public List<User> findAll() {
        final String findAllQuery = "select * from carshop.users order by id limit 10";

        List<User> result = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            Class.forName(POSTRGES_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String jdbcURL = StringUtils.join(DATABASE_URL, DATABASE_PORT, DATABASE_NAME);

        try {
            connection = DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);
            statement = connection.createStatement();
            rs = statement.executeQuery(findAllQuery);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(ID));
                user.setUserName(rs.getString(NAME));
                user.setSurname(rs.getString(SURNAME));
                user.setBirth(rs.getTimestamp(BIRTH_DATE));
                user.setCreationDate(rs.getTimestamp(CREATED));
                user.setModificationDate(rs.getTimestamp(CHANGED));
                user.setWeight(rs.getDouble(WEIGHT));

                result.add(user);
            }

            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

}

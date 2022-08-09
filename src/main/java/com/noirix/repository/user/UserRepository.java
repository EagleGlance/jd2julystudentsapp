package com.noirix.repository.user;

import com.noirix.domain.User;
import com.noirix.util.DatabasePropertiesReader;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.noirix.repository.user.UserTableColumns.ID;
import static com.noirix.repository.user.UserTableColumns.NAME;
import static com.noirix.repository.user.UserTableColumns.SURNAME;
import static com.noirix.repository.user.UserTableColumns.BIRTH_DATE;
import static com.noirix.repository.user.UserTableColumns.CREATED;
import static com.noirix.repository.user.UserTableColumns.CHANGED;
import static com.noirix.repository.user.UserTableColumns.WEIGHT;

import static com.noirix.util.DatabasePropertiesReader.POSTRGES_DRIVER_NAME;
import static com.noirix.util.DatabasePropertiesReader.DATABASE_URL;
import static com.noirix.util.DatabasePropertiesReader.DATABASE_PORT;
import static com.noirix.util.DatabasePropertiesReader.DATABASE_NAME;
import static com.noirix.util.DatabasePropertiesReader.DATABASE_LOGIN;
import static com.noirix.util.DatabasePropertiesReader.DATABASE_PASSWORD;

public class UserRepository implements UserRepositoryInterface {

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.empty();
    }

    public List<User> findAll() {
        final String findAllQuery = "select * from carshop.users order by id limit 10";

        List<User> result = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findAllQuery);

            while (rs.next()) {
                result.add(userRowMapping(rs));
            }

            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    private Connection getConnection() throws SQLException {
        try {
            String driver = DatabasePropertiesReader.getProperty(POSTRGES_DRIVER_NAME);

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String url = DatabasePropertiesReader.getProperty(DATABASE_URL);
        String port = DatabasePropertiesReader.getProperty(DATABASE_PORT);
        String dbName = DatabasePropertiesReader.getProperty(DATABASE_NAME);
        String login = DatabasePropertiesReader.getProperty(DATABASE_LOGIN);
        String password = DatabasePropertiesReader.getProperty(DATABASE_PASSWORD);

        String jdbcURL = StringUtils.join(url, port, dbName);

        return DriverManager.getConnection(jdbcURL, login, password);
    }

    private User userRowMapping(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getLong(ID));
        user.setUserName(rs.getString(NAME));
        user.setSurname(rs.getString(SURNAME));
        user.setBirth(rs.getTimestamp(BIRTH_DATE));
        user.setCreationDate(rs.getTimestamp(CREATED));
        user.setModificationDate(rs.getTimestamp(CHANGED));
        user.setWeight(rs.getDouble(WEIGHT));

        return user;
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        return null;
    }

    @Override
    public User create(User object) {
        return null;
    }

    @Override
    public User update(User object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public Map<String, String> getUserStats() {
        return null;
    }
}

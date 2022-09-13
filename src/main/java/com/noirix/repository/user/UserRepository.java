package com.noirix.repository.user;

import com.noirix.domain.User;
import com.noirix.exception.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.noirix.repository.columns.UserTableColumns.BIRTH_DATE;
import static com.noirix.repository.columns.UserTableColumns.CHANGED;
import static com.noirix.repository.columns.UserTableColumns.CREATED;
import static com.noirix.repository.columns.UserTableColumns.ID;
import static com.noirix.repository.columns.UserTableColumns.IS_DELETED;
import static com.noirix.repository.columns.UserTableColumns.NAME;
import static com.noirix.repository.columns.UserTableColumns.SURNAME;
import static com.noirix.repository.columns.UserTableColumns.WEIGHT;
import static com.noirix.util.UUIDGenerator.generateUUID;

@Repository
//@Primary
@RequiredArgsConstructor
public class UserRepository implements UserRepositoryInterface {

    private static final Logger log = Logger.getLogger(UserRepository.class);

    @Override
    public User findById(Long id) {
        final String findByIdQuery = "select * from carshop.users where id = " + id;

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findByIdQuery);
            boolean hasRow = rs.next();
            if (hasRow) {
                return userRowMapping(rs);
            } else {
                throw new NoSuchEntityException("Entity User with id " + id + " does not exist", 404, generateUUID());
            }
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            throw new RuntimeException("DB connection process issues");
        }
    }

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(findById(id));
    }

    public List<User> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    private Connection getConnection() throws SQLException {
        try {
            String driver = "databaseProperties.getDriverName()";

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.error("JDBC Driver Cannot be loaded!", e);
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String url = "databaseProperties.getUrl()";
        String login = "databaseProperties.getLogin()";
        String password = "databaseProperties.getPassword()";

        return DriverManager.getConnection(url, login, password);
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
        user.setIsDeleted(rs.getBoolean(IS_DELETED));

        return user;

/*        return User.builder()
                .id(rs.getLong(ID))
                .userName(rs.getString(NAME))
                .surname(rs.getString(SURNAME))
                .birth(rs.getTimestamp(BIRTH_DATE))
                .creationDate(rs.getTimestamp(CREATED))
                .modificationDate(rs.getTimestamp(CHANGED))
                .weight(rs.getDouble(WEIGHT))
                .build();*/
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        final String findAllQuery = "select * from carshop.users order by id limit " + limit + " offset " + offset;

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
            log.error("DB connection process issues", e);
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public User create(User object) {
        final String insertQuery =
                "insert into carshop.users (user_name, surname, birth, is_deleted, creation_date, modification_date, weight) " +
                        " values (?, ?, ?, ?, ?, ?, ?);";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(insertQuery);

            statement.setString(1, object.getUserName());
            statement.setString(2, object.getSurname());
            statement.setTimestamp(3, object.getBirth());
            statement.setBoolean(4, object.getIsDeleted());
            statement.setTimestamp(5, object.getCreationDate());
            statement.setTimestamp(6, object.getModificationDate());
            statement.setDouble(7, object.getWeight());

            //executeUpdate - for INSERT, UPDATE, DELETE
            statement.executeUpdate();
            //For select
            //statement.executeQuery();

            /*Get users last insert id for finding new object in DB*/
            ResultSet resultSet = connection.prepareStatement("SELECT currval('carshop.users_id_seq') as last_id").executeQuery();
            resultSet.next();
            long userLastInsertId = resultSet.getLong("last_id");

            return findById(userLastInsertId);
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public User update(User object) {
        final String updateQuery =
                "update carshop.users " +
                        "set " +
                        "user_name = ?, surname = ?, birth = ?, is_deleted = ?, creation_date = ?, modification_date = ?, weight = ? " +
                        " where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(updateQuery);

            statement.setString(1, object.getUserName());
            statement.setString(2, object.getSurname());
            statement.setTimestamp(3, object.getBirth());
            statement.setBoolean(4, object.getIsDeleted());
            statement.setTimestamp(5, object.getCreationDate());
            statement.setTimestamp(6, object.getModificationDate());
            statement.setDouble(7, object.getWeight());
            statement.setLong(8, object.getId());

            statement.executeUpdate();

            return findById(object.getId());
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Long delete(Long id) {
        final String deleteQuery =
                "delete from carshop.users where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);
            statement.executeUpdate();

            return id;
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Map<String, Object> getUserStats() {
        final String callFunction =
                "select carshop.get_users_stats_average_weight(?)";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(callFunction);
            statement.setBoolean(1, true);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            double functiionCall = resultSet.getDouble(1);

            return Collections.singletonMap("avg", functiionCall);
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }
}

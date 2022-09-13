package com.noirix.repository.jdbctemplate;

import com.noirix.domain.User;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.noirix.repository.columns.UserTableColumns.BIRTH_DATE;
import static com.noirix.repository.columns.UserTableColumns.CHANGED;
import static com.noirix.repository.columns.UserTableColumns.CREATED;
import static com.noirix.repository.columns.UserTableColumns.ID;
import static com.noirix.repository.columns.UserTableColumns.IS_DELETED;
import static com.noirix.repository.columns.UserTableColumns.LOGIN;
import static com.noirix.repository.columns.UserTableColumns.NAME;
import static com.noirix.repository.columns.UserTableColumns.PASSWORD;
import static com.noirix.repository.columns.UserTableColumns.SURNAME;
import static com.noirix.repository.columns.UserTableColumns.WEIGHT;

@Component
public class UserRowMapper implements RowMapper<User> {

    private static final Logger log = Logger.getLogger(UserRowMapper.class);

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        log.info("User row mapping start");

        User user = new User();

        user.setId(rs.getLong(ID));
        user.setUserName(rs.getString(NAME));
        user.setSurname(rs.getString(SURNAME));
        user.setBirth(rs.getTimestamp(BIRTH_DATE));
        user.setCreationDate(rs.getTimestamp(CREATED));
        user.setModificationDate(rs.getTimestamp(CHANGED));
        user.setWeight(rs.getDouble(WEIGHT));
        user.setIsDeleted(rs.getBoolean(IS_DELETED));
        user.setLogin(rs.getString(LOGIN));
        user.setPassword(rs.getString(PASSWORD));

        log.info("User row mapping end");
        return user;
    }
}

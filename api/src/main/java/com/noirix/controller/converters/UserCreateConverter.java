package com.noirix.controller.converters;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.hibernate.Credentials;
import com.noirix.domain.hibernate.HibernateUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class UserCreateConverter extends UserBaseConverter<UserCreateRequest, HibernateUser> {

    @Override
    public HibernateUser convert(UserCreateRequest source) {

        HibernateUser hibernateUser = new HibernateUser();

        hibernateUser.setCreationDate(new Timestamp(new Date().getTime()));

        Credentials credentials = new Credentials(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10)
        );

        hibernateUser.setCredentials(credentials);

        return doConvert(hibernateUser, source);
    }
}

package com.noirix.controller.converters;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.hibernate.Credentials;
import com.noirix.domain.hibernate.HibernateUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserCreateConverter extends UserBaseConverter<UserCreateRequest, HibernateUser> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public HibernateUser convert(UserCreateRequest source) {

        HibernateUser hibernateUser = new HibernateUser();

        hibernateUser.setCreationDate(new Timestamp(new Date().getTime()));


        String simplePassword = RandomStringUtils.randomAlphabetic(10);
        System.out.println(simplePassword);

        Credentials credentials = new Credentials(
                RandomStringUtils.randomAlphabetic(10),
                passwordEncoder.encode(simplePassword)
        );

        hibernateUser.setCredentials(credentials);

        return doConvert(hibernateUser, source);
    }
}

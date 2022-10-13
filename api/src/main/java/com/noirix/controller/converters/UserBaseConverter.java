package com.noirix.controller.converters;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.hibernate.Credentials;
import com.noirix.domain.hibernate.HibernateUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class UserBaseConverter<S, T> implements Converter<S, T> {

    public HibernateUser doConvert(HibernateUser userForUpdate, UserCreateRequest request) {

        userForUpdate.setUserName(request.getUserName());
        userForUpdate.setSurname(request.getSurname());
        userForUpdate.setBirth(request.getBirth());
        userForUpdate.setWeight(request.getWeight());

        /*System fields filling*/
        userForUpdate.setModificationDate(new Timestamp(new Date().getTime()));
        userForUpdate.setIsDeleted(false);
        
        return userForUpdate;
    }

}

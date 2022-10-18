package com.noirix.controller.converters;

import com.noirix.controller.requests.RoleRequest;
import com.noirix.domain.hibernate.HibernateRole;
import com.noirix.domain.hibernate.HibernateUser;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class RoleBaseConverter implements Converter<RoleRequest, HibernateRole> {
    @Override
    public HibernateRole convert(RoleRequest source) {
        System.out.println("In Hibernate role converter");
        return new HibernateRole();
    }
}

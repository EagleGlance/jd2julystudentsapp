package com.noirix.controller.converters;

import com.noirix.controller.requests.UserChangeRequest;
import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.springdata.UserSpringDataRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserChangeConverter extends UserBaseConverter<UserChangeRequest, HibernateUser> {

    private final UserSpringDataRepository repository;

    @Override
    public HibernateUser convert(UserChangeRequest source) {

        Optional<HibernateUser> user = repository.findById(source.getId());
        return doConvert(user.get(), source);
    }
}

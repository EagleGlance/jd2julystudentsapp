package com.noirix.repository.hibernate;

import com.noirix.domain.User;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.CRUDRepository;

import java.util.Map;
import java.util.Optional;

public interface HibernateUserInterface extends CRUDRepository<Long, HibernateUser> {

    Map<String, Object> getUserStats();

    Optional<User> findByLogin(String login);
}

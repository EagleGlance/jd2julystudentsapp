package com.noirix.repository.user;

import com.noirix.domain.User;
import com.noirix.repository.CRUDRepository;

import java.util.Map;
import java.util.Optional;

public interface UserRepositoryInterface extends CRUDRepository<Long, User> {

    Map<String, Object> getUserStats();

    Optional<User> findByLogin(String login);

}

package com.noirix.repository;

import com.noirix.domain.User;

import java.util.Map;

public interface UserRepositoryInterface extends CRUDRepository<Long, User> {

    Map<String, String> getUserStats();

}

package com.noirix.service;

import com.noirix.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> findAll();

    Map<String, Object> getUserStats();

    User create(User object);
}

package com.noirix.repository.jdbctemplate;

import com.noirix.domain.Role;
import com.noirix.repository.CRUDRepository;

import java.util.List;

public interface RoleRepositoryInterface extends CRUDRepository<Long, Role> {
    List<Role> findRolesByUserId(Long userId);
}

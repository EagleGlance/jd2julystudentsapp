package com.noirix.repository.springdata;

import com.noirix.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserSpringDataRepository extends CrudRepository<HibernateUser, Long>, JpaRepository<HibernateUser, Long>, PagingAndSortingRepository<HibernateUser, Long> {
}

package com.noirix.repository.springdata;

import com.noirix.domain.Gender;
import com.noirix.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface UserSpringDataRepository extends CrudRepository<HibernateUser, Long>, JpaRepository<HibernateUser, Long>, PagingAndSortingRepository<HibernateUser, Long> {

    HibernateUser findByIdAndGender(Long id, Gender gender);

    HibernateUser findByCredentialsLogin(String login);

    List<HibernateUser> findByCredentialsLoginAndUserNameAndBirth(String login, String name, Timestamp birthDate);

    List<HibernateUser> findByCredentialsLoginAndUserNameOrBirthOrderByIdDescUserNameDesc(String login, String name, Timestamp birthDate);

    List<HibernateUser> findByIsDeletedOrderByIdDesc(Boolean isDeleted);

    //select * from m_users where (login = ? and name = ?) or birth_date = ?

    @Query(value = "select u from HibernateUser u")
    List<HibernateUser> findByHQLQuery();

    @Query(value = "select * from carshop.users", nativeQuery = true)
    List<HibernateUser> findByHQLQueryNative();

    @Query(value = "select u from HibernateUser u where u.credentials.login = :login and u.userName = :userName")
    List<HibernateUser> findByHQLQuery(String login, @Param("userName") String name);

    @Query("select u.id, u.userName from HibernateUser u")
    List<Object[]> getPartsOfUser();


    @Modifying
    @Query(value = "insert into carshop.l_role_user(user_id, role_id) values (:user_id, :role_id)", nativeQuery = true)
    int createRoleRow(@Param("user_id") Long userId, @Param("role_id") Long roleId);
}

package com.noirix.domain.hibernate;

import com.noirix.domain.Gender;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "users")
public class HibernateUser {

    @Id
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column
    private String surname;

    @Column
    private Timestamp birth;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "modification_date")
    private Timestamp modificationDate;

    @Column
    private Double weight;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
}

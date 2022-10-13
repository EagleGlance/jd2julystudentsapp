package com.noirix.controller.requests;

import com.noirix.domain.Gender;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserCreateRequest {

    private String userName;

    private String surname;

    private Timestamp birth;

    private Double weight;

    private Gender gender;

}

package com.noirix.controller.requests;

import com.noirix.domain.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@ApiModel(description = "Create user object without system info")
public class UserCreateRequest {

    @ApiModelProperty(required = true, allowableValues = "slavka", dataType = "string", notes = "user's login")
    @NotNull
    @Size(min = 2, max = 20)
    private String userName;

    @ApiModelProperty(required = true, allowableValues = "kalevich", dataType = "string", notes = "user's surname")
    @NotNull
    @Size(min = 2, max = 50)
    private String surname;

    @ApiModelProperty(required = true, allowableValues = "1665685166000", dataType = "timestamp", notes = "user's birth")
    private Timestamp birth;

    @ApiModelProperty(required = true, allowableValues = "86", dataType = "double", notes = "user's weight")
    private Double weight;

    @ApiModelProperty(required = true, allowableValues = "MALE, FEMALE, NOT_SELECTED", dataType = "string", notes = "user's gender")
    private Gender gender;

}

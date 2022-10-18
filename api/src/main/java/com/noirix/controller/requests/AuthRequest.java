package com.noirix.controller.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Login request")
public class AuthRequest {

    @ApiModelProperty(required = true, allowableValues = "Slavka_Kalevich_4", dataType = "string", notes = "user's login")
    private String login;

    @ApiModelProperty(required = true, allowableValues = "default_password", dataType = "string", notes = "user's password")
    private String password;
}

package com.noirix.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {

    private String login;

    @JsonIgnore
    private String password;
}

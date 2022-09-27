package com.noirix.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchCriteria {

    private String userName;

    private String surname;

    private Long lowerBoundUserId;

    private Integer limit;

    private Integer offset;
}

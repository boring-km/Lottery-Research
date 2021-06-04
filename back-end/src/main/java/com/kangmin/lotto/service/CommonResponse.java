package com.kangmin.lotto.service;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class CommonResponse {

    private String message;
    private int status;
    private String code;

}

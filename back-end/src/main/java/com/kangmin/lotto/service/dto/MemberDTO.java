package com.kangmin.lotto.service.dto;

import com.kangmin.lotto.security.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDTO {

    private String id;
    private String userName;
    private String email;
    private Role role;
}

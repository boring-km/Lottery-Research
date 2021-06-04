package com.kangmin.lotto.service;

import com.kangmin.lotto.security.AuthToken;
import com.kangmin.lotto.service.dto.MemberDTO;

import java.util.Optional;

public interface LoginUseCase {
    Optional<MemberDTO> login(String id, String password);
    AuthToken createAuthToken(MemberDTO memberDTO);
}

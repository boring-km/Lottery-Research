package com.kangmin.lotto.security;

import java.util.Date;

public interface AuthTokenProvider<T> {
    T createAuthToken(String id, String role, Date expireDate);
    T convertAuthToken(String token);
}

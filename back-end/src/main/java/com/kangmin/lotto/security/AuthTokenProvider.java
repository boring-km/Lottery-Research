package com.kangmin.lotto.provider.security;

import java.util.Date;

public interface AuthTokenProvider<T> {
    T createAuthToken(String id, String role, Date expireDate);
    T convertAuthToken(String token);
}

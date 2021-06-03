package com.kangmin.lotto.provider.security;

public interface AuthToken<T> {
    boolean validate();
    T getData();
}

package com.kangmin.lotto.security;

public interface AuthToken<T> {
    boolean validate();
    T getData();
}

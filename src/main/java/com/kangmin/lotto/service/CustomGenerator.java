package com.kangmin.lotto.service;

import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class CustomGenerator implements LottoGenerator {

    private Set<Integer> customNumberSet;

    @Override
    public Set<Integer> make() {
        return customNumberSet;
    }
}

package com.kangmin.lotto.service;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class CustomGenerator implements LottoGenerator {

    private final int[] customNumberSet;

    @Override
    public Set<Integer> make() {
        HashSet<Integer> result = new HashSet<>();
        Arrays.stream(customNumberSet).forEach(result::add);
        return result;
    }
}

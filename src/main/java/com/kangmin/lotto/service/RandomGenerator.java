package com.kangmin.lotto.service;

import java.util.HashSet;
import java.util.Set;

public class RandomGenerator implements LottoGenerator {

    @Override
    public Set<Integer> make() {
        Set<Integer> lottoSet = new HashSet<>();
        while (lottoSet.size() < 6) {
            lottoSet.add(getRandomNumber());
        }
        return lottoSet;
    }

    private static int getRandomNumber() {
        double min = 1;
        double max = 45;
        return (int) ((Math.random() * (max - min)) + min);
    }
}

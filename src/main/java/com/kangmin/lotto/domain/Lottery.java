package com.kangmin.lotto.domain;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
public class Lottery {
    private final Set<Integer> lottoSet;
    private static final int SIZE = 6;

    @Override
    public String toString() {
        return "Lottery{" + lottoSet + '}';
    }

    public int[] getSortedNumbers() {
        ArrayList<Integer> lottoList = new ArrayList<>(lottoSet);
        Collections.sort(lottoList);
        int[] result = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            result[i] = lottoList.get(i);
        }
        return result;
    }
}

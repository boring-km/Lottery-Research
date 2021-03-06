package com.kangmin.lotto.domain;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
public class Lottery {
    private final Set<Integer> lotterySet;
    private static final int SIZE = 6;

    @Override
    public String toString() {
        return "Lottery{" + lotterySet + '}';
    }

    public int[] getSortedNumbers() {
        ArrayList<Integer> lottery = new ArrayList<>(lotterySet);
        Collections.sort(lottery);
        int[] result = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            result[i] = lottery.get(i);
        }
        return result;
    }

    public int getSumOfNumbers() {
        int result = 0;
        for (int number : lotterySet) {
            result += number;
        }
        return result;
    }
}

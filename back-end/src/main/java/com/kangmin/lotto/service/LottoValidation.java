package com.kangmin.lotto.service;

import java.util.Set;

public class LottoValidation {
    private static final int SIZE = 6;

    public static boolean check(Set<Integer> target) {
        if (target.size() != SIZE) return false;
        for (int number: target)
            if (number < 1 || 45 < number)
                return false;
        return true;
    }
}

package com.kangmin.lotto.service;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class LottoValidationTest {

    @Test
    public void 서로_다른_로또번호가_6개여야_생성이_가능하다() {
        Set<Integer> setHavingSameNumbers = new HashSet<>(List.of(7, 7, 7, 7, 7, 7));
        Set<Integer> setHavingOverCount = Set.of(1, 2, 3, 4, 5, 6, 7);
        Set<Integer> setHavingUnderCount = Set.of(1, 2, 3, 4, 5);
        Set<Integer> setHavingDistinctNumbers = Set.of(1, 2, 3, 4, 5, 6);

        System.out.println("번호가 전부 7로 동일한 번호로 구성될 때 실제 로또 번호 길이: " + setHavingSameNumbers.size());
        System.out.println("번호가 전부 다른 7개의 번호로 구성될 때 실제 로또 번호 길이: " + setHavingOverCount.size());
        System.out.println("번호가 전부 다른 5개의 번호로 구성될 때 실제 로또 번호 길이: " + setHavingUnderCount.size());
        System.out.println("번호가 전부 다른 6개의 번호로 구성될 때 실제 로또 번호 길이: " + setHavingSameNumbers.size());

        boolean isLottoHavingSameNumbers = LottoValidation.check(setHavingSameNumbers);
        boolean isLottoHavingOverCount = LottoValidation.check(setHavingOverCount);
        boolean isLottoHavingUnderCount = LottoValidation.check(setHavingUnderCount);
        boolean isLottoHavingDistinctNumbers = LottoValidation.check(setHavingDistinctNumbers);
        
        assertFalse(isLottoHavingSameNumbers);
        assertFalse(isLottoHavingOverCount);
        assertFalse(isLottoHavingUnderCount);
        assertTrue(isLottoHavingDistinctNumbers);
    }

    @Test
    public void 로또번호는_1에서_45까지의_숫자만을_포함해야_한다() {
        Set<Integer> setHavingZero = Set.of(0, 1, 3, 4, 6, 7);
        Set<Integer> setHavingFifty = Set.of(1, 4, 20, 30, 40, 50);
        Set<Integer> setFromOneToFortyFive = Set.of(1, 10, 24, 38, 40, 45);

        boolean isLottoHavingZero = LottoValidation.check(setHavingZero);
        boolean isLottoHavingFifty = LottoValidation.check(setHavingFifty);
        boolean isLottoFromOneToFortyFive = LottoValidation.check(setFromOneToFortyFive);

        assertFalse(isLottoHavingZero);
        assertFalse(isLottoHavingFifty);
        assertTrue(isLottoFromOneToFortyFive);
    }
}
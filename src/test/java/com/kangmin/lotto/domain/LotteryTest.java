package com.kangmin.lotto.domain;

import com.kangmin.lotto.service.RandomGenerator;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class LotteryTest {

    @Test
    public void Lottery_객체를_RandomGenerator로_생성하면_Null이_아니다() {
        Lottery lottery = LottoFactory.generate(new RandomGenerator());

        int[] result = lottery.getSortedNumbers();
        System.out.println(Arrays.toString(result));

        assertNotNull(result);
    }
}
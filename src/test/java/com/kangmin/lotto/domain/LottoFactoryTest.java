package com.kangmin.lotto.domain;

import com.kangmin.lotto.service.CustomGenerator;
import com.kangmin.lotto.service.LottoValidation;
import com.kangmin.lotto.service.RandomGenerator;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class LottoFactoryTest {

    @Test
    public void 로또번호_랜덤생성_테스트() {
        Lottery lotto = LottoFactory.generate(new RandomGenerator());
        System.out.println(lotto);
        assertNotNull(lotto);
    }

    @Test
    public void 로또번호_직접생성_테스트() {
        Lottery lotto = LottoFactory.generate(
                new CustomGenerator(Set.of(6, 32, 22, 15, 42, 24)));
        System.out.println(lotto);
        assertNotNull(lotto);
    }
}
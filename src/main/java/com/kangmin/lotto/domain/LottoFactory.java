package com.kangmin.lotto.domain;

import com.kangmin.lotto.service.LottoGenerator;
import com.kangmin.lotto.service.LottoValidation;

import java.util.Set;

public class LottoFactory {

    public static Set<Integer> generate(LottoGenerator lottoGenerator) {
        Set<Integer> generatedNumbers = lottoGenerator.make();

        if (LottoValidation.check(generatedNumbers))
            return generatedNumbers;
        else
            throw new IllegalArgumentException("잘못된 방식으로 로또 번호가 입력되었습니다.");
    }
}

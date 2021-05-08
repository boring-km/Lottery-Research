package com.kangmin.lotto.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kangmin.lotto.domain.LottoRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LottoInfoSearchingTest {

    @Autowired
    private LottoInfoSearching lottoInfoSearching;

    @Test
    public void 로또_최신회_번호를_호출하면_0이_아니다() {
        int unexpected = 0;
        int actual = 0;

        try {
            actual = lottoInfoSearching.getRecentRound();
            System.out.println(actual);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void 로또_234회의_결과를_받아오면_null이_아니다() throws JsonProcessingException {
        int round = 234;

        LottoRecord record = lottoInfoSearching.getLottoByRound(round);
        System.out.println(record);

        assertNotNull(record);
    }
}
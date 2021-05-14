package com.kangmin.lotto.service.api;

import com.kangmin.lotto.domain.LottoRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LottoSearchingTest {

    @Autowired
    private LottoSearching lottoSearching;

    @Test
    public void 로또_최신회_번호를_호출하면_0이_아니다() {
        int unexpected = 0;
        int actual = 0;

        try {
            actual = lottoSearching.getRecentRound();
            System.out.println(actual);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotEquals(unexpected, actual);
    }

    @Test
    public void 로또_234회의_결과를_받아오면_null이_아니다() {
        int round = 234;

        LottoRecord record = lottoSearching.getLottoByRound(round);
        System.out.println(record);

        assertNotNull(record);
    }

    @Test
    public void 최근회차까지의_모든_로또_정보를_받아오면_null이_아니다() {
        List<LottoRecord> resultList = lottoSearching.getAllRecords();
        for (LottoRecord lotto : resultList) {
            System.out.println(lotto);
        }
        assertNotNull(resultList);
    }
}
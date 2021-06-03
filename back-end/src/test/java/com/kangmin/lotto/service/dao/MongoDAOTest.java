package com.kangmin.lotto.service.dao;

import com.kangmin.lotto.domain.LottoRecord;
import com.kangmin.lotto.service.api.LottoSearching;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDAOTest {

    @Resource
    private MongoDAO mongoDAO;
    @Resource
    private LottoSearching lottoSearching;

    @Test
    public void 첫회차부터_최신화까지_로또정보_저장() {
        List<LottoRecord> recordList = lottoSearching.getAllRecords();
        int expected = recordList.size();

        mongoDAO.saveAll(recordList);
        List<LottoRecord> resultList = mongoDAO.getAllRecords();
        int actual = resultList.size();

        assertEquals(expected, actual);
    }

    @Test
    public void 로또의_각_번호별로_나온횟수는_120회_이상_180회_미만이다() {
        int minimum = Integer.MAX_VALUE;
        int maximum = 0;

        Map<Integer, Integer> result = mongoDAO.getNumbersCount();
        for (int key : result.keySet()) {
            System.out.println(key + "번: " + result.get(key) + " 회");
            minimum = Math.min(minimum, result.get(key));
            maximum = Math.max(maximum, result.get(key));
        }
        System.out.println("최소: " + minimum + " 최대: " + maximum);

        assertTrue(120 <= minimum && maximum < 180);
    }

    @Test
    public void 회차별로_로또번호_합의_평균을_구하면_150이상_170미만이다() {
        int result = mongoDAO.getSumByRound().getAverage();

        System.out.println(result);

        assertTrue(150 <= result && result < 170);
    }
}

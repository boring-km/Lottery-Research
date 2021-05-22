package com.kangmin.lotto.service.dao;

import com.kangmin.lotto.domain.LottoRecord;
import com.kangmin.lotto.service.api.LottoSearching;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

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

}

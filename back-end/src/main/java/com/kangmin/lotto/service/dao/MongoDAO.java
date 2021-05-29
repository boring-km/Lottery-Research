package com.kangmin.lotto.service.dao;

import com.kangmin.lotto.domain.LottoRecord;
import com.kangmin.lotto.domain.LottoSum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MongoDAO {
    private final MongoTemplate mongoTemplate;

    public void saveAll(List<LottoRecord> lottoRecordList) {
        List<LottoRecord> allRecords = getAllRecords();
        int n = allRecords.size();
        boolean[] roundNumbers = new boolean[n + 1];
        allRecords.forEach(record -> roundNumbers[record.getDrwNo()] = true);   // 이미 값이 있는 회차는 true
        lottoRecordList.forEach(lottoRecord -> {
            if (roundNumbers.length < n + 1 || n == 0) {    // 저장된 회차가 없으면 save
                mongoTemplate.save(lottoRecord);
            } else {
                if (!roundNumbers[lottoRecord.getDrwNo()]) {
                    mongoTemplate.save(lottoRecord);
                }
            }
        });
    }

    public List<LottoRecord> getAllRecords() {
        return mongoTemplate.findAll(LottoRecord.class);
    }

    public Map<Integer, Integer> getNumbersCount() {
        List<LottoRecord> all = getAllRecords();
        Map<Integer, Integer> result = new HashMap<>();

        all.forEach(lottoRecord -> addCountInMap(result, new int[]{
                lottoRecord.getDrwtNo1(),
                lottoRecord.getDrwtNo2(),
                lottoRecord.getDrwtNo3(),
                lottoRecord.getDrwtNo4(),
                lottoRecord.getDrwtNo5(),
                lottoRecord.getDrwtNo6(),
                lottoRecord.getBnusNo()}));

        return result;
    }

    private void addCountInMap(Map<Integer, Integer> map, int[] values) {
        for (int value : values) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
    }

    public LottoSum getSumByRound() {
        List<LottoRecord> all = getAllRecords();
        int sumResult = 0;
        int minimum = 255;
        int maximum = 21;
        for (LottoRecord lottoRecord : all) {
            int sum = lottoRecord.getDrwtNo1() +
                    lottoRecord.getDrwtNo2() +
                    lottoRecord.getDrwtNo3() +
                    lottoRecord.getDrwtNo4() +
                    lottoRecord.getDrwtNo5() +
                    lottoRecord.getDrwtNo6() +
                    lottoRecord.getBnusNo();
            minimum = Math.min(minimum, sum);
            maximum = Math.max(maximum, sum);
            sumResult += sum;
        }
        return LottoSum.builder()
                .minimum(minimum)
                .average(sumResult / all.size())
                .maximum(maximum).build();
    }
}

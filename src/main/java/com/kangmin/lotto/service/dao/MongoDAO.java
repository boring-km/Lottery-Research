package com.kangmin.lotto.service.dao;

import com.kangmin.lotto.domain.LottoRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoDAO {
    private final MongoTemplate mongoTemplate;

    public void saveAll(List<LottoRecord> lottoRecordList) {
        List<LottoRecord> allRecords = getAllRecords();
        int n = allRecords.size();
        boolean[] roundNumbers = new boolean[n +1];
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

}

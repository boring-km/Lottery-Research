package com.kangmin.lotto.domain;

import com.kangmin.lotto.service.api.LottoSearching;
import com.kangmin.lotto.service.dao.MongoDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LottoSearchController {

    private final LottoSearching searching;
    private final MongoDAO mongoDAO;

    @GetMapping("/lotto/search/{round}")
    public LottoRecord searchRecord(@PathVariable int round) {
        return searching.getLottoByRound(round);
    }

    @GetMapping("/lotto/recent")
    public int searchRecentRound() {
        return searching.getRecentRound();
    }

    @GetMapping("/lotto/average")
    public int getAverageSumByRound() {
        return mongoDAO.getAverageSumByRound();
    }
}

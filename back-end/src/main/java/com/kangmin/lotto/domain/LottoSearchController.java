package com.kangmin.lotto.domain;

import com.kangmin.lotto.service.api.LottoSearching;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LottoSearchController {

    private final LottoSearching searching;

    @GetMapping("/lotto/search/{round}")
    public LottoRecord searchRecord(@PathVariable int round) {
        return searching.getLottoByRound(round);
    }

}

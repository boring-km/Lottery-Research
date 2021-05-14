package com.kangmin.lotto.domain;

import com.kangmin.lotto.service.CustomGenerator;
import com.kangmin.lotto.service.RandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RequiredArgsConstructor
@RestController
public class LottoGenerativeController {

    private final RandomGenerator randomGenerator;

    @GetMapping("/lotto/random")
    public Lottery getRandomLottery() {
        return LottoFactory.generate(randomGenerator);
    }

    @PostMapping("/lotto/custom")
    public Lottery getCustomLottery(@RequestBody LinkedHashMap<String, int[]> requestNumbers) {
        int[] numbers = requestNumbers.get("numbers");
        return LottoFactory.generate(new CustomGenerator(numbers));
    }

}

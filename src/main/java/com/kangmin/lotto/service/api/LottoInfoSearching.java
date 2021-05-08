package com.kangmin.lotto.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kangmin.lotto.domain.LottoRecord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class LottoInfoSearching {

    @Resource
    private WebClient webClient;

    private static final String BASE_URL = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=";

    public LottoRecord getLottoByRound(int round) throws JsonProcessingException {
        String uri = BASE_URL + round;
        String result = webClient.mutate()
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(result, LottoRecord.class);
    }

    public int getRecentRound() throws IOException {
        String url = "https://dhlottery.co.kr/gameResult.do?method=byWin";
        Element document = Jsoup.connect(url).get().body();
        String target = document.getElementsByClass("win_result").get(0).getElementsByTag("strong").get(0).text();
        return Integer.parseInt(target.substring(0, target.length()-1));
    }
}

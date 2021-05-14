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
import java.util.ArrayList;

@Service
public class LottoSearching {

    @Resource
    private WebClient webClient;

    private static final String BASE_URL = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=";

    public LottoRecord getLottoByRound(int round) {
        String uri = BASE_URL + round;
        String result = webClient.mutate()
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        ObjectMapper mapper = new ObjectMapper();
        LottoRecord lottoRecord = LottoRecord.builder().build();
        try {
            lottoRecord = mapper.readValue(result, LottoRecord.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return lottoRecord;
    }

    public ArrayList<LottoRecord> getAllRecords() {
        ArrayList<LottoRecord> resultList = new ArrayList<>();
        try {
            int finalRound = getRecentRound();
            for (int round = 1; round <= finalRound; round++) {
                resultList.add(getLottoByRound(round));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public int getRecentRound() throws IOException {
        String url = "https://dhlottery.co.kr/gameResult.do?method=byWin";
        Element document = Jsoup.connect(url).get().body();
        String target = document.getElementsByClass("win_result").get(0).getElementsByTag("strong").get(0).text();
        return Integer.parseInt(target.substring(0, target.length()-1));
    }
}

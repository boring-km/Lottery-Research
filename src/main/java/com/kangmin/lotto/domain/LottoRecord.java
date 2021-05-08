package com.kangmin.lotto.domain;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@ToString
public class LottoRecord implements Serializable {
    private final long totSellamnt;
    private final long firstAccumamnt;
    private final long firstWinamnt;
    private final long firstPrzwnerCo;
    private final String returnValue;
    private final String drwNoDate;
    private final int drwNo;
    private final int drwtNo1;
    private final int drwtNo2;
    private final int drwtNo3;
    private final int drwtNo4;
    private final int drwtNo5;
    private final int drwtNo6;
    private final int bnusNo;

}

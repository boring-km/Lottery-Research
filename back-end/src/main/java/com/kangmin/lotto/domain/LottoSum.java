package com.kangmin.lotto.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Getter
public class LottoSum implements Serializable {
    private int minimum;
    private int average;
    private int maximum;
}

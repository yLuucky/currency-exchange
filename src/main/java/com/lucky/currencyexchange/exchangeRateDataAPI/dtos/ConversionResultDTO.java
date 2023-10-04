package com.lucky.currencyexchange.exchangeRateDataAPI.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConversionResultDTO {

    @JsonProperty("result")
    private double value;
    private InfoDTO info;
    @JsonProperty("success")
    private boolean isSuccess;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class InfoDTO {

        private double rate;

    }

}

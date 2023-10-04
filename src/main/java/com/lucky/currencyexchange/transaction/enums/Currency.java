package com.lucky.currencyexchange.transaction.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum Currency {

    BRL("brl"),
    USD("usd"),
    EUR("eur"),
    JPY("jpy");

    public String apiValue;

}

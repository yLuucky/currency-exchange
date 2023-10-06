package com.lucky.currencyexchange.transaction.dtos;

import com.lucky.currencyexchange.transaction.enums.Currency;
import com.lucky.currencyexchange.user.infra.db.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class TransactionDTO {

    private UUID transactionId;
    private User user;
    private Currency initialCurrency;
    private Currency finalCurrency;
    private double originalValue;
    private double conversionRate;

    public double getConvertedValue() {
        return originalValue * conversionRate;
    }

}

package com.lucky.currencyexchange.transaction.controllers.views;

import com.lucky.currencyexchange.transaction.dtos.TransactionDTO;
import com.lucky.currencyexchange.transaction.enums.Currency;
import com.lucky.currencyexchange.user.infra.db.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor(staticName = "create")
public class TransactionView {

    private UUID transactionId;
    private User user;
    private Currency initialCurrency;
    private Currency finalCurrency;
    private double originalValue;
    private double convertedValue;
    private double conversionRate;

    public static TransactionView from(TransactionDTO transactionDTO) {
        return TransactionView.create(
                transactionDTO.getTransactionId(),
                transactionDTO.getUser(),
                transactionDTO.getInitialCurrency(),
                transactionDTO.getFinalCurrency(),
                transactionDTO.getOriginalValue(),
                transactionDTO.getConvertedValue(),
                transactionDTO.getConversionRate()
        );
    }

    public static List<TransactionView> from(List<TransactionDTO> transactionDTOS) {
        return transactionDTOS.stream().map(TransactionView::from).collect(Collectors.toList());
    }

}

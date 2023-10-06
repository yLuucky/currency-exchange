package com.lucky.currencyexchange.transaction.mappers;

import com.lucky.currencyexchange.transaction.dtos.TransactionDTO;
import com.lucky.currencyexchange.transaction.infra.db.entities.Transaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionMapper {

    public static Transaction mapper(final TransactionDTO transactionDTO) {
        final Transaction transaction = new Transaction();
        transaction.setUserId(transactionDTO.getUser().getUserId());
        transaction.setInitialCurrency(transactionDTO.getInitialCurrency());
        transaction.setFinalCurrency(transactionDTO.getFinalCurrency());
        transaction.setOriginalValue(transactionDTO.getOriginalValue());
        transaction.setConversionRate(transactionDTO.getConversionRate());

        return transaction;
    }

    public static TransactionDTO mapper(final Transaction transaction) {
        return TransactionDTO.create(transaction.getId(), null, transaction.getInitialCurrency(),
                transaction.getFinalCurrency(), transaction.getOriginalValue(), transaction.getConversionRate());
    }


}

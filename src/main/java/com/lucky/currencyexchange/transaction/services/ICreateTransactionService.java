package com.lucky.currencyexchange.transaction.services;

import com.lucky.currencyexchange.transaction.dtos.TransactionDTO;
import com.lucky.currencyexchange.transaction.infra.db.entities.Transaction;

public interface ICreateTransactionService {

    TransactionDTO execute(TransactionDTO transactionDTO);

}

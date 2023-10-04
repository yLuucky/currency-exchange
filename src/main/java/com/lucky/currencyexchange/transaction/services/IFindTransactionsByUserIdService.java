package com.lucky.currencyexchange.transaction.services;

import com.lucky.currencyexchange.transaction.dtos.TransactionDTO;
import com.lucky.currencyexchange.transaction.infra.db.entities.Transaction;

import java.util.List;
import java.util.UUID;

public interface IFindTransactionsByUserIdService {

    List<TransactionDTO> execute(UUID userId);

}

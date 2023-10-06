package com.lucky.currencyexchange.transaction.services;

import com.lucky.currencyexchange.exchangeRateDataAPI.client.ExchangeRateDataAPIClient;
import com.lucky.currencyexchange.transaction.dtos.TransactionDTO;
import com.lucky.currencyexchange.transaction.exceptions.TransactionConversionException;
import com.lucky.currencyexchange.transaction.infra.db.entities.Transaction;
import com.lucky.currencyexchange.transaction.infra.db.repositories.jpa.TransactionRepository;
import com.lucky.currencyexchange.transaction.mappers.TransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@Service
public class CreateTransactionServiceImpl implements ICreateTransactionService {

    private final TransactionRepository repository;

    @Autowired
    public CreateTransactionServiceImpl(final TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public TransactionDTO execute(final TransactionDTO transactionDTO) {
        try {
            final TransactionDTO convertedTransaction = ExchangeRateDataAPIClient.convert(transactionDTO);
            final Transaction transaction = this.saveTransaction(convertedTransaction);
            log.info("transaction created", transaction);

            convertedTransaction.setTransactionId(transaction.getId());

            return convertedTransaction;
        } catch (IOException | InterruptedException e) {
            log.error("error to create a transaction");
            throw new TransactionConversionException();
        }
    }

    private Transaction saveTransaction(final TransactionDTO transactionDTO) {
        Transaction transaction = TransactionMapper.mapper(transactionDTO);
        transaction.setCreateAt(LocalDate.now());

        return repository.save(transaction);
    }

}

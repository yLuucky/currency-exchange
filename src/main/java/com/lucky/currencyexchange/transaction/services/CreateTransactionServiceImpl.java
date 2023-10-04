package com.lucky.currencyexchange.transaction.services;

import com.lucky.currencyexchange.exchangeRateDataAPI.client.ExchangeRateDataAPIClient;
import com.lucky.currencyexchange.transaction.dtos.TransactionDTO;
import com.lucky.currencyexchange.transaction.infra.db.entities.Transaction;
import com.lucky.currencyexchange.transaction.infra.db.repositories.jpa.TransactionRepository;
import com.lucky.currencyexchange.transaction.mappers.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

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
            convertedTransaction.setTransactionId(transaction.getId());

            return convertedTransaction;
        } catch (IOException | InterruptedException e) {
            //FIXME: threat this exception
            throw new RuntimeException("Could not process your request");
        }
    }

    private Transaction saveTransaction(final TransactionDTO transactionDTO) {
        Transaction transaction = TransactionMapper.mapper(transactionDTO);
        transaction.setCreateAt(LocalDate.now());
        return repository.save(transaction);
    }

}

package com.lucky.currencyexchange.transaction.services;

import com.lucky.currencyexchange.transaction.dtos.TransactionDTO;
import com.lucky.currencyexchange.transaction.infra.db.entities.Transaction;
import com.lucky.currencyexchange.transaction.infra.db.repositories.jpa.TransactionRepository;
import com.lucky.currencyexchange.transaction.mappers.TransactionMapper;
import com.lucky.currencyexchange.user.infra.db.entities.User;
import com.lucky.currencyexchange.user.infra.db.repositories.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FindTransactionsByUserServiceImpl implements IFindTransactionsByUserIdService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public FindTransactionsByUserServiceImpl(final UserRepository userRepository, final TransactionRepository repository) {
        this.userRepository = userRepository;
        this.transactionRepository = repository;
    }

    @Override
    public List<TransactionDTO> execute(final UUID userId) {
        final User user = userRepository.findById(userId).orElseThrow();
        final List<Transaction> transactions = transactionRepository.findAllByUserId(userId);

        return transactions.stream().map(transaction -> this.mapper(transaction, user)).collect(Collectors.toList());
    }

    private TransactionDTO mapper(final Transaction transaction, User user) {

        return TransactionDTO.create(transaction.getId(), user, transaction.getInitialCurrency(),
                transaction.getFinalCurrency(), transaction.getOriginalValue(), transaction.getConversionRate());

    }

}

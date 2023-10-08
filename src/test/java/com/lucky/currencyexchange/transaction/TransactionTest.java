package com.lucky.currencyexchange.transaction;

import com.lucky.currencyexchange.transaction.dtos.TransactionDTO;
import com.lucky.currencyexchange.transaction.enums.Currency;
import com.lucky.currencyexchange.transaction.infra.db.entities.Transaction;
import com.lucky.currencyexchange.transaction.infra.db.repositories.jpa.TransactionRepository;
import com.lucky.currencyexchange.transaction.mappers.TransactionMapper;
import com.lucky.currencyexchange.transaction.services.CreateTransactionServiceImpl;
import com.lucky.currencyexchange.transaction.services.FindTransactionsByUserServiceImpl;
import com.lucky.currencyexchange.user.infra.db.entities.User;
import com.lucky.currencyexchange.user.infra.db.repositories.jpa.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TransactionTest {

    @InjectMocks
    private CreateTransactionServiceImpl createTransactionService;

    @InjectMocks
    private FindTransactionsByUserServiceImpl findTransactionsByUserService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTransactionTest() {
        TransactionDTO transactionDTO = this.createFakeTransaction();
        Transaction transaction = TransactionMapper.mapper(transactionDTO);

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(transactionDTO.getUser()));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        TransactionDTO createdTransaction = createTransactionService.execute(transactionDTO);

        assertEquals(transactionDTO.getInitialCurrency(), createdTransaction.getInitialCurrency());
        assertEquals(transactionDTO.getFinalCurrency(), createdTransaction.getFinalCurrency());
        assertEquals(transactionDTO.getOriginalValue(), createdTransaction.getOriginalValue());
        assertEquals(transactionDTO.getConversionRate(), createdTransaction.getConversionRate());
    }

    @Test
    public void findTransactionsByUserTest() {
        TransactionDTO transactionDTO = this.createFakeTransaction();
        Transaction transaction = TransactionMapper.mapper(transactionDTO);

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(transactionDTO.getUser()));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        TransactionDTO createdTransaction = createTransactionService.execute(transactionDTO);

        assertEquals(transactionDTO.getInitialCurrency(), createdTransaction.getInitialCurrency());
        assertEquals(transactionDTO.getFinalCurrency(), createdTransaction.getFinalCurrency());
        assertEquals(transactionDTO.getOriginalValue(), createdTransaction.getOriginalValue());
        assertEquals(transactionDTO.getConversionRate(), createdTransaction.getConversionRate());

        findTransactionsByUserService.execute(transactionDTO.getUser().getUserId());

        verify(transactionRepository, times(1)).findAllByUserId(any(UUID.class));
    }

    private User createFakeUser() {
        User user = new User();
        user.setUserId(UUID.randomUUID());
        user.setNickname("userExample");
        user.setEmail("user@example.com");
        user.setPassword("niceSecurityPassword");

        return user;
    }

    private TransactionDTO createFakeTransaction() {
        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setTransactionId(UUID.randomUUID());
        transactionDTO.setUser(this.createFakeUser());
        transactionDTO.setInitialCurrency(Currency.valueOf("USD"));
        transactionDTO.setOriginalValue(100.0);
        transactionDTO.setFinalCurrency(Currency.valueOf("BRL"));
        transactionDTO.setConversionRate(5.0);

        return transactionDTO;
    }
}

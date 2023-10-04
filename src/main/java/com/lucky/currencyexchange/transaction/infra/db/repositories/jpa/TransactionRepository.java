package com.lucky.currencyexchange.transaction.infra.db.repositories.jpa;

import com.lucky.currencyexchange.transaction.infra.db.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, UUID> {

    List<Transaction> findAllByUserId(UUID userId);

}

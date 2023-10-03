package com.lucky.currencyexchange.transaction.infra.db.entities;

import com.lucky.currencyexchange.transaction.enums.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "converter_transaction")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", nullable=false, unique=true)
    private UUID id;

    @Column(name="userId", nullable=false, unique=true)
    private UUID userId;

    @Column(name="initialCurrency", nullable=false)
    private Currency initialCurrency;

    @Column(name="finalCurrency", nullable=false)
    private Currency finalCurrency;

    @Column(name="originalValue", nullable=false)
    private double originalValue;

    @Column(name="conversionRate", nullable=false)
    private double conversionRate;

    @Column(name="created_at", nullable=false)
    private LocalDate createAt;
}

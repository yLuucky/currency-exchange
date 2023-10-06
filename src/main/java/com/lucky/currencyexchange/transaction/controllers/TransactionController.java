package com.lucky.currencyexchange.transaction.controllers;

import com.lucky.currencyexchange.transaction.controllers.views.TransactionView;
import com.lucky.currencyexchange.transaction.dtos.TransactionDTO;
import com.lucky.currencyexchange.transaction.services.ICreateTransactionService;
import com.lucky.currencyexchange.transaction.services.IFindTransactionsByUserIdService;
import com.lucky.currencyexchange.user.services.IFindUserByIdService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private final ICreateTransactionService createTransactionService;
    private final IFindTransactionsByUserIdService findTransactionsByUserIdService;
    private final IFindUserByIdService findUserByIdService;

    @Autowired
    public TransactionController(final ICreateTransactionService createTransactionService,
                                 final IFindTransactionsByUserIdService findTransactionsByUserIdService,
                                 final IFindUserByIdService findUserByIdService) {
        this.findTransactionsByUserIdService = findTransactionsByUserIdService;
        this.createTransactionService = createTransactionService;
        this.findUserByIdService = findUserByIdService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created a transaction"),
            @ApiResponse(responseCode = "400", description = "could not create a transaction")
    })
    @PostMapping
    public ResponseEntity<TransactionView> create(@RequestBody final TransactionDTO transactionDTO, @RequestParam String userId) {
        findUserByIdService.execute(UUID.fromString(userId));
        final TransactionDTO dto = createTransactionService.execute(transactionDTO);
        return ResponseEntity.status(201).body(TransactionView.from(dto));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "get transactions"),
            @ApiResponse(responseCode = "400", description = "could not get transactions")
    })
    @GetMapping("{id}")
    public ResponseEntity<List<TransactionView>> show(final String userId) {
        final List<TransactionDTO> transactionDTOS = findTransactionsByUserIdService.execute(UUID.fromString(userId));
        return ResponseEntity.ok(TransactionView.from(transactionDTOS));
    }

}

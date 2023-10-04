package com.lucky.currencyexchange.transaction.controllers;

import com.lucky.currencyexchange.transaction.controllers.views.TransactionView;
import com.lucky.currencyexchange.transaction.dtos.TransactionDTO;
import com.lucky.currencyexchange.transaction.services.ICreateTransactionService;
import com.lucky.currencyexchange.transaction.services.IFindTransactionsByUserIdService;
import com.lucky.currencyexchange.user.infra.db.repositories.jpa.UserRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("transaction")
public class TransactionController {

    private final UserRepository userRepository;
    private final ICreateTransactionService createTransactionService;
    private final IFindTransactionsByUserIdService findTransactionsByUserIdService;

    @Autowired
    public TransactionController(final ICreateTransactionService createTransactionService,
                                 final IFindTransactionsByUserIdService findTransactionsByUserIdService,
                                 final UserRepository userRepository) {
        this.findTransactionsByUserIdService = findTransactionsByUserIdService;
        this.createTransactionService = createTransactionService;
        this.userRepository = userRepository;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created a transaction"),
            @ApiResponse(responseCode = "400", description = "could not create")
    })
    @PostMapping
    public ResponseEntity<TransactionView> create(final TransactionDTO transactionDTO, final String userId) {
        userRepository.findById(UUID.fromString(userId));
        final TransactionDTO dto = createTransactionService.execute(transactionDTO);
        return ResponseEntity.status(201).body(TransactionView.from(dto));
    }

    @ApiResponse(responseCode = "201", description = "created transaction")
    @ApiResponse(responseCode = "400", description = "could not create")
    @GetMapping("{id}")
    public ResponseEntity<List<TransactionView>> show(final String userId) {
        final List<TransactionDTO> dtos = findTransactionsByUserIdService.execute(UUID.fromString(userId));
        return ResponseEntity.ok(TransactionView.from(dtos));
    }

}

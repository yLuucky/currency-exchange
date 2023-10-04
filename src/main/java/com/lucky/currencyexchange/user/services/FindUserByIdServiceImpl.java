package com.lucky.currencyexchange.user.services;

import com.lucky.currencyexchange.user.infra.db.entities.User;
import com.lucky.currencyexchange.user.infra.db.repositories.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindUserByIdServiceImpl implements IFindUserByIdService {

    private final UserRepository repository;

    @Autowired
    public FindUserByIdServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> execute(final UUID id) {
        return repository.findById(id);
    }
}

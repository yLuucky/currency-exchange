package com.lucky.currencyexchange.user.services;

import com.lucky.currencyexchange.user.infra.db.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface IFindUserByIdService {
    Optional<User> execute(UUID id);
}

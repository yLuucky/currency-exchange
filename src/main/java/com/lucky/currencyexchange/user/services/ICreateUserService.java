package com.lucky.currencyexchange.user.services;

import com.lucky.currencyexchange.user.dtos.UserDTO;
import com.lucky.currencyexchange.user.infra.db.entities.User;

public interface ICreateUserService {
    User execute(UserDTO userDTO);
}

package com.lucky.currencyexchange.user.services;

import com.lucky.currencyexchange.user.dtos.UserDTO;
import com.lucky.currencyexchange.user.infra.db.entities.User;
import com.lucky.currencyexchange.user.infra.db.repositories.jpa.UserRepository;
import com.lucky.currencyexchange.user.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserServiceImpl implements ICreateUserService{

    private final UserRepository repository;


    @Autowired
    public CreateUserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User execute(final UserDTO userDTO) {
        User user = UserMapper.mapper(userDTO);
        return repository.save(user);
    }
}

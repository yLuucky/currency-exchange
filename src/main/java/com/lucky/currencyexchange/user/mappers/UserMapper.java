package com.lucky.currencyexchange.user.mappers;

import com.lucky.currencyexchange.user.dtos.UserDTO;
import com.lucky.currencyexchange.user.infra.db.entities.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User mapper(final UserDTO userDTO) {
        final User user = new User();
        user.setUserId(userDTO.getId());
        user.setNickname(userDTO.getNickname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getUser_pass());

        return user;
    }
}

package com.lucky.currencyexchange.user.mappers;

import com.lucky.currencyexchange.user.controllers.views.UserView;
import com.lucky.currencyexchange.user.infra.db.entities.User;
import lombok.*;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserViewMapper {

    public static UserView mapper(User user) {
        return UserView.create(
                user.getUserId(),
                user.getNickname(),
                user.getEmail(),
                user.getPassword()
        );
    }
}

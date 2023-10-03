package com.lucky.currencyexchange.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class UserDTO {

    private UUID id;
    private String nickname;
    private String email;
    private String user_pass;
}

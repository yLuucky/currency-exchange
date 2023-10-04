package com.lucky.currencyexchange.user.controllers.views;

import com.lucky.currencyexchange.user.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor(staticName = "create")
public class UserView {

    private UUID userId;
    private String nickname;
    private String email;
    private String password;

    public static UserView from(UserDTO userDTO) {
        return UserView.create(
                userDTO.getId(),
                userDTO.getNickname(),
                userDTO.getEmail(),
                userDTO.getUser_pass()
        );
    }

    public static List<UserView> from(List<UserDTO> userDTOS) {
        return userDTOS.stream().map(UserView::from).collect(Collectors.toList());
    }

}

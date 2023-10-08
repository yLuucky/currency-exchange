package com.lucky.currencyexchange.user;

import com.lucky.currencyexchange.user.dtos.UserDTO;
import com.lucky.currencyexchange.user.infra.db.entities.User;
import com.lucky.currencyexchange.user.infra.db.repositories.jpa.UserRepository;
import com.lucky.currencyexchange.user.mappers.UserMapper;
import com.lucky.currencyexchange.user.services.CreateUserServiceImpl;
import com.lucky.currencyexchange.user.services.FindUserByIdServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserTest {

    @InjectMocks
    private CreateUserServiceImpl createUserService;

    @InjectMocks
    private FindUserByIdServiceImpl findUserByIdService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUserTest() {
        UserDTO expectedUser = this.createFakeUser();

        when(userRepository.save(any())).thenReturn(UserMapper.mapper(expectedUser));
        User result = createUserService.execute(expectedUser);

        verify(userRepository, times(1)).save(any());
        assertEquals(expectedUser.getId(), result.getUserId());
        assertEquals(expectedUser.getNickname(), result.getNickname());
        assertEquals(expectedUser.getEmail(), result.getEmail());
        assertEquals(expectedUser.getUser_pass(), result.getPassword());
    }

    @Test
    public void findUserByIdTest() {
        UserDTO expectedUser = this.createFakeUser();

        when(userRepository.findById(any())).thenReturn(Optional.of(UserMapper.mapper(expectedUser)));
        Optional<User> result = findUserByIdService.execute(expectedUser.getId());

        verify(userRepository, times(1)).findById(expectedUser.getId());
        assertTrue(result.isPresent());
        assertEquals(expectedUser.getId(), result.get().getUserId());
        assertEquals(expectedUser.getNickname(), result.get().getNickname());
        assertEquals(expectedUser.getEmail(), result.get().getEmail());
        assertEquals(expectedUser.getUser_pass(), result.get().getPassword());
    }

    @Test
    public void testExecuteUserNotFound() {
        UserDTO expectedUser = this.createFakeUser();

        when(userRepository.findById(any())).thenReturn(Optional.empty());
        Optional<User> result = findUserByIdService.execute(expectedUser.getId());

        verify(userRepository, times(1)).findById(expectedUser.getId());
        assertFalse(result.isPresent());
    }

    private UserDTO createFakeUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(UUID.randomUUID());
        userDTO.setNickname("userExample");
        userDTO.setEmail("user@example.com");
        userDTO.setUser_pass("niceSecurityPassword");

        return userDTO;
    }
}

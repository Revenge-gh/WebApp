package com.javarush.springmvc.service;

import com.javarush.springmvc.dto.UserDto;
import com.javarush.springmvc.entity.User;
import com.javarush.springmvc.exception.ValidationException;
import com.javarush.springmvc.repository.UserRepository;
import com.javarush.springmvc.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    private UserRepository userRepository;
    private UserService userService;
    private User user = User.builder()
            .id(1)
            .name("testName")
            .login("testLogin")
            .build();


    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService =  new UserServiceImpl(userRepository);
        when(userRepository.save(any())).thenReturn(user);
    }

    @Test
    void saveUserReturnUserDto() throws ValidationException {
        UserDto userDto = UserDto.builder().login("testLogin").build();
        UserDto savedUserDto = userService.saveUser(userDto);
        assertThat(savedUserDto.getLogin()).isNotNull().isEqualTo("testLogin");
    }

    @Test
    void saveUserWithNoLoginThrowsValidationException() {
        UserDto userDto = UserDto.builder().build();
        assertThrows(ValidationException.class,
                () -> userService.saveUser(userDto),
                "Login is empty");
    }
}
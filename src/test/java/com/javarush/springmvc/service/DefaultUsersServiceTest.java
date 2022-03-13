package com.javarush.springmvc.service;

import com.javarush.springmvc.converter.UsersConverter;
import com.javarush.springmvc.dto.UsersDto;
import com.javarush.springmvc.entity.Users;
import com.javarush.springmvc.exception.ValidationException;
import com.javarush.springmvc.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultUsersServiceTest {
    private UsersRepository usersRepository;
    private UsersConverter usersConverter;
    private UsersService usersService;
    private Users users = Users.builder()
            .id(1)
            .name("testName")
            .login("testLogin")
            .build();


    @BeforeEach
    void setUp() {
        usersRepository = mock(UsersRepository.class);
        usersConverter = new UsersConverter();
        usersService =  new DefaultUsersService(usersRepository, usersConverter);
        when(usersRepository.save(any())).thenReturn(users);
    }

    @Test
    void saveUserReturnUserDto() throws ValidationException {
        UsersDto usersDto = UsersDto.builder().login("testLogin").build();
        UsersDto savedUsersDto = usersService.saveUser(usersDto);
        assertThat(savedUsersDto.getLogin()).isNotNull().isEqualTo("testLogin");
    }

    @Test
    void saveUserWithNoLoginThrowsValidationException() {
        UsersDto usersDto = UsersDto.builder().build();
        assertThrows(ValidationException.class,
                () -> usersService.saveUser(usersDto),
                "Login is empty");
    }
}
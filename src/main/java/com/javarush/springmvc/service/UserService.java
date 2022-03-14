package com.javarush.springmvc.service;

import com.javarush.springmvc.dto.UserDto;
import com.javarush.springmvc.exception.ValidationException;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto) throws ValidationException;

    void deleteUser(Integer userId);

    UserDto findByLogin(String login);

    List<UserDto> findAll();
}

package com.javarush.springmvc.service;

import com.javarush.springmvc.dto.UsersDto;
import com.javarush.springmvc.exception.ValidationException;

import java.util.List;

public interface UsersService {

    UsersDto saveUser(UsersDto usersDto) throws ValidationException;

    void deleteUser(Integer userId);

    UsersDto findByLogin(UsersDto login);

    List<UsersDto> findAll();
}

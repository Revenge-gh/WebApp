package com.javarush.springmvc.service;

import com.javarush.springmvc.dto.UsersDto;
import com.javarush.springmvc.entity.Users;
import com.javarush.springmvc.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class DefaultUsersService {

    private void validateUserDto(UsersDto usersDto) throws ValidationException {
        if (isNull(usersDto)) {
            throw new ValidationException("Object user is null");
        }

        if (isNull(usersDto.getLogin()) || usersDto.getLogin().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }

}

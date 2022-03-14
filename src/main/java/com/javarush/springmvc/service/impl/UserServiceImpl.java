package com.javarush.springmvc.service.impl;

import com.javarush.springmvc.converter.UserConverter;
import com.javarush.springmvc.dto.UserDto;
import com.javarush.springmvc.entity.User;
import com.javarush.springmvc.exception.ValidationException;
import com.javarush.springmvc.repository.UserRepository;
import com.javarush.springmvc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.javarush.springmvc.converter.UserConverter.*;
import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private void validateUserDto(UserDto userDto) throws ValidationException {
        if (isNull(userDto)) {
            throw new ValidationException("Object user is null");
        }

        if (isNull(userDto.getLogin()) || userDto.getLogin().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }


    @Override
    public UserDto saveUser(UserDto userDto) throws ValidationException {
        validateUserDto(userDto);
        User convertedUser = fromUserDtoToUser(userDto);
        User savedUser = userRepository.save(convertedUser);
        return fromUserToUserDto(savedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login);

        if (user != null) {
            return fromUserToUserDto(user);
        }

        return null;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }
}

package com.javarush.springmvc.service;

import com.javarush.springmvc.dto.UsersDto;
import com.javarush.springmvc.entity.Users;
import com.javarush.springmvc.exception.ValidationException;
import com.javarush.springmvc.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class DefaultUsersService implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersConverter usersConverter;

    private void validateUserDto(UsersDto usersDto) throws ValidationException {
        if (isNull(usersDto)) {
            throw new ValidationException("Object user is null");
        }

        if (isNull(usersDto.getLogin()) || usersDto.getLogin().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }


    @Override
    public UsersDto saveUser(UsersDto usersDto) throws ValidationException {
        validateUserDto(usersDto);
        Users convertedUser = usersConverter.fromUserDtoToUser(usersDto);
        Users savedUser = usersRepository.save(convertedUser);
        return usersConverter.fromUserToUserDto(savedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public UsersDto findByLogin(UsersDto login) {
        Users users = usersRepository.findByLogin(login.getLogin());

        if (users != null) {
            return usersConverter.fromUserToUserDto(users);
        }

        return null;
    }

    @Override
    public List<UsersDto> findAll() {
        return usersRepository.findAll()
                .stream()
                .map(usersConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }
}

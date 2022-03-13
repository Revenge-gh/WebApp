package com.javarush.springmvc.converter;

import com.javarush.springmvc.dto.UsersDto;
import com.javarush.springmvc.entity.Users;
import org.springframework.stereotype.Component;

public class UsersConverter {

    public static Users fromUserDtoToUser(UsersDto usersDto) {
        return Users.builder()
                .id(usersDto.getId())
                .name(usersDto.getName())
                .email(usersDto.getEmail())
                .login(usersDto.getLogin())
                .build();
    }

    public static UsersDto fromUserToUserDto(Users users) {
        return UsersDto.builder()
                .id(users.getId())
                .name(users.getName())
                .login(users.getLogin())
                .email(users.getEmail())
                .build();
    }
}

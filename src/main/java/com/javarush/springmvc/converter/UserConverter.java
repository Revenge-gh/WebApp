package com.javarush.springmvc.converter;

import com.javarush.springmvc.dto.UserDto;
import com.javarush.springmvc.entity.User;

public class UserConverter {

    public static User fromUserDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .login(userDto.getLogin())
                .build();
    }

    public static UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .email(user.getEmail())
                .build();
    }
}

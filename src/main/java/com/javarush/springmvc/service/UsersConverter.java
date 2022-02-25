package com.javarush.springmvc.service;

import com.javarush.springmvc.dto.UsersDto;
import com.javarush.springmvc.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersConverter {

    public Users fromUserDtoToUser(UsersDto usersDto) {
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setName(usersDto.getName());
        users.setLogin(usersDto.getLogin());
        users.setEmail(usersDto.getEmail());
        return users;
    }

    public UsersDto fromUserToUserDto(Users users) {
        return UsersDto.builder()
                .id(users.getId())
                .name(users.getName())
                .login(users.getLogin())
                .email(users.getEmail())
                .build();
    }
}

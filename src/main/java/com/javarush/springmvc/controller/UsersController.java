package com.javarush.springmvc.controller;

import com.javarush.springmvc.dto.UsersDto;
import com.javarush.springmvc.exception.ValidationException;
import com.javarush.springmvc.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
@Log
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/save")
    public UsersDto saveUsers(UsersDto usersDto) throws ValidationException {
       log.info("Handling save users: " + usersDto);
        return usersService.saveUser(usersDto);
    }


}

package com.javarush.springmvc.controller;

import com.javarush.springmvc.dto.UserDto;
import com.javarush.springmvc.exception.ValidationException;
import com.javarush.springmvc.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Log
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public UserDto saveUsers(@RequestBody UserDto userDto) throws ValidationException {
        log.info("Handling save user: " + userDto);
        return userService.saveUser(userDto);
    }

    @GetMapping("/findAll")
    public List<UserDto> findAllUsers() {
        log.info("Handling find all users request");
        return userService.findAll();
    }

    @GetMapping("/findByLogin")
    public UserDto findByLogin(@RequestParam String login) {
        log.info("Handling find by login request: " + login);
        return userService.findByLogin(login);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Integer id) {
        log.info("Handling delete user request: " + id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
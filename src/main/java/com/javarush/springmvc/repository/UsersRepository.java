package com.javarush.springmvc.repository;

import com.javarush.springmvc.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByLogin(String login);
}

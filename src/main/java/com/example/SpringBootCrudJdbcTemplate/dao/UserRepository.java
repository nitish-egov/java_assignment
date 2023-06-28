package com.example.SpringBootCrudJdbcTemplate.dao;

import com.example.SpringBootCrudJdbcTemplate.entity.egovUser;

import java.util.List;

public interface UserRepository {
    egovUser saveUser(egovUser egovUser);
    egovUser updateUser(egovUser egovUser);
    egovUser getbyId(long id);
    String deletebyId(long id);


    List<egovUser> allUsers();
}

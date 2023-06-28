package com.example.SpringBootCrudJdbcTemplate.controller;

import com.example.SpringBootCrudJdbcTemplate.dao.UserRepository;
import com.example.SpringBootCrudJdbcTemplate.entity.egovUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @PostMapping("create")
    public egovUser addUser(@RequestBody egovUser egovUser)
    {
        return userRepository.saveUser(egovUser);

    }
    @PutMapping("update")
    public egovUser updateUser(@RequestBody egovUser egovUser)
    {
        return userRepository.updateUser(egovUser);

    }
    @GetMapping("/user/{id}")
    public egovUser getUser(@PathVariable("id")  long id)
    {
            return userRepository.getbyId(id);
    }
    @GetMapping("/user_all")
    public List<egovUser> getUsers(){
        return userRepository.allUsers();
    }

    @DeleteMapping("/user/{id}")

        public String deleteUser(@PathVariable ("id") long id){

        return userRepository.deletebyId(id);
    }

}

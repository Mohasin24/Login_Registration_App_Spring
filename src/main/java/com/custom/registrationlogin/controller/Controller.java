package com.custom.registrationlogin.controller;
import com.custom.registrationlogin.exception.ValidationException;
import com.custom.registrationlogin.service.UserDao;
import com.custom.registrationlogin.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class Controller
{
    private UserDao userDao;

    @Autowired
    public Controller(UserDao userDao)
    {
        this.userDao = userDao;
    }
    @GetMapping("/")
    public List<User> getAllUsers()
    {
        List<User> userList = userDao.getAllUsers();
        return userList;
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId)
    {
        User user = userDao.getUserById(userId);

        return user;
    }

//    @PostMapping("/")
//    public User addUsers(@RequestBody @Valid User user, BindingResult bindingResult) throws Exception
//    {
//        if(!bindingResult.hasErrors())
//        {
//            User dbUser = userDao.addUser(user);
//            return dbUser;
//        }
//
//        throw new ValidationException(bindingResult.getAllErrors().toString());
//    }

    @PostMapping("/")
    public ResponseEntity<Object> addUsers(@RequestBody @Valid User user, BindingResult bindingResult) throws Exception
    {
        if(!bindingResult.hasErrors())
        {
            User dbUser = userDao.addUser(user);
            return ResponseEntity.ok(dbUser);
        }

        throw new ValidationException(bindingResult.getAllErrors().toString());
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) throws Exception
    {
        try{
            userDao.deleteUserById(userId);
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    @DeleteMapping("/")
    public void deleteAllUser(){
        userDao.deleteAllUser();
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId)
    {
        User user = userDao.getUserById(userId);

        user.setEmail("example@gmail.com");
        user.setFirstName("MohasinLala");
        user.setLastName("Patel Bhai");
        user.setPassword("12ka4");
        user.setUsername("Mohasin Seth");

        return userDao.updateUserDtls(user);
    }
}

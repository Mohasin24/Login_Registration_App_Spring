package com.custom.registrationlogin.controller;
import com.custom.registrationlogin.exception.ValidationException;
import com.custom.registrationlogin.service.UserDao;
import com.custom.registrationlogin.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> userList = userDao.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId)
    {
        User user = userDao.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/")
    public ResponseEntity<User> addUsers(@RequestBody @Valid User user, BindingResult bindingResult) throws Exception
    {
        if(!bindingResult.hasErrors())
        {
            User dbUser = userDao.addUser(user);
            return ResponseEntity.ok(dbUser);
        }

        throw new ValidationException(bindingResult.getAllErrors().toString());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) throws Exception
    {
        try{
            userDao.deleteUserById(userId);
            return ResponseEntity.ok("User with id : " + userId + " successfully deleted.");
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteAllUser(){
        userDao.deleteAllUser();
        return ResponseEntity.ok("All user's successfully deleted.");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody @Valid User user)
    {
        User currUser = userDao.getUserById(userId);

        if (currUser == null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        currUser.setUsername(user.getUsername());
        currUser.setFirstName(user.getFirstName());
        currUser.setLastName(user.getLastName());
        currUser.setEmail(user.getEmail());
        currUser.setPassword(user.getPassword());

        User updatedUser = userDao.updateUserDtls(currUser);
        ResponseEntity<User> responseEntity = new ResponseEntity<>(updatedUser, HttpStatus.OK);
        return responseEntity;
    }
}

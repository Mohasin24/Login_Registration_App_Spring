package com.custom.registrationlogin.service;

import com.custom.registrationlogin.dao.UserJpaRepository;
import com.custom.registrationlogin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoImpl implements UserDao
{
    List<User> userList = new ArrayList<>();
    private UserJpaRepository userJpaRepository;

    @Autowired
    public UserDaoImpl(UserJpaRepository userJpaRepository)
    {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public List<User> getAllUsers()
    {
        userList = userJpaRepository.findAll();
        return userList;
    }

    @Override
    public User getUserById(Long id)
    {
        User user = userJpaRepository.findById(id).get();
        return user;
    }

    @Override
    public User addUser(User user)
    {
        return userJpaRepository.save(user);
    }

    @Override
    public User updateUserDtls(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public void deleteAllUser() {
        userJpaRepository.deleteAll();
    }
}

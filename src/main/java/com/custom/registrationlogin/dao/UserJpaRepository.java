package com.custom.registrationlogin.dao;

import com.custom.registrationlogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<User,Long> {

//    load user by username
    User findByUsername(String username);

//    @Query("SELECT u FROM User u WHERE u.username = :username")
//    User findByUsername(@Param("username") String username);

//    check if user is already registered or not
//    boolean existsByEmail(String email);
}

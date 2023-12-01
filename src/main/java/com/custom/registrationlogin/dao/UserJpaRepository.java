package com.custom.registrationlogin.dao;

import com.custom.registrationlogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User,Long> {
}

package com.example.demo.repository;

import com.example.demo.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhubuqing on 2018/2/24.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String name);
}

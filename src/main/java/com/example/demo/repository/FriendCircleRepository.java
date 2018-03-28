package com.example.demo.repository;

import com.example.demo.po.FriendCircle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendCircleRepository extends JpaRepository<FriendCircle, Long> {

    FriendCircle findById(int id);

}

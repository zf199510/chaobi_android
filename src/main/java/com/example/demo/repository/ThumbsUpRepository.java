package com.example.demo.repository;

import com.example.demo.po.ThumbsUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhubuqing on 2018/2/24.
 */
public interface ThumbsUpRepository extends JpaRepository<ThumbsUp, Long> {
    List<ThumbsUp> getByCircleId(long circleId);
}

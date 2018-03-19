package com.example.demo.repository;

import com.example.demo.po.PunchPoint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhubuqing on 2018/2/24.
 */
public interface PunchPointRepository extends JpaRepository<PunchPoint, Long> {
}

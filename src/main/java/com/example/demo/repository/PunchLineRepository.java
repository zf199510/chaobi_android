package com.example.demo.repository;

import com.example.demo.po.PunchLine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhubuqing on 2018/2/24.
 */
public interface PunchLineRepository extends JpaRepository<PunchLine, Long> {
}

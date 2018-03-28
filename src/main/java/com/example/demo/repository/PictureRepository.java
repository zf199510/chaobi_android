package com.example.demo.repository;

import com.example.demo.po.Circle;
import com.example.demo.po.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhubuqing on 2018/2/24.
 */
public interface PictureRepository extends JpaRepository<Picture, Long> {

}

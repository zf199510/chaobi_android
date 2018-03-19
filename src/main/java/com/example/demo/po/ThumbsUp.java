package com.example.demo.po;

import com.example.demo.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by zhubuqing on 2018/2/21.
 */
@Data
@Entity
public class ThumbsUp extends BaseEntity {
    private long circleId;

    private long userId;
}

package com.example.demo.po;

import com.example.demo.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by zhubuqing on 2018/2/12.
 */
@Data
@Entity
public class PunchPoint extends BaseEntity {
    private long punchLineId; // 路线ID

    private double lat;

    private double lng;

    private long userId;

    private String pic;

    private String content;

    private String address;

    private String type;
}

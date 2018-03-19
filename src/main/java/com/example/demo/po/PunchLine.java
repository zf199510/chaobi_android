package com.example.demo.po;

import com.example.demo.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by zhubuqing on 2018/2/12.
 */
@Data
@Entity
public class PunchLine extends BaseEntity {
    private long userId; // 用户ID

    private String pic; // 生成图片

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime; // 开始时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime; // 结束时间
}

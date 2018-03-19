package com.example.demo.util;

import com.example.demo.po.Circle;
import com.example.demo.po.PunchPoint;
import com.example.demo.po.User;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by zhubuqing on 2017/6/12.
 */
@Data
public class Result {
    private int code;
    private User user;
    private PunchPoint punchPoint;
    private List<PunchPoint> punchPoints;
    private String createTime;
    private Page<Circle> circlePage;
}

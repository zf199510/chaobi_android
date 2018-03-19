package com.example.demo.controller;

import com.example.demo.po.PunchPoint;
import com.example.demo.repository.PunchPointRepository;
import com.example.demo.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhubuqing on 2018/3/1.
 */
@RestController
@RequestMapping("/punchPoint")
public class PunchPointController {
    private static Logger logger = LoggerFactory.getLogger(PunchPointController.class);

    @Autowired
    private PunchPointRepository punchPointRepository;

    private Result result;

    /**
     * 添加定点
     *
     * @param userId
     * @param pic
     * @param content
     * @param lat
     * @param lng
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "addPointByUserId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result addPointByUserId(@RequestParam String userId, @RequestParam String pic, @RequestParam String content, @RequestParam String lat, @RequestParam String lng) {
        result = new Result();
        PunchPoint point = new PunchPoint();
        point.setType("near");
        point.setContent(content);
        point.setLat(Double.valueOf(lat));
        point.setUserId(Long.valueOf(userId));
        point.setPic(pic);
        point.setLng(Double.valueOf(lng));
        punchPointRepository.save(point);
        result.setCode(1);
        return result;
    }
}

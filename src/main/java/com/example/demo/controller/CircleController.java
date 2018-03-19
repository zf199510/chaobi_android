package com.example.demo.controller;

import com.example.demo.po.Circle;
import com.example.demo.po.ThumbsUp;
import com.example.demo.repository.CircleRepository;
import com.example.demo.repository.ThumbsUpRepository;
import com.example.demo.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by zhubuqing on 2018/3/4.
 */
@RestController
@RequestMapping("/circle")
public class CircleController {
    private static Logger logger = LoggerFactory.getLogger(CircleController.class);

    @Autowired
    private CircleRepository circleRepository;

    @Autowired
    private ThumbsUpRepository thumbsUpRepository;

    private Result result;

    /**
     * 添加朋友圈
     *
     * @param circle
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "addCircle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result addCircle(@RequestParam Circle circle) {
        result = new Result();
        circle.setTime(new Date(System.currentTimeMillis()));
        circleRepository.save(circle);
        result.setCode(1);
        return result;
    }

    /**
     * 获取所有朋友圈
     *
     * @param pageable
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "searchCircle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result searchCircle(@RequestParam Pageable pageable) {
        result = new Result();
        Page<Circle> circlePage = circleRepository.findAll(pageable);
        result.setCirclePage(circlePage);
        return result;
    }

    /**
     * 删除朋友圈
     *
     * @param circleId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "deleteCircle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result deleteCircle(@RequestParam long circleId) {
        result = new Result();
        List<ThumbsUp> thumbsUpList = thumbsUpRepository.getByCircleId(circleId);
        for (ThumbsUp thumbsUp : thumbsUpList) {
            thumbsUpRepository.delete(thumbsUp.getId());
        }
        circleRepository.delete(circleId);
        result.setCode(1);
        return result;
    }
}

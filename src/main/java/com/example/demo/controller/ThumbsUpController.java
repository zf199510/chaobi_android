package com.example.demo.controller;

import com.example.demo.po.ThumbsUp;
import com.example.demo.repository.ThumbsUpRepository;
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

/**
 * Created by zhubuqing on 2018/3/1.
 */
@RestController
@RequestMapping("/thumbsUp")
public class ThumbsUpController {
    private static Logger logger = LoggerFactory.getLogger(ThumbsUpController.class);

    @Autowired
    private ThumbsUpRepository thumbsUpRepository;

    private Result result;

    /**
     * 点赞
     *
     * @param thumbsUp
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "addThumbs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result addThumbs(@RequestParam ThumbsUp thumbsUp) {
        result = new Result();
        thumbsUpRepository.save(thumbsUp);
        return result;
    }

    /**
     * 取消点赞
     *
     * @param thumbsUpId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "deleteThumbs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result deleteThumbs(@RequestParam long thumbsUpId) {
        result = new Result();
        thumbsUpRepository.delete(thumbsUpId);
        return result;
    }
}

package com.example.demo.controller;

import com.example.demo.po.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.Result;
import com.google.gson.Gson;
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
 * Created by zhubuqing on 2018/2/24.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    private Result userResult;

    /**
     * Android 登录
     *
     * @param name
     * @param pass
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String login(@RequestParam String name, @RequestParam String pass) {
        System.out.print(name+"++++++"+pass);
        userResult = new Result();
        Gson gson = new Gson();
        User user = userRepository.getByUsername(name);
        if (user == null) {
            userResult.setCode(-1);
            return gson.toJson(userResult);
        }
        if (!user.getPassword().equals(pass)) {
            userResult.setCode(-1);
            return gson.toJson(userResult);
        }
        userResult.setCode(1);
        userResult.setUser(user);

        return gson.toJson(userResult);
    }

    /**
     * Android 注册
     *
     * @param name
     * @param pass
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result register(@RequestParam String name, @RequestParam String pass) {
        User user = userRepository.getByUsername(name);
        userResult = new Result();
        if (user != null) {
            userResult.setCode(-1);
            return userResult;
        }
        User u = new User();
        u.setUsername(name);
        u.setPassword(pass);
        u.setHeadPic("head.jpg");
        userRepository.save(u);
        userResult.setCode(1);
        return userResult;
    }
}

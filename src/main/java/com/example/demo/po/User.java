package com.example.demo.po;

import com.example.demo.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by zhubuqing on 2018/2/21.
 */
@Data
@Entity
public class User extends BaseEntity {

    private String username; // 用户姓名

    private String password; // 密码

    private String headPic; // 头像

    private String email; // email

    private String sex; // 性别

    private String phone; // 手机号
}

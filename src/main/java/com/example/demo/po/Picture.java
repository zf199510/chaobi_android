package com.example.demo.po;

import com.example.demo.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by ZF on 2017-07-27.
 */
@Data
@Entity
public class Picture extends BaseEntity{

    private String url;

    private int width;

    private int height;


}

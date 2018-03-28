package com.example.demo.po;

import com.example.demo.base.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by zhubuqing on 2018/2/12.
 */
@Data
@Entity
public class Circle extends BaseEntity {
    private long userId;

    private String title; // 标题

    private String content; // 内容

    private long punchLineId;


    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPunchLineId() {
        return punchLineId;
    }

    public void setPunchLineId(long punchLineId) {
        this.punchLineId = punchLineId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

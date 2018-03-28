package com.example.demo.util;

import com.example.demo.po.Circle;
import com.example.demo.po.FriendCircle;
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
    private List<FriendCircle> friendCircles;
    private FriendCircle friendCircle;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PunchPoint getPunchPoint() {
        return punchPoint;
    }

    public void setPunchPoint(PunchPoint punchPoint) {
        this.punchPoint = punchPoint;
    }

    public List<PunchPoint> getPunchPoints() {
        return punchPoints;
    }

    public void setPunchPoints(List<PunchPoint> punchPoints) {
        this.punchPoints = punchPoints;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Page<Circle> getCirclePage() {
        return circlePage;
    }

    public void setCirclePage(Page<Circle> circlePage) {
        this.circlePage = circlePage;
    }

    public List<FriendCircle> getFriendCircles() {
        return friendCircles;
    }

    public void setFriendCircles(List<FriendCircle> friendCircles) {
        this.friendCircles = friendCircles;
    }

    public FriendCircle getFriendCircle() {
        return friendCircle;
    }

    public void setFriendCircle(FriendCircle friendCircle) {
        this.friendCircle = friendCircle;
    }
}

package com.dsp.domain;

import com.dsp.vo.QueryDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 留言板
 */

@Table(name = "guestbook")
@Data
public class Guestbook extends QueryDate {
    public Guestbook(int gId, String gTitle, String gContent, Integer userId, Date gReleaseTime,User user) {
        this.gId = gId;
        this.gTitle = gTitle;
        this.gContent = gContent;
        this.userId = userId;
        this.gReleaseTime = gReleaseTime;
        this.user = user;
    }

    public Guestbook() {
    }

    @Id
    private int gId;

    private String gTitle;//标题
    private String gContent;//内容
    private Integer userId;//用户id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date gReleaseTime;//发布时间

    private User user;

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public String getgTitle() {
        return gTitle;
    }

    public void setgTitle(String gTitle) {
        this.gTitle = gTitle;
    }

    public String getgContent() {
        return gContent;
    }

    public void setgContent(String gContent) {
        this.gContent = gContent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getgReleaseTime() {
        return gReleaseTime;
    }

    public void setgReleaseTime(Date gReleaseTime) {
        this.gReleaseTime = gReleaseTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

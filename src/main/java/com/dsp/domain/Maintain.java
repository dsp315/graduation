package com.dsp.domain;

import com.dsp.vo.QueryDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 维修
 */
@Data
@Table(name = "maintain")
public class Maintain extends QueryDate {
    @Id
    private Integer mId;
    private String mContent;//内容
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date mReleaseTime;//发布时间
    private String mResult;//受理结果
    private Integer mState;//受理状态
    private Integer userId;//用户id

    private User user;
}

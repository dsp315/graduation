package com.dsp.domain;

import com.dsp.vo.QueryDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 公告
 */
@Data
@Table(name = "notice")
public class Notice extends QueryDate {
    @Id
    private Integer nId;
    private String nTitle;
    private String nContent;
    private Integer adminPuId;
    private Integer adminMoId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nReleaseTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nModifeTime;

    private Integer nState;

    private Admin AdminPu;//发布者
    private Admin AdminMo;//修改者
}

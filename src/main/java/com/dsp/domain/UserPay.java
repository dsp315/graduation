package com.dsp.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user_pay")
@Data
public class UserPay {
    @Id
    private Integer id;
    private Integer userId;
    private String payId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    private String value;
    private Integer status;

    private User user;
    private Pay pay;
}

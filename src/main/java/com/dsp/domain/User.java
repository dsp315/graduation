package com.dsp.domain;

import com.dsp.vo.QueryNumber;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户
 */
@Data
@Table(name = "user")
public class User {
    @Id
    private Integer uId;
    private String uName;
    private String uPassword;
    private Integer uSex;
    private Long uPhone;
    private String username;
    private Float uPay;
    private String uImg;

    private Integer roomId;
    private Integer carId;
    private Integer pay_id;

    private Room room;
    private Car car;

}

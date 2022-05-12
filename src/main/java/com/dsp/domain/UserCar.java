package com.dsp.domain;

import lombok.Data;
import org.springframework.boot.convert.PeriodFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "user_car")
public class UserCar {
    @Id
    private Integer id;
    private Integer userId;
    private Integer carId;
    @DateTimeFormat(pattern = "yyyy-MM=dd")
    private Date inTime;
    @DateTimeFormat(pattern = "yyyy-MM=dd")
    private Date outTime;

    private User user;
    private Car car;
}

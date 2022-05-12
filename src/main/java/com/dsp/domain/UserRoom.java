package com.dsp.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "user_room")
public class UserRoom {
    @Id
    private Integer id;
    private Integer userId;
    private Integer roomId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date outTime;

    private Room room;
    private User user;
}

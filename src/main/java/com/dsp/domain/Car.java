package com.dsp.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "car")
public class Car {
    @Id
    private Integer cId;
    private String cName;
    private Integer cType;//车位类型
    private Integer cState;//车位状态 有无主人

}

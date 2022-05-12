package com.dsp.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 缴费类型
 */
@Data
@Table(name = "pay")
public class Pay {
    @Id
    private Integer pId;
    private String pName;
}

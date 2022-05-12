package com.dsp.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 建筑
 */
@Data
@Table(name = "building")
public class Building {

    @Id
    private Integer bId;

    private String  bName;
    private Integer bType;
}

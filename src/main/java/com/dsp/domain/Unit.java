package com.dsp.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 单元
 */

@Data
@Table(name = "unit")
public class Unit {
    @Id
    private Integer tId;
    private String tName;
    private Integer BuildingId;
    private Building Build;

    public Unit() {
    }

    public Unit(Integer tId, String tName, Integer BId, Building Build) {
        this.tId = tId;
        this.tName = tName;
        BId = BId;
        this.Build = Build;
    }
}

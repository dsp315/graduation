package com.dsp.domain;

import com.dsp.vo.QueryNumber;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 房间
 */
@Data
@Table(name="room")
public class Room extends QueryNumber {

    @Id
    private Integer rId;
    private String rName;
    private Float rArea;
    private Integer rState;
    private Integer UnitId;

    private Unit unit;

    public Room() {
    }

    public Room(Integer rId, String rName, Float rArea, Integer rState, Integer unitId, Unit unit) {
        this.rId = rId;
        this.rName = rName;
        this.rArea = rArea;
        this.rState = rState;
        UnitId = unitId;
        this.unit = unit;
    }
}

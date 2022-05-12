package com.dsp.service;

import com.dsp.domain.Building;
import com.dsp.domain.Unit;

import java.util.List;

public interface UnitSerivce {

    List<Unit> getUnitList();

    List<Unit> getUnitAndLike(Unit param);

    int addUnit(Unit unit);

    int delUnitById(String ids);

    Unit getUnitById(Integer id);

    int updateUnit(Unit unit);

}

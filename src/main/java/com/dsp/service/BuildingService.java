package com.dsp.service;

import com.dsp.domain.Building;

import java.util.List;

public interface BuildingService {
    List<Building> getBuildList();

    List<Building> getBuildAndLike(Building param);

    int addBuild(Building building);

    int delBuildById(String strIds);

    Building getBuildById(Integer id);

    int updateBuild(Building building);
}

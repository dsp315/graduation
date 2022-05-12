package com.dsp.mapper;

import com.dsp.common.mapper.BaseTkMapper;
import org.apache.ibatis.annotations.Mapper;
import com.dsp.domain.Building;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BuildingMapper extends BaseTkMapper<Building> {
}

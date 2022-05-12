package com.dsp.mapper;

import com.dsp.common.mapper.BaseTkMapper;
import com.dsp.domain.Maintain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MaintainMapper extends BaseTkMapper<Maintain> {

    List<Maintain> selectMaintainList();

    List<Maintain> selectMaintainAndLike(Maintain param);

    Maintain selectById(Integer id);
}

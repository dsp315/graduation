package com.dsp.mapper;

import com.dsp.common.mapper.BaseTkMapper;
import com.dsp.domain.Unit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UnitMapper extends BaseTkMapper<Unit> {

    List<Unit> selectListUnit();
    //根据id查找
    Unit selectUnitById(@Param("id") Integer id);
    //根据名字模糊查询
    List<Unit> selectUnitByNameAndLike(Unit unit);
    //添加
    int insertUnit(@Param("unitName") String unitName,@Param("bId") Integer bId);

}

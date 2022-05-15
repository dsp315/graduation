package com.dsp.mapper;

import com.dsp.common.mapper.BaseTkMapper;
import com.dsp.domain.Car;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

@Component
@Mapper
public interface CarMapper extends BaseTkMapper<Car> {
    int updateCarStateSetZero(Integer cId);

    int updateCarStateSetOne(Integer carId);
}

package com.dsp.mapper;

import com.dsp.common.mapper.BaseTkMapper;
import com.dsp.domain.Pay;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PayMapper extends BaseTkMapper<Pay> {
}

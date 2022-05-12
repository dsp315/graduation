package com.dsp.mapper;

import com.dsp.common.mapper.BaseTkMapper;
import com.dsp.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UsersMapper extends BaseTkMapper<User> {
}

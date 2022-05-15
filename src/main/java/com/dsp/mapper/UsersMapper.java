package com.dsp.mapper;

import com.dsp.common.mapper.BaseTkMapper;
import com.dsp.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UsersMapper extends BaseTkMapper<User> {

    /*停用*/
    int updateCarIdSetZeroById(Integer id);
    int updateRoomIdSetZeroById(Integer id);

    /*分配*/
    int updateCarIdSetOneById(Integer uId, Integer cId);
    int updateRoomIdSetOneById(Integer uId, Integer rId);
}

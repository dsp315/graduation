package com.dsp.mapper;

import com.dsp.common.mapper.BaseTkMapper;
import com.dsp.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AdminMapper extends BaseTkMapper<Admin> {

    int updateAdminInfo(Admin admin);

    int editImg(@Param("aId") Integer aId, @Param("filePath") String filePath);
}

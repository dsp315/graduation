package com.dsp.mapper;

import com.dsp.common.mapper.BaseTkMapper;
import com.dsp.domain.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NoticeMapper extends BaseTkMapper<Notice> {

    List<Notice> selectNoticeList();

    List<Notice> selectNoticeAndLike(Notice notice);

    Notice selectNoticeById(Integer id);

    //查询已发布的公告
    List<Notice> selectNoticeStateSetOne();

    List<Notice> selectNoticeStateSetOnePage(long page, long pages);
}

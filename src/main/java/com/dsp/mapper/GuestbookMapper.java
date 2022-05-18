package com.dsp.mapper;

import com.dsp.common.mapper.BaseTkMapper;
import com.dsp.domain.Guestbook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GuestbookMapper extends BaseTkMapper<Guestbook> {

    List<Guestbook> selectGuestbookList();

    List<Guestbook> selectGuestbookListAndLike(Guestbook param);

    Guestbook selectById(Integer id);

    int deleteById(Integer id);

    List<Guestbook> selectGuestbookListPage(long page, long pages);

    int insertGuestbook(Guestbook guestbook);
}

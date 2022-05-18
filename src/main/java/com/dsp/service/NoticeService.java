package com.dsp.service;

import com.dsp.domain.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getNoticeAndLike(Notice param);

    List<Notice> getNoticeList();

    int delNoticeById(String ids);

    int addNotice(Notice notice);

    Notice getNoticeById(Integer id);

    int updateNotice(Notice notice);

    List<Notice> getNoticeStateSetOne();

    List<Notice> getNoticeStateSetOne(long current,int pageTotal);
}

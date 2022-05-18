package com.dsp.service;

import com.dsp.domain.Guestbook;

import java.util.List;

public interface GuestbookService {
    List<Guestbook> getGuestbookAndLike(Guestbook param);

    List<Guestbook> getGuestbookList();
    List<Guestbook> getGuestbookList(long current,long pages);

    Guestbook getGuestbookById(Integer id);

    int delGuestbookById(String strIds);

    int addGuestbook(Guestbook guestbook);
}

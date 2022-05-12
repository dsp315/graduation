package com.dsp.service;

import com.dsp.domain.Guestbook;

import java.util.List;

public interface GuestbookService {
    List<Guestbook> getGuestbookAndLike(Guestbook param);

    List<Guestbook> getGuestbookList();

    Guestbook getGuestbookById(Integer id);

    int delGuestbookById(String strIds);
}

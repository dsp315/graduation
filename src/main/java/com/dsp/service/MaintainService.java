package com.dsp.service;

import com.dsp.domain.Maintain;

import java.util.List;

public interface MaintainService {
    List<Maintain> getMaintainAndLike(Maintain param);

    List<Maintain> getNoticeList();

    int delMaintainById(String ids);

    Maintain getMaintainById(Integer id);

    int addMaintain(Maintain maintain);

    List<Maintain> getMaintainListByUserId(Integer id);
}

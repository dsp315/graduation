package com.dsp.service;

import com.dsp.domain.Pay;

import java.util.List;

public interface PayService {
    List<Pay> getPayList();

    List<Pay> getPayAndLike(Pay param);

    int addPay(Pay pay);

    int delPayById(String ids);

    Pay getPayById(Integer id);

    int updatePay(Pay pay);
}

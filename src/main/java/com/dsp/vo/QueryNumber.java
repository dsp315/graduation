package com.dsp.vo;

import lombok.Data;


public class QueryNumber {
    public Long startNum;
    public Long endNum;

    public QueryNumber(Long startNum, Long endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
    }

    public QueryNumber() {
    }

    public Long getStartNum() {
        return startNum;
    }

    public void setStartNum(Long startNum) {
        this.startNum = startNum;
    }

    public Long getEndNum() {
        return endNum;
    }

    public void setEndNum(Long endNum) {
        this.endNum = endNum;
    }
}

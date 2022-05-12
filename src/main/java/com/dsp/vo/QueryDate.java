package com.dsp.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class QueryDate {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public QueryDate(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public QueryDate() {
    }
}

package com.dsp.vo;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer code;   //状态码
    private String msg; //提示信息
    private T data;         //接受得数据
    private Long count;     //查询的数据的总条数(layui需要)

    public ResultVO() {

    }

    public ResultVO(Integer code, String msg, T data, Long count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }
}

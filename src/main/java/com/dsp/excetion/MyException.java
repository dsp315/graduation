package com.dsp.excetion;

public class MyException extends RuntimeException{
    private MyEnum myEnum;

    public MyException(MyEnum myEnum) {
        super(myEnum.getMessage());
        this.myEnum = myEnum;
    }
}

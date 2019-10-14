package com.jf.shop.login.shopTeminal.utils;

import lombok.Data;

@Data
public class ResultModel<T>{

    private String message;
    private int code;
    private T data;
}

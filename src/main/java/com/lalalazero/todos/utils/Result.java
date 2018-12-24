package com.lalalazero.todos.utils;

import lombok.Data;
import lombok.ToString;

/**
 * @Date 2018/12/23 下午3:50
 */
@Data
@ToString
public class Result {

    int status;
    String msg;
    Object data;

    public Result(int status, String msg, Object data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}

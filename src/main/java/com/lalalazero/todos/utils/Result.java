package com.lalalazero.todos.utils;

import com.lalalazero.todos.consts.ResultEnum;
import lombok.Data;
import lombok.ToString;

/**
 * @Date 2018/12/23 下午3:50
 */
@Data
@ToString
public class Result<T> {

    int status;
    String msg;
    T data;

    public Result(int status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static Result Error(ResultEnum resultEnum){
        return new Result(resultEnum.getStatus(), resultEnum.getMsg(),null);
    }

    public static Result Error(String msg){
        return new Result(-1, msg, null);
    }

    public static Result Success(Object data){
        return new Result(ResultEnum.SUCCESS.getStatus(),ResultEnum.SUCCESS.getMsg(), data);
    }

    public static Result Success(){
        return new Result(ResultEnum.SUCCESS.getStatus(),ResultEnum.SUCCESS.getMsg(), null);
    }

}

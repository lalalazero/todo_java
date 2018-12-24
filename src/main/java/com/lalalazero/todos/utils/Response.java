package com.lalalazero.todos.utils;

/**
 * @Date 2018/12/23 下午3:48
 */
public class Response {

    public static Result Error(String msg){
        return new Result(-1, msg, null);
    }

    public static Result Error(int status, String msg) {
        return new Result(status, msg, null);
    }

    public static Result Success(String msg) {
        return new Result(0, msg, null);
    }

    public static Result Success() {
        return new Result(0, "", null);
    }

    public static Result Success(Object data){
        return new Result(0,"", data);
    }
}


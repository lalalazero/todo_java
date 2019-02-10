package com.lalalazero.todos.consts;

/**
 * @Date 2018/12/24 上午9:48
 */
public enum ResultEnum {

    SUCCESS(0,"操作成功"),
    FAIL(-1,"操作失败"),
    USERNAME_NOT_UNIQUE(-2, "用户名已存在"),
    USER_NON_EXIST(-3,"用户不存在"),
    WRONG_PASS(-4, "用户名或者密码不正确"),
    LIST_NON_EXIST(-5,"清单不存在"),
    NON_TODO_EXIST(-6,"待办项不存在" ),
    WRONG_PARAM(-7, "参数不正确");


    Integer status;
    String msg;

    ResultEnum(Integer status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public String getMsg(){
        return this.msg;
    }

    public Integer getStatus(){
        return this.status;
    }
}

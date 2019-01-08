package com.lalalazero.todos.exception;

import com.lalalazero.todos.consts.ResultEnum;
import com.lalalazero.todos.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Date 2018/12/24 上午9:44
 */
@ControllerAdvice
public class GlobalHandler {

    @ResponseBody
    @ExceptionHandler(value = TodoException.class)
    public Result handleTodo(TodoException e){
        return Result.Error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handleGlobal(Exception e){
        System.out.println(e);
        return Result.Error(ResultEnum.FAIL);
    }
}

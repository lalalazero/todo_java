package com.lalalazero.todos.service;

import com.lalalazero.todos.utils.Result;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Date 2018/12/27 下午1:26
 */
public interface TodoService {

    Result add(String value,Integer listId);

    Result delete(Integer todoId);

    Result update(Integer todoId, String value, String note);

    Result check(Integer todoId,Integer status);

    Result queryDetail(Integer todoId);
}

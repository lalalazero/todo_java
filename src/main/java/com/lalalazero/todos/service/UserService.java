package com.lalalazero.todos.service;

import com.lalalazero.todos.exception.TodoException;
import com.lalalazero.todos.utils.Result;

/**
 * @Date 2018/12/24 上午9:26
 */
public interface UserService {

    Result register(String username, String password) throws TodoException;
}

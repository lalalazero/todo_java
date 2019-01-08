package com.lalalazero.todos.service;

import com.lalalazero.todos.utils.Result;

/**
 * @Date 2018/12/24 下午3:55
 */
public interface ListService {

    Result queryUserList(Integer userId);

    Result createList(String listname, Integer userId, Integer listType);

    Result deleteList(Integer listId);

    Result update(Integer listId, String newerName);

    boolean isListExist(Integer listId);

    Result queryListTodos(Integer listId, Integer type);


}

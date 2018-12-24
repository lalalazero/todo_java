package com.lalalazero.todos.service.impl;

import com.lalalazero.todos.consts.TodoExceptions;
import com.lalalazero.todos.exception.TodoException;
import com.lalalazero.todos.jpa.UserRepository;
import com.lalalazero.todos.service.UserService;
import com.lalalazero.todos.utils.Response;
import com.lalalazero.todos.utils.Result;
import com.sun.tools.javac.comp.Todo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Date 2018/12/24 上午9:28
 */
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Result register(String username, String password) throws TodoException{
        isNameUnique(username);
        return Response.Success();
    }

    private void isNameUnique(String username) throws TodoException{
        Boolean isUnique = userRepository.findByUsernameEquals(username).size() > 0 ? true : false;
        if(!isUnique){
            throw new TodoException(TodoExceptions.USERNAME_NOT_UNIQUE);
        }
    }


}

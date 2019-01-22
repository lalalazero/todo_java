package com.lalalazero.todos.service;

import com.lalalazero.todos.utils.Result;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;

/**
 * @Date 2018/12/24 上午9:26
 */
public interface UserService {

    Result register(String username, String password);

    Result login(String username, String password);

    Boolean isExist(String username);
}

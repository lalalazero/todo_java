package com.lalalazero.todos.service.impl;

import com.lalalazero.todos.consts.ResultEnum;
import com.lalalazero.todos.model.User;
import com.lalalazero.todos.dao.UserRepository;
import com.lalalazero.todos.service.ListService;
import com.lalalazero.todos.service.UserService;
import com.lalalazero.todos.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Date 2018/12/24 上午9:28
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ListService listService;


    @Override
    @Transactional
    public Result register(String username, String password){
        if(isNameUnique(username)){
            User user = new User(username, password);
            userRepository.save(user);
            listService.createList("计划",user.getId(),0);
            return Result.Success(user.getId());
        }
        return Result.Error(ResultEnum.USERNAME_NOT_UNIQUE);

    }

    @Override
    public Result login(String username, String password) {

        List<User> list = userRepository.findByUsernameEquals(username);
        if(list.size() > 0){
            User user = list.get(0);
            if(password.equals(user.getPassword())){
                return  Result.Success(user.getId());
            }
            return Result.Error(ResultEnum.WRONG_PASS);
        }
        return Result.Error(ResultEnum.USER_NON_EXIST);

    }

    private boolean isNameUnique(String username){
        return userRepository.findByUsernameEquals(username).size() > 0 ? false : true;
    }


}

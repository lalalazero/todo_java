package com.lalalazero.todos.service.impl;

import com.lalalazero.todos.consts.ResultEnum;
import com.lalalazero.todos.model.User;
import com.lalalazero.todos.dao.UserRepository;
import com.lalalazero.todos.model.dto.UserDTO;
import com.lalalazero.todos.service.ListService;
import com.lalalazero.todos.service.UserService;
import com.lalalazero.todos.service.JWT;
import com.lalalazero.todos.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @Date 2018/12/24 上午9:28
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ListService listService;
    @Autowired
    JWT jwt;


    @Override
    @Transactional
    public Result register(String username, String password){
        if(!isExist(username)){
            User user = new User(username, password);
            userRepository.save(user);
            listService.createList("计划",user.getId(),0);
            listService.createList("今天",user.getId(),0);
            listService.createList("星标",user.getId(),0);
            Map<String,Object> res = new HashMap<>();
            res.put("token",jwt.newToken(username));
            UserDTO dto = new UserDTO(user.getId(), user.getUsername(), user.getHeaderImg());
            res.put("user", dto);
            return Result.Success(res);
        }
        return Result.Error(ResultEnum.USERNAME_NOT_UNIQUE);

    }

    @Override
    public Result login(String username, String password) {

        User user = userRepository.findUserByUsername(username);
        if(user != null){
            if(password.equals(user.getPassword())){
                Map<String,Object> res = new HashMap<>();
                res.put("token",jwt.newToken(username));
                UserDTO dto = new UserDTO(user.getId(), user.getUsername(), user.getHeaderImg());
                res.put("user", dto);
                return  Result.Success(res);
            }
            return Result.Error(ResultEnum.WRONG_PASS);
        }
        return Result.Error(ResultEnum.USER_NON_EXIST);

    }

    @Override
    public Boolean isExist(String username) {
        return userRepository.findUserByUsername(username) == null ? false : true;
    }

    @Override
    public Result userInfo(Integer userId) {
        try {
            User user = userRepository.findById(userId).get();
            return Result.Success(new UserDTO(user.getId(),user.getUsername(), user.getHeaderImg()));
        }catch (NoSuchElementException e){
            return Result.Error(ResultEnum.USER_NON_EXIST);
        }

    }


}

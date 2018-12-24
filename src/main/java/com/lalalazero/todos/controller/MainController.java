package com.lalalazero.todos.controller;

import com.lalalazero.todos.service.UserService;
import com.lalalazero.todos.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Date 2018/12/22 上午11:26
 */
@Controller
@RequestMapping("api")
public class MainController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    @ResponseBody
    public Object login(@RequestBody Map<String,String> body){
        String userName = body.get("username");
        String password = body.get("password");
        if(userName.equals("lalala") && password.equals("123")){
            return Response.Success("登陆成功");
        }

        return Response.Error("用户名或者密码错误");
    }

    @PostMapping("register")
    @ResponseBody
    public Object register(@RequestBody Map<String,String> body){
        String userName = body.get("username");
        String password = body.get("password");
        return userService.register(userName, password);
    }

}

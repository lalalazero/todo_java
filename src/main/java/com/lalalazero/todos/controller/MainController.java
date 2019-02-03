package com.lalalazero.todos.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lalalazero.todos.consts.ResultEnum;
import com.lalalazero.todos.model.dto.TodoDTO;
import com.lalalazero.todos.service.ListService;
import com.lalalazero.todos.service.TodoService;
import com.lalalazero.todos.service.UserService;
import com.lalalazero.todos.service.JWT;
import com.lalalazero.todos.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @Date 2018/12/22 上午11:26
 */
@Controller
@RequestMapping("api")
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    ListService listService;

    @Autowired
    TodoService todoService;

    @Autowired
    JWT jwt;

    public static void main(String[] args) {
//        String t = "AAAAA.BBBB.Ccccc";
//        String B = "AA.BB.CC";
//        String c = "ABBBBBBccc.B.c";
//        String regex = "((\\w)*\\.){2}(\\w)*";
//        System.out.println(t.matches(regex));
//        System.out.println(B.matches(regex));
//        System.out.println(c.matches(regex));

        String s = "1548857264120";
        System.out.println(new Date(s));
    }

    @GetMapping("valid")
    @ResponseBody
    public Object valid(HttpServletRequest request){
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            return Result.Error("后台校验失败，token is empty");
        }
        if(!token.matches("(.*\\.){2}.*"))
        {
            System.out.println("正则校验不通过");
            return Result.Error("正则校验不通过");
        }
        if(jwt.isTokenValid(token)){
            return Result.Success();
        };
        return Result.Error("后台校验失败， token is not valid");
    }

    @PostMapping("login")
    @ResponseBody
    public Object login(@RequestBody Map<String,String> body){
        String userName = body.get("username");
        String password = body.get("password");
        return userService.login(userName, password);
    }

    @PostMapping("register")
    @ResponseBody
    public Object register(@RequestBody Map<String,String> body){
        String userName = body.get("username");
        String password = body.get("password");
        return userService.register(userName, password);
    }

    @GetMapping("lists")
    @ResponseBody
    public Object queryUserlist(@RequestParam("userid") Integer userid){
        return listService.queryUserList(userid);
    }

    @ResponseBody
    @PostMapping("lists")
    public Object createList(@RequestParam("userid") Integer userid, @RequestParam("name") String listName){
        return listService.createList(listName, userid, 1);
    }

    @DeleteMapping("lists")
    @ResponseBody
    public Object deleteList(@RequestParam("id") Integer listId){
        return listService.deleteList(listId);
    }

    @PutMapping("lists")
    @ResponseBody
    public Object updateList(@RequestParam("id") Integer listId, @RequestParam("name") String newerName){
        return listService.update(listId, newerName);
    }


    @GetMapping("lists/items")
    @ResponseBody
    public Object queryListItem(@RequestParam("id") Integer listId,@RequestParam("type") Integer type){
        if(type != 0 && type != 1){
            return Result.Error(ResultEnum.WRONG_PARAM);
        }
        return listService.queryListTodos(listId,type);
    }

    @GetMapping("lists/items/detail")
    @ResponseBody
    public Object queryItemDetail(@RequestParam("id") Integer itemId){
        return todoService.queryDetail(itemId);
    }



    @PostMapping("lists/items")
    @ResponseBody
    public Object createTodo(@RequestBody Map<String, Object> body){
        JSONObject json = new JSONObject(body);
        Integer listId = (Integer)json.get("id");
        TodoDTO todo = JSON.parseObject(JSON.toJSONString(json.get("todo")), TodoDTO.class);
        if(todo.getDue() == null){
            return todoService.add(todo.getValue(), todo.getMarked(), null, listId);
        }else{
            return todoService.add(todo.getValue(), todo.getMarked(),
                    new Date(todo.getDue()), listId);
        }

    }

    @PutMapping("lists/items/star")
    @ResponseBody
    public Object markStar(@RequestParam("id") Integer todoId, @RequestParam("stared") Integer stared){
        return todoService.markStar(todoId, stared);
    }

    @PutMapping("lists/items/status")
    @ResponseBody
    public Object checkItem(@RequestParam("id") Integer todoId,@RequestParam("status") Integer status){
        return todoService.check(todoId,status);
    }

    @DeleteMapping("lists/items")
    @ResponseBody
    public Object deleteTodo(@RequestParam("id") Integer todoId){
        return todoService.delete(todoId);
    }

    @ResponseBody
    @PutMapping("lists/items")
    public Object updateTodo(@RequestBody Map<String,Object> body){
        Integer id = (Integer) body.get("id");
        String title = (String)body.get("title");
        String note = (String)body.get("note");
        return todoService.update(id, title, note);
    }


}

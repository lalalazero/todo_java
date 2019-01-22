package com.lalalazero.todos;

import com.alibaba.fastjson.JSON;
import com.lalalazero.todos.dao.UserRepository;
import com.lalalazero.todos.model.User;
import com.lalalazero.todos.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodosApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	public void contextLoads() {
		Map<String,Object> res = new HashMap<>();
		res.put("token","12345");
		res.put("userId",1);
		Result r = Result.Success(res);
		System.out.println(JSON.toJSON(r));
	}



}


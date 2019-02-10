package com.lalalazero.todos;

import com.alibaba.fastjson.JSON;
import com.lalalazero.todos.dao.TodoItemRepository;
import com.lalalazero.todos.dao.TodoListRepository;
import com.lalalazero.todos.dao.UserRepository;
import com.lalalazero.todos.model.TodoItem;
import com.lalalazero.todos.model.TodoList;
import com.lalalazero.todos.model.User;
import com.lalalazero.todos.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodosApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TodoItemRepository todoItemRepository;

	@Autowired
	TodoListRepository listRepository;

	@Test
	public void contextLoads() {
//		Map<String,Object> res = new HashMap<>();
//		res.put("token","12345");
//		res.put("userId",1);
//		Result r = Result.Success(res);
//		System.out.println(JSON.toJSON(r));

//		Date date = new Date();
//		long time = date.getTime();
//		TodoItem item = new TodoItem();
//		item.setFinished(date);
//		item.setDue(new Date(time));
//		item.setValue("context 测试 date");
//		System.out.println("date => " + date);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("formate " + sdf.format(date));
//		todoItemRepository.save(item);

//		test();
//		test2();

	}

//	private void test(){
//
//		List< TodoList > todoLists = listRepository.findByUserId(8);
//		List<Integer> listIds = todoLists.stream().map(TodoList::getId).collect(toList());
//
//		List<TodoItem> todos = todoItemRepository.findAllByDoneAndListIdIn(0, listIds);
//
//		Calendar canlendar = Calendar.getInstance();
//		canlendar.set(Calendar.MINUTE, 0);
//		canlendar.set(Calendar.SECOND, 0);
//		canlendar.set(Calendar.HOUR_OF_DAY, 0);
//		Date start = canlendar.getTime();
//		canlendar.set(Calendar.HOUR_OF_DAY, 24);
//		Date end = canlendar.getTime();
//
//		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(sdf.format(end) + " , " + sdf.format(start));
//		List<TodoItem> todos2 = todoItemRepository.findAllByDueLessThanEqualAndDueGreaterThanEqualAndDoneAndListIdIn(end, start, 0, listIds);
//		System.out.println(todos.size());
//	}
//
//	private void test2(){
//		Calendar canlendar = Calendar.getInstance();
//		canlendar.set(Calendar.MINUTE, 0);
//		canlendar.set(Calendar.SECOND, 0);
//		canlendar.set(Calendar.HOUR_OF_DAY, 0);
//		Date start = canlendar.getTime();
//		canlendar.set(Calendar.HOUR_OF_DAY, 24);
//		Date end = canlendar.getTime();
//
//		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(sdf.format(end) + " , " + sdf.format(start));
//
//		List<TodoItem> list = todoItemRepository.findAllByDueLessThanEqualAndDueGreaterThanEqual(end, start);
//		System.out.println(list.size());
//	}



}


package com.lalalazero.todos.dao;

import com.lalalazero.todos.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Date 2018/12/24 下午4:17
 */
public interface TodoListRepository extends JpaRepository<TodoList, Integer>, JpaSpecificationExecutor<TodoList>{

    List<TodoList> findByUserId(Integer userid);


}

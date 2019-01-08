package com.lalalazero.todos.dao;

import com.lalalazero.todos.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Date 2018/12/27 下午1:22
 */
public interface TodoItemRepository extends JpaRepository<TodoItem,Integer>, JpaSpecificationExecutor<TodoItem>{

    List<TodoItem> queryAllByDoneAndAndListId(Integer done, Integer listId);

    void deleteAllByListId(Integer listId);
}

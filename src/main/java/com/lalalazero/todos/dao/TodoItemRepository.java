package com.lalalazero.todos.dao;

import com.lalalazero.todos.model.TodoItem;
import com.sun.tools.javac.comp.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

/**
 * @Date 2018/12/27 下午1:22
 */
public interface TodoItemRepository extends JpaRepository<TodoItem,Integer>, JpaSpecificationExecutor<TodoItem>{

    List<TodoItem> queryAllByDoneAndAndListId(Integer done, Integer listId);

    void deleteAllByListId(Integer listId);

    List<TodoItem> findAllByDoneAndStarAndListId(Integer done, Integer mark, Integer listId);

    List<TodoItem> findAllByDueLessThanEqualAndDueGreaterThanEqualAndDoneAndListIdIn(Date end, Date start, Integer done, List<Integer> listIds);

    List<TodoItem> findAllByListIdIn(List<Integer> listIds);

//    List<TodoItem> findAllByDoneAndListIdIn(Integer done, List<Integer> listIds);

//    List<TodoItem> findAllByDueLessThanEqualAndDueGreaterThanEqual(Date end, Date start);
}

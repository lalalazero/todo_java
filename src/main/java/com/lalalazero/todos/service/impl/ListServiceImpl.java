package com.lalalazero.todos.service.impl;

import com.lalalazero.todos.consts.ResultEnum;
import com.lalalazero.todos.model.TodoItem;
import com.lalalazero.todos.model.TodoList;
import com.lalalazero.todos.dao.TodoItemRepository;
import com.lalalazero.todos.dao.TodoListRepository;
import com.lalalazero.todos.service.ListService;
import com.lalalazero.todos.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @Date 2018/12/24 下午3:59
 */
@Service
public class ListServiceImpl implements ListService{

    @Autowired
    TodoListRepository listRepository;
    @Autowired
    TodoItemRepository itemRepository;

    @Override
    public Result queryUserList(Integer userId) {
        List<TodoList> lists = listRepository.findByUserId(userId);
        lists.stream().sorted(new Comparator<TodoList>() {
            @Override
            public int compare(TodoList o1, TodoList o2) {
                return o1.getId() > o2.getId() ? 1 : -1;
            }
        }).forEach(obj -> {
            Integer validCount = itemRepository.queryAllByDoneAndAndListId(0,obj.getId()).size();
            obj.setValidCount(validCount);});
        return Result.Success(lists);
    }

    @Override
    @Transactional
    public Result createList(String listname, Integer userId, Integer listType) {
        if(0 != listType && 1 != listType){
            return Result.Error(ResultEnum.WRONG_PARAM);
        }
        TodoList list = new TodoList(userId, listname);
        list.setUserCreate(listType);
        listRepository.save(list);
        return Result.Success();
    }

    @Override
    @Transactional
    public Result deleteList(Integer listId) {
        try{
            // 删除清单相关的todo
            itemRepository.deleteAllByListId(listId);
            listRepository.deleteById(listId);
            return Result.Success();
        }catch (EmptyResultDataAccessException e){
            return Result.Success();
        }

    }

    @Override
    @Transactional
    public Result update(Integer listId, String newerName) {
        try{
            TodoList list = listRepository.findById(listId).get();
            list.setName(newerName);
            listRepository.save(list);
            return Result.Success();
        }catch (NoSuchElementException e){
            return Result.Error(ResultEnum.LIST_NON_EXIST);
        }
    }

    @Override
    public boolean isListExist(Integer listId) {
        try{
            listRepository.findById(listId).get();
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    @Override
    public Result queryListTodos(Integer listId, Integer type) {
        return Result.Success(itemRepository.queryAllByDoneAndAndListId(type, listId).stream().sorted(new Comparator<TodoItem>() {
            @Override
            public int compare(TodoItem o1, TodoItem o2) {
                return o1.getId() > o2.getId() ? 1 : -1;
            }
        }));
    }
}

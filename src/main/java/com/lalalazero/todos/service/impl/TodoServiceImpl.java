package com.lalalazero.todos.service.impl;

import com.lalalazero.todos.consts.ResultEnum;
import com.lalalazero.todos.model.TodoItem;
import com.lalalazero.todos.dao.TodoItemRepository;
import com.lalalazero.todos.service.ListService;
import com.lalalazero.todos.service.TodoService;
import com.lalalazero.todos.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

/**
 * @Date 2018/12/27 下午1:28
 */
@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    ListService listService;

    @Autowired
    TodoItemRepository todoItemRepository;

    @Override
    @Transactional
    public Result add(String value, Integer listId) {
        if(listService.isListExist(listId)){
            TodoItem todo = new TodoItem(value,listId);
            todoItemRepository.save(todo);
            return Result.Success();
        }else{
            return Result.Error(ResultEnum.LIST_NON_EXIST);
        }

    }

    @Override
    @Transactional
    public Result delete(Integer todoId) {
        try{
            todoItemRepository.deleteById(todoId);
            return Result.Success();
        }catch (NoSuchElementException e){
            return Result.Success();
        }
    }

    @Override
    @Transactional
    public Result update(Integer todoId, String value, String note) {
        try{
            TodoItem todo = todoItemRepository.findById(todoId).get();
            todo.setValue(value);
            todo.setNote(note);
            todoItemRepository.save(todo);
            return Result.Success();
        }catch (NoSuchElementException e){
            return Result.Error(ResultEnum.NON_TODO_EXIST);
        }
    }

    @Override
    @Transactional
    public Result check(Integer todoId,Integer status) {
        if(0 != status && 1 != status){
            return Result.Error(ResultEnum.WRONG_PARAM);
        }
        try{
            TodoItem todo = todoItemRepository.findById(todoId).get();
            todo.setDone(status);
            todoItemRepository.save(todo);
            return Result.Success();
        }catch (NoSuchElementException e){
            return Result.Error(ResultEnum.NON_TODO_EXIST);
        }
    }

    @Override
    public Result queryDetail(Integer todoId) {
        try{
            return Result.Success(todoItemRepository.findById(todoId).get());
        }catch (NoSuchElementException e){
            return Result.Error(ResultEnum.NON_TODO_EXIST);
        }
    }


}

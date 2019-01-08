package com.lalalazero.todos.model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Date 2018/12/24 下午3:56
 */
@Data
@ToString
@Entity
public class TodoList {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;
    String name;
    Integer userId;
    Integer validCount = 0;
    Integer userCreate = 0;

    public TodoList(){

    }

    public TodoList(Integer userId, String name){
        this.userId = userId;
        this.name = name;
    }

}

package com.lalalazero.todos.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Date 2018/12/27 下午1:19
 */
@Entity
@Data
@ToString
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;

    Integer done = 0; // 0-未完成 1-完成
    String note; // 备注
    String value;
    Integer listId;

    public TodoItem(){

    }

    public TodoItem(String value, Integer listId){
        this.value = value;
        this.listId = listId;
    }
}

package com.lalalazero.todos.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Date 2018/12/24 上午9:31
 */
@Entity
@Data
@ToString
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;
    String username;
    String password;

    public User(){

    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}

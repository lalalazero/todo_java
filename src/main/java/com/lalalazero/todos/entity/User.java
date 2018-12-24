package com.lalalazero.todos.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * @Date 2018/12/24 上午9:31
 */
@Entity
@Data
@ToString
public class User {

    Integer id;
    String username;
    String password;
}

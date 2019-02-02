package com.lalalazero.todos.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Date 2019/2/2 下午5:54
 */
@Data
public class TodoDTO {

    Long due;
    String value;
    Integer marked;
}

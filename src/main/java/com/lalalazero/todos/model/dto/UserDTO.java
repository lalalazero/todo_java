package com.lalalazero.todos.model.dto;

import lombok.Data;

/**
 * @Date 2019/2/10 下午1:29
 */
@Data
public class UserDTO {

    Integer id;
    String name;
    String headerImg;

    public UserDTO(Integer id, String name, String headerImg){
        this.id = id;
        this.name = name;
        this.headerImg = headerImg;
    }

}

package com.lalalazero.todos.jpa;

import com.lalalazero.todos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Date 2018/12/24 上午9:34
 */
public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User>{

    List<User> findByUsernameEquals(String userName);
}

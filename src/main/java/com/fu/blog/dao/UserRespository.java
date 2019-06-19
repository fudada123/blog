package com.fu.blog.dao;

import com.fu.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fudada
 * @date 2019/6/19 - 17:16
 */
public interface UserRespository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);

}

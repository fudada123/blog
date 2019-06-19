package com.fu.blog.service;

import com.fu.blog.po.User;

/**
 * @author fudada
 * @date 2019/6/19 - 17:13
 */
public interface UserService {

    User checkUser(String username, String password);
}

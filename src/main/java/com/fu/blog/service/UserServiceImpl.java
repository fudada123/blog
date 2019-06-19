package com.fu.blog.service;

import com.fu.blog.dao.UserRespository;
import com.fu.blog.po.User;
import com.fu.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fudada
 * @date 2019/6/19 - 17:14
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRespository userRespository;


    @Override
    public User checkUser(String username, String password) {
        User user = userRespository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

}

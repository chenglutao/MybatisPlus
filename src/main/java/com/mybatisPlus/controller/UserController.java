package com.mybatisPlus.controller;


import com.mybatisPlus.entity.User;
import com.mybatisPlus.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-11-06
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("list")
    public Object list(){
        User user = new User();
        user.setName("成路涛");
        user.setPhone("18813024889");
        user.setAge(25);
        return userMapper.insert(user);
    }
}

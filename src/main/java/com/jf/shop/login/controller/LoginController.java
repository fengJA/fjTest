package com.jf.shop.login.controller;

import com.jf.shop.login.entity.Users;
import com.jf.shop.login.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    UsersService usersService;

    @RequestMapping("/login")
    public String goto_login(Users user){


        return null;
    }

}

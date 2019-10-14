package com.jf.shop.login.service;

import com.jf.shop.login.entity.Users;

public interface UsersService {

    Users selectUsersByName(Users users);
    Users selectUsersById(Integer id);

    int insertUsers(Users users);

    int deleteUsersById(Integer id);

    int updateUsersById(Integer id);
}

package com.jf.shop.login.service.Impl;

import com.jf.shop.login.dao.UsersDao;
import com.jf.shop.login.entity.Users;
import com.jf.shop.login.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImp implements UsersService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public Users selectUsersByName(Users users) {
        return usersDao.selectUsersByName(users);
    }

    @Override
    public Users selectUsersById(Integer id) {
        return usersDao.selectUsersById(id);
    }

    @Override
    public int insertUsers(Users users) {
        return usersDao.insertUsers(users);
    }

    @Override
    public int deleteUsersById(Integer id) {
        return usersDao.deleteUsersById(id);
    }

    @Override
    public int updateUsersById(Integer id) {
        return usersDao.updateUsersById(id);
    }
}

package com.jf.shop.login.dao;

import com.jf.shop.login.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UsersDao {


    Users selectUsersByName(Users users);
    Users selectUsersById(Integer id);

    int insertUsers(Users users);

    int deleteUsersById(Integer id);

    int updateUsersById(Integer id);
}

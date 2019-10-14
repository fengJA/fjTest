package com.jf.shop.login.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Users {

    private Integer id;
    private String username;
    private String password;
    private Integer tel;
    private String email;
    private Date lastLoginTime;

    public Users(Integer id) {
        this.id = id;
    }

    public Users() {
        super();
    }

    public Users(Integer id, String username, String password, Integer tel, String email, Date lastLoginTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.email = email;
        this.lastLoginTime = lastLoginTime;
    }
}

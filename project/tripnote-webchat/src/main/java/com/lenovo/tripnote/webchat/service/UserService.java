package com.lenovo.tripnote.webchat.service;

import com.github.pagehelper.Page;

/**
 * Created by Administrator on 2017/8/16.
 */
public interface UserService<T> {

    int addUser(T user);

    Page<T> findAllUser(int pageNum, int pageSize);
}
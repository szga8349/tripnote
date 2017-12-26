package com.lenovo.tripnote.service;

import com.lenovo.tripnote.entity.User;

public interface UserService {

    User searchById(String id);

    int insert(User user);

    int delete(String id);
}
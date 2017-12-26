package com.lenovo.tripnote.service;

import java.util.Set;

import com.lenovo.tripnote.entity.User;

public interface UserService {

    User searchById(String id);

    int insert(User user);

    int delete(String id);
    
    User getByUsername(String userName);

	Set<String> getRoles(String username);

	Set<String> getPermissions(String username);
}
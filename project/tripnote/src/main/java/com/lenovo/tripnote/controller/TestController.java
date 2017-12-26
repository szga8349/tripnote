package com.lenovo.tripnote.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lenovo.tripnote.entity.User;
import com.lenovo.tripnote.service.UserService;

@Controller
public class TestController {
	@Autowired
    private UserService userService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	
	public  @ResponseBody List<User> Test(){
		System.out.println(userService);
		User user = new User();
		user.setId("1");
		user.setName("Test");
		user.setPwd("Password");
		List<User> l = new ArrayList<User>();
		l.add(user);
		return l;
	}
}

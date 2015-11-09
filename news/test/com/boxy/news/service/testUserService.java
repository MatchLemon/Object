package com.boxy.news.service;

import java.util.List;

import org.junit.Test;

import com.boxy.news.bean.User;
import com.boxy.news.service.impl.UserServiceImpl;

public class testUserService {
	@Test
	public void testFindAll(){
		UserService userService = new UserServiceImpl();
		
		List<User> users = userService.findAll();
		
		for(User user : users){
			System.out.println(user);
		}
	}
}

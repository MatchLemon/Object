package com.boxy.news.service;

import com.boxy.news.bean.User;
import com.boxy.service.GenericService;

public interface UserService extends GenericService<User> {
	public boolean login(String userName, String password);
	public void register(User user);
}

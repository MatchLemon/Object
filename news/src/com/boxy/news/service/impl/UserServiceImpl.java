package com.boxy.news.service.impl;

import com.boxy.dao.GenericDao;
import com.boxy.news.bean.User;
import com.boxy.news.dao.impl.UserDaoImpl;
import com.boxy.news.service.UserService;
import com.boxy.service.impl.GenericServiceImpl;

public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {
	public UserServiceImpl(){
		this(new UserDaoImpl());
	}
	
	public UserServiceImpl(GenericDao<User> dao) {
		super(dao);
	}

	@Override
	public boolean login(String userName, String password) {
		User user = dao.find(" where user_name = ? ", userName);
		
		return user != null && user.getPassword().equals(password);
	}

	@Override
	public void register(User user) {
		dao.add(user);
	}
}

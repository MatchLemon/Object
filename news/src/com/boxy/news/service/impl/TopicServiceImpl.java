package com.boxy.news.service.impl;

import com.boxy.dao.GenericDao;
import com.boxy.news.bean.Topic;
import com.boxy.news.dao.impl.TopicDaoImpl;
import com.boxy.news.service.TopicService;
import com.boxy.service.impl.GenericServiceImpl;

public class TopicServiceImpl extends GenericServiceImpl<Topic> implements TopicService{
	public TopicServiceImpl(){
		this(new TopicDaoImpl());
	}
	public TopicServiceImpl(GenericDao<Topic> dao) {
		super(dao);
	}
}

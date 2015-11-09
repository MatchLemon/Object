package com.boxy.news.service.impl;

import java.util.List;

import com.boxy.dao.GenericDao;
import com.boxy.news.bean.News;
import com.boxy.news.bean.Topic;
import com.boxy.news.dao.TopicDao;
import com.boxy.news.dao.impl.NewsDaoImpl;
import com.boxy.news.dao.impl.TopicDaoImpl;
import com.boxy.news.service.NewsService;
import com.boxy.service.PagedList;
import com.boxy.service.impl.GenericServiceImpl;

public class NewsServiceImpl extends GenericServiceImpl<News> implements NewsService{
	private TopicDao topicDao = new TopicDaoImpl();
	
	public NewsServiceImpl(){
		this(new NewsDaoImpl());
	}
	public NewsServiceImpl(GenericDao<News> dao) {
		super(dao);
	}
	
	@Override
	public PagedList<News> findPage(int pageIndex, int pageSize, int topicId) {
		int totalCount = 0;
		List<News> news = null;
		
		String where = "";
		String order = "order by id desc";
		
		if(topicId != 0)
		{
			where = " where topic_id = ? "; 
			totalCount = dao.totalCount(where, topicId);
			news = dao.findPage(pageIndex, pageSize, where, order, topicId);
		}
		else{
			totalCount = dao.totalCount();
			news = dao.findPage(pageIndex, pageSize, where, order);
		}
		
		return new PagedList<News>(pageIndex, pageSize, totalCount, news);
	}
	
	@Override
	public PagedList<News> findPage(int pageIndex, int pageSize, String topic) {
		// 获取主题Id
		int topicId = 0;
		Topic t = topicDao.find(" where topic_name = ?", topic);
		
		if(t != null){
			topicId = t.getId();
		}
		
		return  this.findPage(pageIndex, pageSize, topicId);
	}
}

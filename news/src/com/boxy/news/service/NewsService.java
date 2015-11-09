package com.boxy.news.service;

import com.boxy.news.bean.News;
import com.boxy.service.GenericService;
import com.boxy.service.PagedList;

//新闻
public interface NewsService extends GenericService<News> {
	public PagedList<News> findPage(int pageIndex, int pageSize, int topicId);
	public PagedList<News> findPage(int pageIndex, int pageSize, String topic);
}

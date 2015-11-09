package com.boxy.news.service;

import java.util.List;

import org.junit.Test;

import com.boxy.news.bean.News;
import com.boxy.news.service.impl.NewsServiceImpl;

public class testNewsService {
	@Test
	public void testFindAll(){
		NewsService newsService = new NewsServiceImpl();
		
		List<News> news = newsService.findAll();
		
		for(News n : news){
			System.out.println(n);
		}
	}
}

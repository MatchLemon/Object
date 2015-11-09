package com.boxy.news.service;

import java.util.List;

import org.junit.Test;

import com.boxy.news.bean.Topic;
import com.boxy.news.service.impl.TopicServiceImpl;

public class testTopicService {
	@Test
	public void testFindAll(){
		TopicService topicService = new TopicServiceImpl();
		
		List<Topic> topic = topicService.findAll();
		
		for(Topic t : topic){
			System.out.println(t);
		}
	}
}

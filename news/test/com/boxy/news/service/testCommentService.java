package com.boxy.news.service;

import java.util.List;

import org.junit.Test;

import com.boxy.news.bean.Comment;
import com.boxy.news.service.impl.CommentServiceImpl;

public class testCommentService {
	@Test
	public void testFindAll(){
		CommentService commentService = new CommentServiceImpl();
		
		List<Comment> comments = commentService.findAll();
		
		for(Comment comment : comments){
			System.out.println(comment);
		}
	}
}

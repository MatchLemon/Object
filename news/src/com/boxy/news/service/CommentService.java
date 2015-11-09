package com.boxy.news.service;

import java.util.List;

import com.boxy.news.bean.Comment;
import com.boxy.service.GenericService;

public interface CommentService extends GenericService<Comment> {
	public List<Comment> findComments(Integer newsId);
}

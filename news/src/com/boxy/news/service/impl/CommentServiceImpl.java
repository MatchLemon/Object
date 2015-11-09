package com.boxy.news.service.impl;

import java.util.List;

import com.boxy.dao.GenericDao;
import com.boxy.news.bean.Comment;
import com.boxy.news.dao.impl.CommentDaoImpl;
import com.boxy.news.service.CommentService;
import com.boxy.service.impl.GenericServiceImpl;

public class CommentServiceImpl extends GenericServiceImpl<Comment> implements CommentService{
	public CommentServiceImpl(){
		this(new CommentDaoImpl());
	}
	public CommentServiceImpl(GenericDao<Comment> dao) {
		super(dao);
	}
	@Override
	public List<Comment> findComments(Integer newsId) {
		return dao.findAll(" where news_id = ? ", " order by id desc ", newsId);
	}
}

package com.boxy.news.bean;

import java.util.Date;

import com.boxy.dao.annotation.Column;
import com.boxy.dao.annotation.Entity;
import com.boxy.dao.annotation.Key;

@Entity("comments")
public class Comment {
	@Key
	private Integer id;
	@Column("news_id")
	private Integer newsId;
	@Column
	private String content;
	@Column
	private String author;
	@Column("author_ip")
	private String authorIp;
	@Column("create_date")
	private Date createDate;
	
	public Comment() {
	}

	public Comment(Integer id, Integer newsId, String content, String author,
			String authorIp, Date createDate) {
		this.id = id;
		this.newsId = newsId;
		this.content = content;
		this.author = author;
		this.authorIp = authorIp;
		this.createDate = createDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthorIp() {
		return authorIp;
	}
	public void setAuthorIp(String authorIp) {
		this.authorIp = authorIp;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", newsId=" + newsId + ", content="
				+ content + ", author=" + author + ", authorIp=" + authorIp
				+ ", createDate=" + createDate + "]";
	}
}

package com.boxy.news.bean;

import java.util.Date;

import com.boxy.dao.annotation.Column;
import com.boxy.dao.annotation.Entity;
import com.boxy.dao.annotation.Key;

@Entity
public class News {
	@Key
	private Integer id;
	@Column
	private String title;
	@Column
	private String summary;
	@Column
	private String content;
	@Column("image_url")
	private String imageUrl;
	@Column
	private String author;
	@Column("create_date")
	private Date createDate;
	@Column("modify_date")
	private Date modifyDate;
	@Column("topic_id")
	private Integer topicId;
	
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public News(Integer id, String title, String summary, String content,
			String imageUrl, String author, Date createDate, Date modifyDate,
			Integer topicId) {
		super();
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.content = content;
		this.imageUrl = imageUrl;
		this.author = author;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.topicId = topicId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", summary=" + summary
				+ ", content=" + content + ", imageUrl=" + imageUrl
				+ ", author=" + author + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", topicId=" + topicId + "]";
	}
	
	
}

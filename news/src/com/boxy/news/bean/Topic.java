package com.boxy.news.bean;

import com.boxy.dao.annotation.Column;
import com.boxy.dao.annotation.Entity;
import com.boxy.dao.annotation.Key;

@Entity
public class Topic {
	@Key
	private Integer id;
	@Column("topic_name")
	private String topicName;
	
	public Topic() {
	}
	public Topic(Integer id, String topicName) {
		this.id = id;
		this.topicName = topicName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	@Override
	public String toString() {
		return "Topic [id=" + id + ", topicName=" + topicName + "]";
	}
}

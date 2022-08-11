package com.cg.dca.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feeds")
public class Feed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int feedId;
	
	@Column(name = "query")
	private String query;
	
	@Column(name = "feed_date")
	private LocalDate feedDate;
	
	@Column(name = "feed_time")
	private LocalTime feedTime;
	
	@Column(name = "topic")
	private String topic;
	
	@Column(name = "relevance")
	private int relevance;
	
//	private Developer dev;
	
//	@Column(name = "reponses")
//	private List<Response> responses;
	
	@Column(name = "total_comments")
	private int totalComments;

	public Feed() {
	}

	public Feed(String query, LocalDate feedDate, LocalTime feedTime, String topic, int relevance, int totalComments) {
		super();
		this.query = query;
		this.feedDate = feedDate;
		this.feedTime = feedTime;
		this.topic = topic;
		this.relevance = relevance;
		this.totalComments = totalComments;
	}

	public int getFeedId() {
		return feedId;
	}

	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public LocalDate getFeedDate() {
		return feedDate;
	}

	public void setFeedDate(LocalDate feedDate) {
		this.feedDate = feedDate;
	}

	public LocalTime getFeedTime() {
		return feedTime;
	}

	public void setFeedTime(LocalTime feedTime) {
		this.feedTime = feedTime;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getRelevance() {
		return relevance;
	}

	public void setRelevance(int relevance) {
		this.relevance = relevance;
	}

	public int getTotalComments() {
		return totalComments;
	}

	public void setTotalComments(int totalComments) {
		this.totalComments = totalComments;
	}

	@Override
	public String toString() {
		return "Feed [feedId=" + feedId + ", query=" + query + ", feedDate=" + feedDate + ", feedTime=" + feedTime
				+ ", topic=" + topic + ", relevance=" + relevance + ", totalComments="
				+ totalComments + "]";
	}
	
	
	
}

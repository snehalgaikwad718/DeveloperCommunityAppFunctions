package com.cg.dca.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;
import javax.transaction.Transactional;

import lombok.Data;

@Entity
@Transactional
@Data
@Table(name = "feeds")
public class Feed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long feedId;
	private String query;
	private LocalDate feedDate = LocalDate.now();
	private LocalTime feedTime = LocalTime.now();
	private String topic;
	private int relevance;

	public long getFeedId() {
		return feedId;
	}

	public void setFeedId(long feedId) {
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
	
}

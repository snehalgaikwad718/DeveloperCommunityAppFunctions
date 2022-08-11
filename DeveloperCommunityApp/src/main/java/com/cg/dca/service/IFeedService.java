package com.cg.dca.service;

import java.util.List;

import com.cg.dca.entity.Feed;

public interface IFeedService {
	
	List<Feed> getFeedByTopic(String topic);

	List<Feed> getFeedByKeyword(String keyword);

}

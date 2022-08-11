package com.cg.dca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.dca.entity.Feed;

@Repository
public interface IFeedRepository extends JpaRepository<Feed, Long>{

//	List<Feed> fetchFeedByTopic(String topic);
	

}

package com.cg.dca.repository;

import java.util.List;

import com.cg.dca.entity.Developer;
import com.cg.dca.entity.Feed;
import com.cg.dca.entity.Response;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface IDeveloperRepository extends JpaRepository<Developer, Long> {

	@Query("select feeds from Developer d where d.id = ?1")
	List<Feed> fetchFeedsByDevId(long devId);
	

	@Query("select responses from Developer d where d.id = ?1")
	List<Response> fetchResponseByDevId(Long devId);

}


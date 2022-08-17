package com.cg.dca.service;

import java.util.List;

import com.cg.dca.entity.Feed;
import com.cg.dca.entity.Response;
import com.cg.dca.repository.IDeveloperRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperService {
	
	@Autowired
	private IDeveloperRepository developerRepository;
	
	public List<Feed> fetchFeedsByDevId(long devId){
		return developerRepository.fetchFeedsByDevId(devId);
	}
	
	public List<Response> fetchResponseByDevId(long devId){
		return developerRepository.fetchResponseByDevId(devId);
	}
	
	
//	
//	Developer addDeveloper(Developer dev) throws UnknownDeveloperException;
//
//	Optional<Developer> getDeveloper(int devId);
//
//	Developer editDeveloper(Developer dev);
//
//	List<Developer> getAllDevelopers();
}

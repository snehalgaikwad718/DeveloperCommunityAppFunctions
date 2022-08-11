package com.cg.dca.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.dca.entity.Feed;
import com.cg.dca.exception.UnknownFeedException;
import com.cg.dca.repository.IFeedRepository;
import com.cg.dca.service.IFeedService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class FeedController {

	@Autowired
	private IFeedRepository feedRepository;
	private IFeedService feedService;
	
	// Get Feed
	@GetMapping("/feeds")
	public List<Feed> fetchFeed(){
		return feedRepository.findAll();
	}
	
	// Get Feed By Topic
	@GetMapping("/feeds/topic")
	public ResponseEntity<List<Feed>> fetchFeedsByTopic(@RequestParam String topic) {
		return new ResponseEntity<List<Feed>>(feedService.getFeedByTopic(topic), HttpStatus.OK);
	}
	
	// Get Feed By Keyword
		@GetMapping("/feeds/keyword")
		public ResponseEntity<List<Feed>> fetchFeedsByKeyword(@RequestParam String keyword) {
			return new ResponseEntity<List<Feed>>(feedService.getFeedByKeyword(keyword), HttpStatus.OK);
		}
	
	// Save Feed
		@PostMapping("/feeds")
		public Feed saveFeed(@RequestBody Feed feed) {
			return feedRepository.save(feed);
		}
		
		// Update Feed
		@PutMapping("/feeds/{id}")
		public ResponseEntity<Feed> updateFeed(@PathVariable(value = "id") Long feedId,
		@RequestBody Feed responseDetails) throws UnknownFeedException{
			
			Feed feed = feedRepository.findById(feedId)
					.orElseThrow(() -> new UnknownFeedException("Feed not found for this id :: " + feedId));
			
			feed.setQuery(responseDetails.getQuery());
			feed.setFeedDate(responseDetails.getFeedDate());
			feed.setFeedTime(responseDetails.getFeedTime());
			feed.setTopic(responseDetails.getTopic());
			final Feed updatedFeed  = feedRepository.save(feed);
			return ResponseEntity.ok(updatedFeed);
		}
		
		// Delete Feed
		@DeleteMapping("/feeds/{id}")
		public Map<String, Boolean> deleteFeed(@PathVariable(value = "id") Long feedId) throws UnknownFeedException{
			Feed feed = feedRepository.findById(feedId)
					.orElseThrow(() -> new UnknownFeedException("Feed not found for this id :: " + feedId));
			
			feedRepository.delete(feed);
			
			Map<String, Boolean> errorResponse = new HashMap<>();
			errorResponse.put("deleted", Boolean.TRUE);
			
			return errorResponse;
		}
}

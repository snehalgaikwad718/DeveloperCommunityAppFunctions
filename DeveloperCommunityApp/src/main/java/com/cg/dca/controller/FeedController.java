package com.cg.dca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@Autowired
	IFeedService feedService;
	
	// Get Feed
	@GetMapping("/feeds")
	public List<Feed> getAllDeveloper(){
		return feedRepository.findAll();
	}
	
	// Get Feed By Topic
	@GetMapping("/feeds/topic")
	public ResponseEntity<List<Feed>> fetchFeedsByTopic(@RequestParam String topic) throws UnknownFeedException {
		return new ResponseEntity<List<Feed>>(feedRepository.findByTopic(topic), HttpStatus.OK);
	}
	
	@PostMapping("/feeds")
	public Feed createResponse(@RequestBody Feed feedData) {
		return feedRepository.save(feedData);
	}
	
	// Get Feed By Id
	 	@GetMapping("/getFeed/{id}")
	 	public ResponseEntity<Feed> fetchFeedById(@PathVariable(value = "id") Long feedId) throws UnknownFeedException {
	 		Feed feed = feedRepository.findById(feedId)
	 				.orElseThrow(() -> new UnknownFeedException("Feed not found for this id :: " + feedId));
	 		return ResponseEntity.ok().body(feed);
	 	}
	
		// Update Feed
		@PutMapping("/feeds/{id}")
		public ResponseEntity<Feed> updateFeed(@PathVariable(value = "id") Long feedId,
		@RequestBody Feed feedDetails) throws UnknownFeedException{
			
			Feed feed = feedRepository.findById(feedId)
					.orElseThrow(() -> new UnknownFeedException("Feed not found for this id :: " + feedId));
			
			feed.setQuery(feedDetails.getQuery());
			feed.setFeedDate(feedDetails.getFeedDate());
			feed.setFeedTime(feedDetails.getFeedTime());
			feed.setTopic(feedDetails.getTopic());
			feed.setRelevance(feedDetails.getRelevance());
			final Feed updatedFeed  = feedRepository.save(feed);
			return ResponseEntity.ok(updatedFeed);
		}
		
		@GetMapping("feeds/{id}/like")
		public ResponseEntity<Feed> likeFeed(@PathVariable(value = "id") Long feedId) throws UnknownFeedException{
			Feed feed = feedRepository.findById(feedId)
					.orElseThrow(() -> new UnknownFeedException("Feed not found for this id :: " + feedId));
			
			int count = 0;
			count = feed.getRelevance() + 1;
			feed.setQuery(feed.getQuery());
			feed.setFeedDate(feed.getFeedDate());
			feed.setFeedTime(feed.getFeedTime());
			feed.setTopic(feed.getTopic());
			feed.setRelevance(count);
			final Feed likedFeed  = feedRepository.save(feed);
			return ResponseEntity.ok(likedFeed);
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

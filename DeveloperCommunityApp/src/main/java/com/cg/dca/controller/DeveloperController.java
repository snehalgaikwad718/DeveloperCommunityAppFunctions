package com.cg.dca.controller;

import com.cg.dca.entity.Developer;
import com.cg.dca.entity.Feed;
import com.cg.dca.entity.Response;
import com.cg.dca.exception.UnknownDeveloperException;
import com.cg.dca.service.DeveloperService;
import com.cg.dca.service.IFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.dca.repository.IDeveloperRepository;
import com.cg.dca.repository.IResponseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class DeveloperController {
	
	@Autowired
	IDeveloperRepository developerRepository;
	
	@Autowired
	IResponseRepository responseRepository;
	
	@Autowired
	DeveloperService developerService;
	
	@Autowired
	IFeedService feedService;

    @PostMapping("/addDeveloper")
    public ResponseEntity<String> createDeveloper(@RequestBody List<Developer> developerData) {
    	developerRepository.saveAll(developerData);
		return ResponseEntity.ok("Data saved");
	}

    @GetMapping("/getAllDeveloper")
    public List<Developer> getAllDeveloper(){
		return developerRepository.findAll();
	}

    // Get Developer By Id
 	@GetMapping("/getDeveloper/{id}")
 	public ResponseEntity<Developer> fetchDeveloperById(@PathVariable(value = "id") Long devId) throws UnknownDeveloperException {
 		Developer developer = developerRepository.findById(devId)
 				.orElseThrow(() -> new UnknownDeveloperException("Developer not found for this id :: " + devId));
 		return ResponseEntity.ok().body(developer);
 	}
 	
 	// Get Feeds By Developer Id
 	@GetMapping("/getDeveloper/{id}/feeds")
	public List<Feed> fetchFeedsByDevId(@PathVariable(value = "id") Long devId) throws UnknownDeveloperException {
		return developerService.fetchFeedsByDevId(devId);
	}
 	
 // Get Responses By Feed Id
  	@GetMapping("/getDeveloper/{id}/responses")
 	public List<Response> fetchResponseByDevId(@PathVariable(value = "id") Long devId) throws UnknownDeveloperException {
 		return developerService.fetchResponseByDevId(devId);
 	}

 // Update Developer
 	@PutMapping("/updateDeveloper/{id}")
 	public ResponseEntity<Developer> updateDeveloper(@PathVariable(value = "id") Long devId,
 	@RequestBody Developer developerDetails) throws UnknownDeveloperException{
 		
 		Developer developer = developerRepository.findById(devId)
 				.orElseThrow(() -> new UnknownDeveloperException("Developer not found for this id :: " + devId));
 		
 		developer.setName(developerDetails.getName());
 		developer.setEmail(developerDetails.getEmail());
 		developer.setSkillLevel(developerDetails.getSkillLevel());
 		developer.setMemberSince(developerDetails.getMemberSince());
 		developer.setFeeds(developerDetails.getFeeds());
 		developer.setResponses(developerDetails.getResponses());
 		final Developer updatedDeveloper  = developerRepository.save(developer);
 		return ResponseEntity.ok(updatedDeveloper);
 	}
 	
 // Delete Developer
 	@DeleteMapping("/deleteDeveloper/{id}")
 	public Map<String, Boolean> deleteDeveloper(@PathVariable(value = "id") Long devId) throws UnknownDeveloperException{
 		Developer developer = developerRepository.findById(devId)
 				.orElseThrow(() -> new UnknownDeveloperException("Developer not found for this id :: " + devId));
 		
 		developerRepository.delete(developer);
 		
 		Map<String, Boolean> errorDeveloper = new HashMap<>();
 		errorDeveloper.put("deleted", Boolean.TRUE);
 		
 		return errorDeveloper;
 	}

}


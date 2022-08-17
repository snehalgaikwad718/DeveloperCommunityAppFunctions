package com.cg.dca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dca.entity.Feed;
import com.cg.dca.entity.Response;
import com.cg.dca.exception.UnknownResponseException;
import com.cg.dca.repository.IResponseRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class ResponseController {

	@Autowired
	private IResponseRepository responseRepository;
	
	Feed feed;
	
	Principal principal;
	
	// Get Response
	@GetMapping("/responses")
	public List<Response> getAllDeveloper(){
		
		return responseRepository.findAll();
	}
	
	// Save Response
	@PostMapping("/responses")
	public Response createResponse(@RequestBody Response response) {
		return responseRepository.save(response);
	}
	
	// Update Response
	@PutMapping("/responses/{id}")
	public ResponseEntity<Response> updateResponse(@PathVariable(value = "id") Long respId,
	@RequestBody Response responseDetails) throws UnknownResponseException{
		
		Response response = responseRepository.findById(respId)
				.orElseThrow(() -> new UnknownResponseException("Response not found for this id :: " + respId));
		
		response.setAnswer(responseDetails.getAnswer());
		response.setRespDate(responseDetails.getRespDate());
		response.setRespTime(responseDetails.getRespTime());
		response.setAccuracy(responseDetails.getAccuracy());
		final Response updatedResponse  = responseRepository.save(response);
		return ResponseEntity.ok(updatedResponse);
	}
	
	// Like Response
	@GetMapping("responses/{id}/like")
	public ResponseEntity<Response> likeResponse(@PathVariable(value = "id") Long respId) throws UnknownResponseException{
		Response response = responseRepository.findById(respId)
				.orElseThrow(() -> new UnknownResponseException("Response not found for this id :: " + respId));
		
		int count = 0;
		count = response.getAccuracy() + 1;
		response.setAnswer(response.getAnswer());
		response.setRespDate(response.getRespDate());
		response.setRespTime(response.getRespTime());
		response.setAccuracy(count);
		final Response likedResponse = responseRepository.save(response);
		return ResponseEntity.ok(likedResponse);
	}
	
	@RequestMapping(value="/username", method = RequestMethod.GET)
	@ResponseBody
	public String currentUserName(Principal principal) {
		return principal.getName();
	}
	
	// Delete Response
	@DeleteMapping("/responses/{id}")
	public Map<String, Boolean> deleteResponse(@PathVariable(value = "id") Long respId) throws UnknownResponseException{
		Response response = responseRepository.findById(respId)
				.orElseThrow(() -> new UnknownResponseException("Response not found for this id :: " + respId));
		
		responseRepository.delete(response);
		
		Map<String, Boolean> errorResponse = new HashMap<>();
		errorResponse.put("deleted", Boolean.TRUE);
		
		return errorResponse;
	}
}

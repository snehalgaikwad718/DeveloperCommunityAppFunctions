package com.cg.dca.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dca.entity.Response;
import com.cg.dca.exception.UnknownResponseException;
import com.cg.dca.repository.IResponseRepository;
import com.cg.dca.service.IResponseService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class ResponseController {

	@Autowired
	private IResponseRepository responseRepository;
	private IResponseService responseService;
	
	// Get Response
	@GetMapping("/responses")
	public List<Response> getAllResponse(){
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
//	@GetMapping("/responses/{id}/like")
	@PutMapping("/responses/{id}/like")
	public ResponseEntity<Response> likeResponse(@PathVariable(value = "id") Long respId,
	@RequestBody Response responseDetails) throws UnknownResponseException{
		
		Response response = responseRepository.findById(respId)
				.orElseThrow(() -> new UnknownResponseException("Response not found for this id :: " + respId));
		
		response.setAccuracy(responseDetails.getAccuracy()+responseService.likeResponse(respId));
		final Response updatedResponse  = responseRepository.save(response);
		return ResponseEntity.ok(updatedResponse);
	}
//	public ResponseEntity<?> likeResponse(Long respId){
//		return new ResponseEntity<>(responseService.likeResponse(respId), HttpStatus.OK);
//	}
//	@PutMapping("/responses/{id}/like")
//	public ResponseEntity<Response> likeResponse(@PathVariable(value = "id") Long respId,
//			@ReqyestBody Response responseDetails) {
//		
//		Response response = responseRepository.findById(respId);
//		
//		response.setAccuracy(responseDetails.getAccuracy());
//		final Response likedResponse  = responseRepository.save(response);
//		return ResponseEntity.ok(likedResponse);
//		
//		
//		Map<String, Integer> errorResponse = new HashMap<>();
//		errorResponse.put("Response Liked", count+1);
//		
//		return errorResponse;
	
	
	
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

package com.cg.dca.service;

import com.cg.dca.entity.Response;

public interface IResponseService {

	public default int likeResponse(Long id) {
		// TODO Auto-generated method stub
		Response response = new Response();
		return response.getAccuracy()+1;
	}
//
////Response saveResponse(Response resp);
////	
////	Response updateResponse(Response resp);
////	
////	Response deleteResponse(int respId) throws UnknownResponseException;
////	
////	Response likeResponse(int respId);
////	
////	
////	List<Response> fetchResponseByFeed(int feedId) throws UnknownFeedException;
////	
////	List<Response> fetchResponseByDeveloper(int devId) throws UnknownDeveloperException;
//	
}

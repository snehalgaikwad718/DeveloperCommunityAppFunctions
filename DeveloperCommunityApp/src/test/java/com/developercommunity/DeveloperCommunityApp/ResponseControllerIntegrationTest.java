package com.developercommunity.DeveloperCommunityApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.cg.dca.DeveloperCommunityAppApplication;
import com.cg.dca.entity.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeveloperCommunityAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResponseControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllResponse() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/responses",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testCreateResponse() {
		Response response = new Response();
		response.setAnswer("This is test response");
		response.setRespDate(LocalDate.now());
		response.setRespTime(LocalTime.now());
		response.setAccuracy(2);

		ResponseEntity<Response> postResponse = restTemplate.postForEntity(getRootUrl() + "/responses", response, Response.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateResponse() {
		int id = 1;
		Response response = restTemplate.getForObject(getRootUrl() + "/responses/" + id, Response.class);
		response.setAnswer("This is update test response");
		response.setAccuracy(4);

		restTemplate.put(getRootUrl() + "/responses/" + id, response);

		Response updatedResponse = restTemplate.getForObject(getRootUrl() + "/responses/" + id, Response.class);
		assertNotNull(updatedResponse);
	}

	@Test
	public void testDeleteResponse() {
		int id = 2;
		Response response = restTemplate.getForObject(getRootUrl() + "/responses/" + id, Response.class);
		assertNotNull(response);

		restTemplate.delete(getRootUrl() + "/responses/" + id);

		try {
			response = restTemplate.getForObject(getRootUrl() + "/responses/" + id, Response.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}

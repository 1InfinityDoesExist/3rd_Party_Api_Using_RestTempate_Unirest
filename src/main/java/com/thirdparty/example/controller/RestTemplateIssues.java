package com.thirdparty.example.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(path = "/api/object/rest")
public class RestTemplateIssues {

	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<?> saveEmployeeData() {
		String url = "http://dummy.restapiexample.com/api/v1/create";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, String> request = new HashMap<String, String>();
		request.put("name", "Avinash Patel");
		request.put("salary", "600000");
		request.put("age", "24");

		HttpEntity<String> httpEntity = new HttpEntity<String>(new Gson().toJson(request), headers);
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		JsonObject jsonObject = new Gson().fromJson(response.getBody(), JsonObject.class);
		if (!jsonObject.get("errorMessage").isJsonNull()) {
			return new ResponseEntity<String>("Something went Wrong!!!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Successfully Persisited", HttpStatus.CREATED);

	}

}

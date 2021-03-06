package com.thirdparty.example.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
@RequestMapping(path = "/api/object/rest")
public class RestTemplateIssues {
	private static final Logger logger = LoggerFactory.getLogger(RestTemplateIssues.class);

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> saveEmployeeData() throws ParseException, JsonMappingException, JsonProcessingException {
		String url = "http://dummy.restapiexample.com/api/v1/create";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, String> request = new HashMap<String, String>();
		request.put("name", "Avinash Rocks");
		request.put("salary", "600000");
		request.put("age", "24");

		HttpEntity<String> httpEntity = new HttpEntity<String>(new Gson().toJson(request), headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);
		// One Way Of Parsing
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(response.getBody());
		logger.info("jsonObject:- " + jsonObject);

		// Another Way Of Parsing
		JSONObject jObject = new ObjectMapper().readValue(response.getBody(), JSONObject.class);
		logger.info("objectMapper:-" + jObject);

		for (Object object : jsonObject.keySet()) {
			String propName = (String) object;
			logger.info("---------->" + propName);
			switch (propName) {
			case "id":
				Object obj1 = jsonObject.get(propName);
				String value1 = (String) obj1;
				logger.info("Employee Id: --" + value1);
				continue;
			case "employee_name":
				Object obj2 = jsonObject.get(propName);
				String str1 = (String) obj2;
				logger.info("Employee Name:- " + str1);
				continue;
			case "employee_salary":
				Object obj3 = jsonObject.get(propName);
				String str2 = (String) obj3;
				logger.info("Employee Salary:- " + str2);
				continue;
			case "employee_age":
				Object obj4 = jsonObject.get(propName);
				String str3 = (String) obj4;
				logger.info("Employee Age:- " + str3);
				continue;
			case "profile_image":
				Object obj5 = jsonObject.get(propName);
				String str4 = (String) obj5;
				logger.info("Profile Image- " + str4);
				continue;

			default:
				logger.info("****************End Of Switch Case***************************");
			}
		}

		return new ResponseEntity<String>("SuccessFully Retrieved Data For Employee", HttpStatus.OK);
	}

	@RequestMapping(path = "/get/rest", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getEmployeeDetails(@RequestParam(value = "id", required = true) String id)
			throws ParseException, JsonMappingException, JsonProcessingException {

		final String url = "http://dummy.restapiexample.com/api/v1/employee/";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url + id, String.class);

		// One Way Of Parsing
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(response.getBody());

		// Another Way Of Parsing
		JSONObject jObject = new ObjectMapper().readValue(response.getBody(), JSONObject.class);
		logger.info("objectMapper:-" + jObject);

		for (Object object : jsonObject.keySet()) {
			String propName = (String) object;
			logger.info("---------->" + propName);
			switch (propName) {
			case "id":
				Object obj1 = jsonObject.get(propName);
				String value1 = (String) obj1;
				logger.info("Employee Id: --" + value1);
				continue;
			case "employee_name":
				Object obj2 = jsonObject.get(propName);
				String str1 = (String) obj2;
				logger.info("Employee Name:- " + str1);
				continue;
			case "employee_salary":
				Object obj3 = jsonObject.get(propName);
				String str2 = (String) obj3;
				logger.info("Employee Salary:- " + str2);
				continue;
			case "employee_age":
				Object obj4 = jsonObject.get(propName);
				String str3 = (String) obj4;
				logger.info("Employee Age:- " + str3);
				continue;
			case "profile_image":
				Object obj5 = jsonObject.get(propName);
				String str4 = (String) obj5;
				logger.info("Profile Image- " + str4);
				continue;

			default:
				logger.info("****************End Of Switch Case***************************");
			}
		}

		return new ResponseEntity<String>("SuccessFully Retrieved Data For Employee", HttpStatus.OK);
	}

	@RequestMapping(path = "/get", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> getAllEmployee() throws ParseException {
		final String url = "http://dummy.restapiexample.com/api/v1/employees";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(response.getBody());

		logger.info("JsonArray Size :- " + jsonArray.size());
		for (int iter = 0; iter < jsonArray.size(); iter++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(iter);
			logger.info("JsonObject :- " + jsonObject);
			for (Object object : jsonObject.keySet()) {
				String propName = (String) object;
				if (propName.equals("employee_name")) {
					logger.info("Name:---->" + jsonObject.get(propName));
					break;
				}
			}
		}
		return new ResponseEntity<String>("SuccessFully Persisted", HttpStatus.OK);
	}

	// delete method returns void ....however in Returns some response...!!!
	@RequestMapping(path = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@RequestParam(value = "id", required = true) String id)
			throws ParseException {
		String url = "http://dummy.restapiexample.com/api/v1/delete/{id}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url, params);
		return new ResponseEntity<String>("SuccessFully Deteted", HttpStatus.OK);
	}

	@RequestMapping(path = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@RequestParam(value = "id", required = true) String id) {

		final String url = "http://dummy.restapiexample.com/api/v1/update/{id}";

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		Map<String, String> request = new HashMap<String, String>();
		request.put("name", "Avinash Rocks");
		request.put("salary", "600000");
		request.put("age", "24");

		HttpEntity<String> entity = new HttpEntity<String>(new Gson().toJson(request), headers);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url, entity, params);
		

		return new ResponseEntity<String>("Successfully Updated", HttpStatus.OK);

	}

	// Friend API

}

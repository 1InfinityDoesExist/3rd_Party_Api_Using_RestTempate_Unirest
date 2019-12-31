package com.thirdparty.example.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
@RequestMapping(path = "/api/object/template")
public class RestTemplateController {

	private static final Logger logger = LoggerFactory.getLogger(RestTemplateController.class);

	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = "appllication/json")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> restTemplate1() throws UnirestException {

		String url = "http://dummy.restapiexample.com/api/v1/employees";
		HttpResponse<JsonNode> jsonNode = Unirest.get(url).header("accept", "application/json")
				.header("content-type", "application/json").asJson();

		JSONArray jsonArray = jsonNode.getBody().getArray();
		for (int iter = 0; iter < jsonArray.length(); iter++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(iter);
			for (Object object : jsonObject.keySet()) {
				String propName = (String) object;
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
		}

		return new ResponseEntity<String>("ResponseEntity", HttpStatus.OK);
	}

	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getEmployeeDetialsById(@RequestParam(value = "id", required = true) String id)
			throws UnirestException {

		String url = "http://dummy.restapiexample.com/api/v1/employee/";

		HttpResponse<JsonNode> jsonNode = Unirest.get(url + id).header("accept", "application/json")
				.header("content-type", "application/json").asJson();

		JSONObject jsonObject = jsonNode.getBody().getObject();

		for (Object object : jsonObject.keySet()) {
			String propName = (String) object;
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

		return new ResponseEntity<String>("SuccessFully Excecuted Unirest", HttpStatus.OK);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployeeDetials(@RequestParam(value = "id", required = true) String id)
			throws UnirestException {
		String url = "http://dummy.restapiexample.com/api/v1/delete/";
		HttpResponse<JsonNode> jsonNode = Unirest.delete(url + id).header("content-type", "application/json")
				.header("accept", "application/json").asJson();

		JSONObject jsonObject = jsonNode.getBody().getObject();
		for (Object object : jsonObject.keySet()) {
			String propName = (String) object;
			if (propName.equals("success")) {
				JSONObject successObject = jsonObject.getJSONObject(propName);
				for (Object sObject : successObject.keySet()) {
					String textObject = (String) sObject;
					if (textObject.equals("text")) {
						String response = successObject.getString(textObject);
						logger.info("Success Info:- " + response);
					}
				}
			}
		}

		return new ResponseEntity<String>("SuccessFully Deleted The Employee Detials", HttpStatus.OK);
	}

	// One Way
	@RequestMapping(path = "/create1", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> createEmployeeDetails() throws UnirestException {

		Map<String, Object> payloadObject = new HashMap<String, Object>();
		payloadObject.put("name", "Avinash Patel");
		payloadObject.put("salary", "600000");
		payloadObject.put("age", "24");

		String url = "http://dummy.restapiexample.com/api/v1/create";
		HttpResponse<JsonNode> jsonNode = Unirest.post(url).header("accept", "application/json")
				.header("content-type", "application/json").fields(payloadObject).asJson();
		JSONObject jsonObject = jsonNode.getBody().getObject();
		for (Object object : jsonObject.keySet()) {
			String propName = (String) object;
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

		return new ResponseEntity<String>("Successfully Created", HttpStatus.OK);
	}

	// The Other way
	@RequestMapping(path = "/create2", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> createEmployeeResource() throws UnirestException {

		JSONObject payloadObject = new JSONObject();
		payloadObject.put("name", "Avinash Patel");
		payloadObject.put("salary", "600000");
		payloadObject.put("age", "24");

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("content-type", "application/json");
		headers.put("accept", "application/json");

		String url = "http://dummy.restapiexample.com/api/v1/create";
		HttpResponse<JsonNode> jsonNode = Unirest.post(url).headers(headers).body(payloadObject).asJson();
		JSONObject jsonObject = jsonNode.getBody().getObject();
		for (Object object : jsonObject.keySet()) {
			String propName = (String) object;
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

		return new ResponseEntity<String>("Successfully Created", HttpStatus.OK);
	}
	
	
	

}

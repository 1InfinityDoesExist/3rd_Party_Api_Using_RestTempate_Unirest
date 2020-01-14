package com.thirdparty.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path = "/api/object/template")
@Api(value = "Unirest Contoller", description = "Using Unirest To Save Data And Perform Other Operation")
public class RestTemplateController {

	private static final Logger logger = LoggerFactory.getLogger(RestTemplateController.class);

	@RequestMapping(path = "/get1", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK, reason = "SuccessFully Retrieved Data From Unirest (3rd Party Api)")
	@ApiOperation(value = "Retrieving Data From 3rd Party api", notes = "I am using unirest to cal third party apis")
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

	@RequestMapping(path = "/get2", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK, reason = "Successfully Retrieved Data from The Database")
	@ApiOperation(value = "Get Employee Resource By ID", notes = "For this one as well I am using 3rd Party Api")
	public ResponseEntity<?> getEmployeeDetialsById(
			@ApiParam(value = "id", required = true, example = "123") @RequestParam(value = "id", required = true) String id)
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
	@ApiOperation(value = "Delete Employe By ID", notes = "Using 3rd Party Api to Delete Employee Data")
	public ResponseEntity<?> deleteEmployeeDetials(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) String id)
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
	@ApiOperation(value = "Create Employee Resource", notes = "Using Unirest to post Emoloyee Details")
	public ResponseEntity<?> createEmployeeDetails() throws UnirestException {

		Map<String, Object> payloadObject = new HashMap<String, Object>();
		payloadObject.put("name", "Avinash Patel");
		payloadObject.put("salary", "600000");
		payloadObject.put("age", "24");

		final String url = "http://dummy.restapiexample.com/api/v1/create";
		logger.info("i am here");
		HttpResponse<JsonNode> jsonNode = Unirest.post(url).header("accept", "application/json")
				.header("content-type", "application/json").body(new Gson().toJson(payloadObject)).asJson();
		logger.info("i am here too");
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
	@ApiOperation(value = "Create Employee by Using Unirest", notes = "Using 3rd Party Api To create Resource")
	public ResponseEntity<?> createEmployeeResource() throws UnirestException {

		JSONObject payloadObject = new JSONObject();
		payloadObject.put("name", "Avinash Patel");
		payloadObject.put("salary", "600000");
		payloadObject.put("age", "24");

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("content-type", "application/json");

		final String url = "http://dummy.restapiexample.com/api/v1/create";
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

	// get operation
	@RequestMapping(path = "/get/typicode", method = RequestMethod.GET)
	@ApiOperation(value = "Get Employee Details By Id", notes = "Using Unirest To get Data From 3rd Party Api")
	public ResponseEntity<?> getEmployeeDetials(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id") Long id) throws UnirestException {
		final String url = "https://jsonplaceholder.typicode.com/posts/{id}";

		HttpResponse<JsonNode> jsonNode = Unirest.get(url).header("Content-Type", "application/json")
				.header("Accept", "application/json").routeParam("id", id.toString()).asJson();

		JSONObject jsonObject = jsonNode.getBody().getObject();
		for (Object object : jsonObject.keySet()) {
			String propName = (String) object;

			switch (propName) {
			case "userId":
				Object obj1 = (Object) jsonObject.get(propName);
				Integer userIdValue = (Integer) obj1;
				logger.info("userId value is:-" + userIdValue);
				continue;

			case "id":
				Object obj2 = (Object) jsonObject.get(propName);
				Integer idValue = (Integer) obj2;
				logger.info("id value is :-" + idValue);
				continue;

			case "title":
				Object obj3 = (Object) jsonObject.get(propName);
				String titleValue = (String) obj3;
				logger.info("Title Value is:- " + titleValue);
				continue;

			case "body":
				Object obj4 = (Object) jsonObject.get(propName);
				String bodyValue = (String) obj4;
				logger.info("Body Value is : " + bodyValue);
				continue;

			default:
				logger.info("***************End Of Switch Case************************");

			}
		}

		return new ResponseEntity<String>("Successfully Retrieved Data From DB", HttpStatus.OK);

	}

	// OnlineAPi
	@RequestMapping(path = "/get/free", method = RequestMethod.GET)
	@ApiOperation(value = "Unirest To Get Resourc By Page ", notes = "Using 3rd party Apito get Resource")
	public ResponseEntity<?> getFreeApi(
			@ApiParam(value = "page", required = true) @RequestParam(value = "page") Long page)
			throws UnirestException {
		final String url = "https://reqres.in/api/users?page={pageNumber}";
		HttpResponse<JsonNode> jsonNode = Unirest.get(url).header("Accept", "application/json")
				.header("Content-Type", "application/json").routeParam("pageNumber", page.toString()).asJson();

		JSONObject jsonObject = jsonNode.getBody().getObject();
		for (Object object : jsonObject.keySet()) {
			String propName = (String) object;

			switch (propName) {
			case "page":
				Object object1 = jsonObject.get(propName);
				Integer pageValue = (Integer) object1;
				logger.info("Page :- " + pageValue);
				continue;
			case "per_page":
				Object object2 = jsonObject.get(propName);
				Integer perPageValue = (Integer) object2;
				logger.info("PerPageValue :-" + perPageValue);
				continue;

			case "total":
				Object object3 = jsonObject.get(propName);
				Integer totalValue = (Integer) object3;
				logger.info("Total :- " + totalValue);
				continue;
			case "total_pages":
				Object object4 = jsonObject.get(propName);
				Integer totalPagesValue = (Integer) object4;
				logger.info("TotalPages :-" + totalPagesValue);
				continue;
			case "data":
				JSONArray jsonArray = jsonObject.getJSONArray(propName);
				for (int iter = 0; iter < jsonArray.length(); iter++) {
					JSONObject jsonArrayObject = (JSONObject) jsonArray.get(iter);
					for (Object obj : jsonArrayObject.keySet()) {
						String prop = (String) obj;
						switch (prop) {
						case "id":
							Object obj1 = jsonArrayObject.get(prop);
							Integer obj1Value = (Integer) obj1;
							logger.info("Data ID: " + obj1Value);
							continue;

						case "email":
							Object obj2 = jsonArrayObject.get(prop);
							String obj2Value = (String) obj2;
							logger.info("Email :- " + obj2Value);
							continue;

						case "first_name":
							Object obj3 = jsonArrayObject.get(prop);
							String obj3Value = (String) obj3;
							logger.info("FirstName:- " + obj3Value);
							continue;

						case "last_name":
							Object obj4 = jsonArrayObject.get(prop);
							String obj4Value = (String) obj4;
							logger.info("last_name:-" + obj4Value);
							continue;

						case "avatar":
							Object obj5 = jsonArrayObject.get(prop);
							String obj5Value = (String) obj5;
							logger.info("AvatarValue:-" + obj5Value);
							continue;
						}
					}
				}
			default:
				logger.info("************End of Switch Case:*************");
			}
		}
		return new ResponseEntity<String>("Successfully Done", HttpStatus.OK);

	}

}

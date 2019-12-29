package com.thirdparty.example.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
@RequestMapping(path = "/api/get")
public class ControllerClass {

	private static final Logger logger = LoggerFactory.getLogger(ControllerClass.class);

	@RequestMapping(path = "/confDetails/{code}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> thirdPartyApi1(@PathVariable(value = "code", required = true) Long code)
			throws UnirestException {
		HttpResponse<JsonNode> jsonResponse = Unirest.get("https://restcountries.eu/rest/v2/callingcode/" + code)
				.header("accept", "application/json").header("content-type", "application/json").asJson();
		JSONArray response = jsonResponse.getBody().getArray();
		for (int iter = 0; iter < response.length(); iter++) {
			JSONObject jsonObject = (JSONObject) response.get(iter);

			for (Object obj : jsonObject.keySet()) {
				String str = (String) obj;
				if (str.equals("name")) {
					logger.info("Name value" + jsonObject.get(str));
					break;
				}

			}
		}
		return new ResponseEntity<String>("Successfully Compiled Unirest ", HttpStatus.OK);

	}

	@RequestMapping(path = "/confDetails", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> thirdPartyApi2() throws UnirestException {
		logger.info("********Inside Using Path Params************************");
		HttpResponse<JsonNode> jsonResponse = Unirest.get("https://restcountries.eu/rest/v2/callingcode/{code}")
				.header("accept", "application/json").header("content-type", "application/json")
				.routeParam("code", "372").asJson();
		
		return new ResponseEntity<String>("Successfully Compiled Unirest ", HttpStatus.OK);

	}

	@RequestMapping(path = "/confDetail", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> thirdPartyApi3(@RequestParam(value = "code", required = true) Long code)
			throws UnirestException {
		logger.info("*****************************Using Request Pram************************");
		HttpResponse<JsonNode> jsonResponse = Unirest.get("https://restcountries.eu/rest/v2/callingcode")
				.header("accept", "application/json").header("content-type", "application/json").asJson();
		String body = jsonResponse.getBody().toString();
		return new ResponseEntity<String>("Successfully Compiled Unirest ", HttpStatus.OK);

	}

}

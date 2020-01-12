package com.thirdparty.example.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RestController
@RequestMapping(path = "/api/object/unirest")
public class UnirestController {

	final static private Logger logger = LoggerFactory.getLogger(UnirestController.class);

	@RequestMapping(path = "/first", method = RequestMethod.GET)
	public ResponseEntity<?> unirestIssue() throws UnirestException {
		logger.info("*****************Inside Get All Method*****************");
		final String url = "https://swapi.co/api/people/";

		HttpResponse<JsonNode> jsonNode = Unirest.get(url).header("Accept", "application/json")
				.header("Content-Type", "application/json").asJson();
		JSONObject jsonObject = jsonNode.getBody().getObject();

		for (Object object : jsonObject.keySet()) {
			String propName = (String) object;

			switch (propName) {
			case "count":
				Integer propValue = (Integer) jsonObject.get(propName);
				logger.info("Count Value:-" + propValue);
				continue;

			case "next":
				// String nextProp = String.valueOf(jsonObject.get(propName));
				String nextProp = (String) jsonObject.get(propName);
				logger.info("Next Value:-" + nextProp);
				continue;

			case "results":
				JSONArray jsonArray = (JSONArray) jsonObject.get(propName);
				for (int iter = 0; iter < jsonArray.length(); iter++) {
					JSONObject resultObject = (JSONObject) jsonArray.get(iter);
					for (Object obj : resultObject.keySet()) {
						String resultPropName = (String) obj;
						switch (resultPropName) {
						case "homeworld":
							String homeValue = (String) resultObject.get(resultPropName);
							logger.info("HomeWorld:- " + homeValue);
							continue;

						case "films":
							JSONArray filmJsonArray = (JSONArray) resultObject.get(resultPropName);
							for (int jter = 0; jter < filmJsonArray.length(); jter++) {
								Object filmObject = filmJsonArray.get(jter);
								String filmValue = (String) filmObject;
								logger.info("FilmValue :- " + filmValue);
							}
							continue;

						case "edited":
							Object editObject = resultObject.get(resultPropName);
							String editValue = (String) editObject;
							logger.info("EditValue:-" + editValue);
							continue;
						case "vehicles":
							JSONArray vehicleArray = (JSONArray) resultObject.get(resultPropName);
							for (int jter = 0; jter < vehicleArray.length(); jter++) {
								Object vehicleObject = vehicleArray.get(jter);
								String vehicleValue = (String) vehicleObject;
								logger.info("Vehicle Value:-" + vehicleValue);
								break;
							}

						default:
							logger.info("***************End of Inner Switch Case***************");
						}
					}
				}

			default:
				logger.info("***************************End Of Switch Case**************************");
			}

		}
		return new ResponseEntity<String>("Successfully Executed", HttpStatus.OK);

	}

	@RequestMapping(path = "/second", method = RequestMethod.GET)
	public ResponseEntity<?> getByID(@RequestParam(value = "id", required = true) Long id) throws UnirestException {
		logger.info("*********************Inside GetByID Method**************************");
		final String url = "https://swapi.co/api/people/{id}";
		HttpResponse<JsonNode> jsonNode = Unirest.get(url).header("Accept", "application/json")
				.header("Content-Type", "application/json").routeParam("id", id.toString()).asJson();

		JSONObject jsonObject = jsonNode.getBody().getObject();
		for (Object object : jsonObject.keySet()) {
			String propName = (String) object;

			switch (propName) {
			case "homeworld":
				String homeWorldValue = jsonObject.getString(propName);
				logger.info("HomeValue :- " + homeWorldValue);
				continue;

			case "url":
				Object urlObject = jsonObject.get(propName);
				String urlString = (String) urlObject;
				logger.info("Url Value:-" + urlString);
				continue;

			case "films":
				JSONArray jsonArray = (JSONArray) jsonObject.get(propName);
				for (int iter = 0; iter < jsonArray.length(); iter++) {
					Object filmObject = jsonArray.get(iter);
					String filmValue = (String) filmObject;
					logger.info("FilmValue" + filmValue);
				}
				continue;

			case "species":
				JSONArray jsonArray1 = (JSONArray) jsonObject.get(propName);
				for (int iter = 0; iter < jsonArray1.length(); iter++) {
					Object object1 = jsonArray1.get(iter);
					String obj1Value = (String) object1;
					logger.info("Species:" + obj1Value);
				}
				continue;

			default:
				logger.info("***************End Of Switch Case**************");
				break;
			}

		}
		return new ResponseEntity<String>("Successfully Executed", HttpStatus.OK);
	}
}

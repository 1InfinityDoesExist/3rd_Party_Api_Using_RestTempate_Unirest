package com.thirdparty.example.controller;

import java.util.List;

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

		JSONArray jsonArray = jsonResponse.getBody().getArray();
		for (int iter = 0; iter < jsonArray.length(); iter++) {
			JSONObject jsonObject = jsonArray.getJSONObject(iter);
			for (Object obj : jsonObject.keySet()) {
				String str = (String) obj;
				if (str.equals("name")) {
					logger.info("Name value: " + jsonObject.getString(str));
					break;
				}
			}
		}
		return new ResponseEntity<String>("Successfully Compiled Unirest ", HttpStatus.OK);
	}

	@RequestMapping(path = "/confDetail", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> thirdPartyApi3(@RequestParam(value = "code", required = true) Long code)
			throws UnirestException {
		logger.info("*****************************Using Request Pram************************");
		String url = "https://restcountries.eu/rest/v2/callingcode/" + code;
		HttpResponse<JsonNode> jsonResponse = Unirest.get(url).header("accept", "application/json")
				.header("content-type", "application/json").asJson();

		JSONArray jsonArray = jsonResponse.getBody().getArray();
		for (int iter = 0; iter < jsonArray.length(); iter++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(iter);
			for (Object obj : jsonObject.keySet()) {
				String str = (String) obj;

				switch (str) {

				case "capital":
					String ssk = (String) jsonObject.get(str);
					System.out.println(ssk);
					continue;

				case "name":
					logger.info("Name value: " + jsonObject.getString(str));
					continue;

				case "alpha2Code":
					logger.info("alpha2Code" + jsonObject.getString(str));
					continue;

				case "alpha3Code":
					logger.info(jsonObject.getString(str));
					continue;

				case "callingCodes":
					JSONArray jsonArr = jsonObject.getJSONArray(str);
					for (Object s : jsonArr) {
						String ss = (String) s;
						System.out.println(ss);
					}
					continue;

				case "altSpellings":

					JSONArray altJsonArray = jsonObject.getJSONArray(str);
					for (int jter = 0; jter < altJsonArray.length(); jter++) {
						String value = altJsonArray.getString(jter);
						System.out.println(value);
					}
					continue;

				case "latlng":
					JSONArray latlngArray = jsonObject.getJSONArray(str);
					for (int jter = 0; jter < latlngArray.length(); jter++) {
						long value = latlngArray.getInt(jter);
						System.out.println(value);
					}
					continue;

				case "topLevelDomain":
					JSONArray topArray = jsonObject.getJSONArray("topLevelDomain");
					for (int jter = 0; jter < topArray.length(); jter++) {
						String value = topArray.getString(iter);
						System.out.println(value);
					}
					continue;

				case "region":
					Object o1 = jsonObject.get(str);
					String s1 = (String) o1;
					System.out.println(s1);
					continue;

				case "subregion":
					String s2 = jsonObject.getString(str);
					System.out.println(s2);
					continue;

				case "languages":
					JSONArray languageArray = jsonObject.getJSONArray(str);
					for (int jter = 0; jter < languageArray.length(); jter++) {
						JSONObject langObject = (JSONObject) languageArray.get(jter);
						for (Object oo1 : langObject.keySet()) {
							String str2 = (String) oo1;
							System.out.println(str2 + "-------->  " + langObject.getString(str2));
						}
					}
					continue;

				case "population":
					long l1 = jsonObject.getLong(str);
					System.out.println(l1);
					continue;

				case "demonym":
					String s3 = jsonObject.getString(str);
					System.out.println(s3);
					continue;

				case "translations":

					JSONObject transObj = (JSONObject) jsonObject.get(str);
					for (Object trObject : transObj.keySet()) {
						String propTrans = (String) trObject;
						System.out.println(propTrans + "------>" + transObj.getString(propTrans));
					}

					continue;

				case "area":
					long l2 = jsonObject.getLong(str);
					logger.info("area: -- " + l2);
					continue;

				case "gini":
					long l3 = jsonObject.getLong(str);
					System.out.println(l3);
					continue;

				case "borders":
					JSONArray borderJsonArray = jsonObject.getJSONArray(str);
					for (int jter = 0; jter < borderJsonArray.length(); jter++) {
						System.out.println(borderJsonArray.getString(jter));
					}
					continue;

				case "nativeName":
					Object o2 = jsonObject.get(str);
					String s5 = (String) o2;
					System.out.println(s5);
					continue;

				case "currencies":
					JSONArray currenciesArray = jsonObject.getJSONArray(str);
					for (int jter = 0; jter < currenciesArray.length(); jter++) {
						JSONObject curObject = (JSONObject) currenciesArray.get(jter);
						for (Object curO : curObject.keySet()) {
							String propCurrencies = (String) curO;
							System.out.println(propCurrencies + "--->  " + curObject.getString(propCurrencies));
						}

					}
					continue;

				case "numericCode":
					Object o3 = jsonObject.get(str);
					String s6 = (String) o3;
					System.out.println(s6);
					continue;

				case "regionalBlocs":
					JSONArray regionArray = jsonObject.getJSONArray(str);
					for (int jter = 0; jter < regionArray.length(); jter++) {
						JSONObject object1 = (JSONObject) regionArray.get(jter);
						for (Object object3 : object1.keySet()) {
							Object obj34 = object3;
							String str4 = (String) obj34;
							if (str4.equals("acronym")) {
								System.out.println(str4 + " value is :- " + object1.getString(str4));
							}
							if(str4.equals("name")) {
								System.out.println(str4 + " value is :- " + object1.getString(str4));
							}
							if(str4.equals("otherAcronyms")) {
								JSONArray jArray = object1.getJSONArray(str4);
								for(int pter = 0; pter < jArray.length(); pter++) {
									continue;
								}
								
							}
							if(str4.equals("otherNames")) {
								JSONArray jArray = object1.getJSONArray(str4);
								for(int pter = 0; pter < jArray.length(); pter++) {
									continue;
								}
								
							}
						}
					}
					continue;

				case "cioc":
					Object o7 = jsonObject.get(str);
					String s7 = (String) o7;
					System.out.println(s7);
					continue;

				case "flag":
					Object obj2 = jsonObject.get(str);
					String str23 = (String) obj2;
					System.out.println(str23);
					continue;

				default:
					logger.info("************************End Of Switch Case*********************************");
				}
			}
		}
		return new ResponseEntity<String>("Successfully Compiled Unirest ", HttpStatus.OK);

	}

}

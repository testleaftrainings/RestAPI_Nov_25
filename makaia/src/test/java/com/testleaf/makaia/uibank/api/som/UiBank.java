package com.testleaf.makaia.uibank.api.som;

import static com.testleaf.makaia.general.utils.PropertiesHandler.config;

import java.util.HashMap;
import java.util.Map;

import com.testleaf.makaia.rest.assured.api.client.RestAssuredApiClient;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UiBank {
	
	protected RestAssuredApiClient apiClient = new RestAssuredApiClient();	
	protected RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
	protected Response response;
	protected static Map<String, Object> extract = new HashMap<String, Object>();
	
	protected RequestSpecBuilder globalRequest() {
		return new RequestSpecBuilder()
				   .setBaseUri(config("uibank.backend.base.uri"))	
				   .setBasePath(config("uibank.backend.base.path"))				   
				   .setAccept(ContentType.JSON);
	}

}
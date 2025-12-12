package com.testleaf.makaia.servicenow.api.som;

import static com.testleaf.makaia.general.utils.PropertiesHandler.*;

import com.google.gson.Gson;
import com.testleaf.makaia.rest.assured.api.client.RestAssuredApiClient;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ServiceNow {
	
	protected RestAssuredApiClient apiClient = new RestAssuredApiClient();	
	protected RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
	protected Response response;
	protected Gson gson = new Gson();	
	
	protected RequestSpecBuilder globalRequest() {
		return new RequestSpecBuilder()
				   .setBaseUri(config("service.now.base.uri"))	
				   .setBasePath(config("service.now.base.path"))
				   .setAuth(RestAssured.basic(config("sevice.now.instance.username"), secret("service.now.instance.password")))
				   .setAccept(ContentType.JSON);
	}
	
	protected <T> T deSerializeResponse(String response, Class<T> classType) {
		return gson.fromJson(response, classType);
	}

}
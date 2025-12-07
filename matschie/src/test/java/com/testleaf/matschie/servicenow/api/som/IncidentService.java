package com.testleaf.matschie.servicenow.api.som;

import org.hamcrest.Matchers;

import com.testleaf.matchie.rest.assured.api.client.RestAssuredApiClient;
import com.testleaf.matschie.servicenow.serialization.pojos.CreateRecordPayload;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class IncidentService {

	public static final String TABLENAME = "incident";
	private Response response;
	private RestAssuredApiClient apiClient = new RestAssuredApiClient();

	public void getRecords(RequestSpecBuilder requestSpecBuilder) {
		response = apiClient.get(requestSpecBuilder, TABLENAME);
	}

	public void getRecordsByCategory(RequestSpecBuilder requestSpecBuilder, String categoryName) {
		response = apiClient.get(requestSpecBuilder.addQueryParam("category", categoryName), TABLENAME);
	}

	public void getRecord(RequestSpecBuilder requestSpecBuilder, String sysId) {
		response = apiClient.get(requestSpecBuilder, TABLENAME + "/" + sysId);
	}

	public void getRecord(RequestSpecBuilder requestSpecBuilder) {
		response = apiClient.get(requestSpecBuilder, TABLENAME + "/{sys_id}");
	}

	public void createNewRecord(RequestSpecBuilder requestSpecBuilder, CreateRecordPayload payload) {
		response = apiClient.post(requestSpecBuilder, TABLENAME, payload);
	}

	public void validateResponse(int statusCode, String statusLine, String reponseFormat) {
		response.then().assertThat().statusCode(statusCode);
		response.then().assertThat().statusLine(Matchers.containsString(statusLine));
		if (reponseFormat.equalsIgnoreCase("JSON")) {
			response.then().assertThat().contentType(ContentType.JSON);
		} else if (reponseFormat.equalsIgnoreCase("XML")) {
			response.then().assertThat().contentType(ContentType.XML);
		} else {
			throw new RuntimeException("Servicenow table api response format should be JSON or XML");
		}
	}

}
package com.testleaf.makaia.servicenow.api.som;

import static org.hamcrest.Matchers.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;

import com.testleaf.makaia.servicenow.deserialization.pojos.Result;
import com.testleaf.makaia.servicenow.deserialization.pojos.TableApiJson;
import com.testleaf.makaia.servicenow.deserialization.pojos.TableApiJsonArray;
import com.testleaf.makaia.servicenow.serialization.pojos.CreateIncident;
import com.testleaf.makaia.servicenow.serialization.pojos.UpdateIncident;

import io.restassured.http.ContentType;

public class IncidentSerivce extends ServiceNow {

	private static final String TABLE_NAME = "incident";

	public IncidentSerivce() {
		requestBuilder = globalRequest();
	}
	
	public IncidentSerivce fetchIncidentRecords() {
		response = apiClient.get(requestBuilder, TABLE_NAME);
		return this;
	}
	
	public IncidentSerivce fetchIncidentRecord(String sysId) {
		response = apiClient.get(requestBuilder, TABLE_NAME+"/"+sysId);
		return this;
	}
	
	public IncidentSerivce fetchIncidentRecordByNumber(String number) {
		response = apiClient.get(requestBuilder
				                   .addQueryParam("sysparm_query", "number="+number), 
				                   TABLE_NAME);
		return this;
	}
	
	public IncidentSerivce createIncidentRecord() {
		response = apiClient.post(requestBuilder.setContentType(ContentType.JSON), TABLE_NAME, null);
		return this;
	}
	
	public IncidentSerivce createIncidentRecord(CreateIncident payload) {
		response = apiClient.post(requestBuilder, TABLE_NAME, payload);
		return this;
	}
	
	public IncidentSerivce updateIncidentRecord(UpdateIncident payload, String sysId) {
		response = apiClient.put(requestBuilder.setContentType(ContentType.JSON),  TABLE_NAME+"/"+sysId, payload);
		return this;
	}
	
	public IncidentSerivce fetchOnlyHardwareCategoryIncidentRecords() {		
		response = apiClient.get(requestBuilder.addQueryParam("sysparm_query", "category=hardware"), TABLE_NAME);
		return this;
	}
	
	public IncidentSerivce deleteIncidentRecord(String sysId) {
		response = apiClient.delete(requestBuilder, TABLE_NAME+"/"+sysId);
		return this;
	}
	
	public IncidentSerivce validateSuccessResponse() {
		validateStatusCode("200");
		validateStatusLine("OK");
		validateResponseFormat("JSON");
		return this;
	}
	
	public IncidentSerivce validateCreationResponse() {
		validateStatusCode("201");
		validateStatusLine("Created");
		validateResponseFormat("JSON");
		return this;
	}
	
	public IncidentSerivce validateDeletionResponse() {
		validateStatusCode("204");
		validateStatusLine("No Content");
		return this;
	}
	
	public IncidentSerivce validateNotFoundResponse() {
		validateStatusCode("404");
		validateStatusLine("Not Found");
		validateResponseFormat("JSON");
		return this;
	}
	
	public IncidentSerivce validateCategories(String expected) {
		TableApiJsonArray deSerializeResponse = deSerializeResponse(response.prettyPrint(), TableApiJsonArray.class);
		List<Result> results = deSerializeResponse.getResultJsonArray();
		for (Result result : results) {
			assertThat(result.getCategory(), equalToIgnoringCase(expected));
		}
		return this;
	}
	
	public String extractIncidentNumber() {
		String number = deSerializeResponse(response.prettyPrint(), TableApiJson.class).getResult().getNumber();
		assertThat(number, not(emptyOrNullString()));
		return number;
	}
	
	public IncidentSerivce validateSysId(String expected) {
		String sys_id = deSerializeResponse(response.prettyPrint(), TableApiJson.class).getResult().getSysId();
		assertThat(sys_id, not(emptyOrNullString()));
		assertThat(sys_id, equalTo(expected));
		return this;
	}
	
	public IncidentSerivce validateIncidentNumber(String expected) {
		assertThat(deSerializeResponse(response.prettyPrint(), TableApiJsonArray.class).getResultJsonArray().getFirst().getNumber(), not(emptyOrNullString()));
		assertThat(deSerializeResponse(response.prettyPrint(), TableApiJsonArray.class).getResultJsonArray().getFirst().getNumber(), equalTo(expected));
		return this;
	}
	
	private void validateStatusCode(String statusCode) {
		response.then().assertThat().statusCode(Integer.parseInt(statusCode));
	}
	
	private void validateStatusLine(String statusLine) {
		response.then().assertThat().statusLine(containsString(statusLine));
	}
	
	private void validateResponseFormat(String responseFormat) {
		if (responseFormat.equalsIgnoreCase("JSON")) {
			response.then().assertThat().contentType(ContentType.JSON);
		} else if (responseFormat.equalsIgnoreCase("XML")) {
			response.then().assertThat().contentType(ContentType.XML);
		} else {
			throw new RuntimeException("Currently matschie framework support JSON or XML.");
		}
	}

}
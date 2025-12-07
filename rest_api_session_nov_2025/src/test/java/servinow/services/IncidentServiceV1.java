package servinow.services;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import week3.day2.CreateRecordPayload;

public class IncidentServiceV1 {
	
	public static final String TABLENAME = "incident";
	
	private RequestSpecification given(RequestSpecBuilder requestSpecBuilder) {
		return RestAssured.given()
				.log().all()
                .spec(requestSpecBuilder.build());
	}
	
	public Response getRecords(RequestSpecBuilder requestSpecBuilder) {
		return given(requestSpecBuilder).get(TABLENAME);
	}
	
	public Response getRecordsByCategory(RequestSpecBuilder requestSpecBuilder, String categoryName) {
		return given(requestSpecBuilder.addQueryParam("category", categoryName)).get(TABLENAME);
	}
	
	public Response getRecord(RequestSpecBuilder requestSpecBuilder, String sysId) {
		return given(requestSpecBuilder).get(TABLENAME+"/"+sysId);
	}
	
	public Response getRecord(RequestSpecBuilder requestSpecBuilder) {
		return given(requestSpecBuilder).get(TABLENAME+"/{sys_id}");
	}
	
	public Response createNewRecord(RequestSpecBuilder requestSpecBuilder, CreateRecordPayload payload) {
		return given(requestSpecBuilder).body(payload).post(TABLENAME);
	}

}
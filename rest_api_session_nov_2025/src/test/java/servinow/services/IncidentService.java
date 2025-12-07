package servinow.services;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class IncidentService {
	
	public static final String TABLENAME = "incident";
	
	public Response getRecords(RequestSpecBuilder requestSpecBuilder) {
		return RestAssured.given()
				.log().all()
                .spec(requestSpecBuilder.build())
                .get(TABLENAME);
	}
	
	public Response getRecordsByCategory(RequestSpecBuilder requestSpecBuilder, String categoryName) {
		return RestAssured.given()
				          .log().all()
				          .spec(requestSpecBuilder
				        		  .addQueryParam("category", categoryName)
				        		  .build())
				          .get(TABLENAME);
	}
	
	public Response getRecord(RequestSpecBuilder requestSpecBuilder, String sysId) {
		return RestAssured.given()
				          .log().all()
				          .spec(requestSpecBuilder.build())
				          .get(TABLENAME+"/"+sysId);
	}
	
	public Response getRecord(RequestSpecBuilder requestSpecBuilder) {
		return RestAssured.given()
				          .log().all()
				          .spec(requestSpecBuilder.build())
				          .get(TABLENAME+"/{sys_id}");
	}

}
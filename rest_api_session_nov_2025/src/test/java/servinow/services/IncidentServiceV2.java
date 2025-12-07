package servinow.services;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import week3.day2.CreateRecordPayload;

public class IncidentServiceV2 {
	
	public static final String TABLENAME = "incident";
	private Response response;
	
	private RequestSpecification given(RequestSpecBuilder requestSpecBuilder) {
		return RestAssured.given()
				.log().all()
                .spec(requestSpecBuilder.build());
	}
	
	public void getRecords(RequestSpecBuilder requestSpecBuilder) {
		response = given(requestSpecBuilder).get(TABLENAME);
	}
	
	public void getRecordsByCategory(RequestSpecBuilder requestSpecBuilder, String categoryName) {
		response = given(requestSpecBuilder.addQueryParam("category", categoryName)).get(TABLENAME);
	}
	
	public void getRecord(RequestSpecBuilder requestSpecBuilder, String sysId) {
		response = given(requestSpecBuilder).get(TABLENAME+"/"+sysId);
	}
	
	public void getRecord(RequestSpecBuilder requestSpecBuilder) {
		response = given(requestSpecBuilder).get(TABLENAME+"/{sys_id}");
	}
	
	public void createNewRecord(RequestSpecBuilder requestSpecBuilder, CreateRecordPayload payload) {
		response = given(requestSpecBuilder).body(payload).post(TABLENAME);
	}
	
	public void validateResponse(int statusCode, String statusLine, String reponseFormat) {
		response.then().assertThat().statusCode(statusCode);
		response.then().assertThat().statusLine(Matchers.containsString(statusLine));
		if(reponseFormat.equalsIgnoreCase("JSON")) {
			response.then().assertThat().contentType(ContentType.JSON);
		} else if (reponseFormat.equalsIgnoreCase("XML")) {
			response.then().assertThat().contentType(ContentType.XML);
		} else {
			throw new RuntimeException("Servicenow table api response format should be JSON or XML");
		}
	}

}
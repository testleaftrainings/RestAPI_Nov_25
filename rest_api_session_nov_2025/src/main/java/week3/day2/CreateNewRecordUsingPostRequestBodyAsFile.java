package week3.day2;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;

public class CreateNewRecordUsingPostRequestBodyAsFile {	

	public static void main(String[] args) {
		
		File request_body = new File("src/main/resources/servicenow-payloads/create-incident-paylaod.json");
		
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("/api/now/table")		 
		 .contentType(ContentType.JSON)
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("table_name", "incident")
		 .log().all() // Request Log
	   .when()
	     .body(request_body)
		 .post("/{table_name}")
	   .then()
	     .log().all() // Response Log
	     .assertThat()
	     .statusCode(201)
	     .statusLine(Matchers.containsString("Created"))
	     .time(Matchers.lessThan(5000L)); // Used validate the response time should under 5000ms (5Sec).
	}

}
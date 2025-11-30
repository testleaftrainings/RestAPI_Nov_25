package week3.day2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;

public class CreateNewRecordUsingPostRequestBodyAsString {	

	public static void main(String[] args) {
		
		String request_body = """
				{
	             "description": "Create a new record using POST method",
	             "short_description": "RESTAPIOCT2025"
	            }			
				""";
		
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
	     .statusLine(containsString("Created"))
	     .time(lessThan(5000L)) // Used validate the response time should under 5000ms (5Sec).
	     .body("result.short_description", equalTo("RESTAPIOCT2025"));
	}

}
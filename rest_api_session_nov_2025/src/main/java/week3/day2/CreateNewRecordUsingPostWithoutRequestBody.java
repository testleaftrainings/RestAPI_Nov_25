package week3.day2;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;

public class CreateNewRecordUsingPostWithoutRequestBody {

	public static void main(String[] args) {
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("/api/now/table")
		 //.header("Content-Type", "application/json")
		 .contentType(ContentType.JSON)
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("table_name", "incident")
		 .log().all() // Request Log
	   .when()
		 .post("/{table_name}")
	   .then()
	     .log().all() // Response Log
	     .assertThat()
	     .statusCode(201)
	     .statusLine(Matchers.containsString("Created"))
	     .time(Matchers.lessThan(5000L)); // Used validate the response time should under 5000ms (5Sec).
	}

}
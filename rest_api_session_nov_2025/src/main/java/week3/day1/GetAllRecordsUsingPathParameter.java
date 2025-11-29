package week3.day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;

public class GetAllRecordsUsingPathParameter {

	public static void main(String[] args) {
		given()	
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("/api/now/table")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("table_name", "incident")		 
	   .when()	   
	     .get("/{table_name}")
	   .then()	   
	     .assertThat()
	     .statusCode(200)
	     .statusLine(containsString("OK"))
	     .contentType(ContentType.JSON)
	     .extract()
	     .response()
	     .prettyPrint();
	}

}
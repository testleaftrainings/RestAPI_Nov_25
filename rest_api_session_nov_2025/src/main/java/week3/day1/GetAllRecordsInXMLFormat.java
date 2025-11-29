package week3.day1;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
//import io.restassured.http.ContentType;

public class GetAllRecordsInXMLFormat {
	
	public static void main(String[] args) {
		RestAssured.given()
		           .auth()
		           .basic("admin", "e5!pRsPN%lH5")
		           //.header("Accept", "application/xml")
		           //.accept(ContentType.XML)
		           .accept("application/xml")
		           .when()
		           .get("https://dev324941.service-now.com/api/now/table/incident")
		           .then()
		           .assertThat()
		           .statusCode(200)
		           .statusLine(Matchers.containsString("OK"))
		           .contentType(ContentType.XML)
		           .extract()
		           .response()
		           .prettyPrint();
	}

}
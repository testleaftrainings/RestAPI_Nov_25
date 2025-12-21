package week6.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class JsonSchemaValidatorInRestAssured {	

	public static void main(String[] args) {
		RestAssured.given()
		           .baseUri("https://fakerestapi.azurewebsites.net")
		           .basePath("/api/v1")
		           .log().all()
		           .when()
		           .get("/Activities")
		           .then()
		           .assertThat()
		           .statusCode(200)
		           .contentType(ContentType.JSON)
		           .body(JsonSchemaValidator
		        		     .matchesJsonSchema(CreateJsonSchema.create(GetAcivitiesPojo[].class)));
	}

}
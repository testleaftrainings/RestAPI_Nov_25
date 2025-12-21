package week6.day1;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class JsonSchemaValidatorInRestAssured {
	
	static String expectedSchema = """
			{  
  "type": "array",
  "items": [
    {
      "type": "object",
      "properties": {
        "id": {
          "type": "integer"
        },
        "title": {
          "type": "string"
        },
        "dueDate": {
          "type": "string"
        },
        "completed": {
          "type": "boolean"
        }
      },
      "required": [
        "id",
        "title",
        "dueDate",
        "completed"
      ]
    } 
  ]   
}
			""";
	
	static File filePath = new File("./json-schema/response/fakerapi-response.json");

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
		           .body(JsonSchemaValidator.matchesJsonSchema(filePath));
	}

}
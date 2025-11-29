package week3.day1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;

public class GetASingleRecord {
	
	static Map<String, Object> pathParams = new HashMap<String, Object>();
	
	public static void main(String[] args) {
		
		pathParams.put("table_name", "incident");
		pathParams.put("sys_id", "46b66a40a9fe198101f243dfbc79033d");
		
		given()	
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("/api/now/table")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParams(pathParams)
		 //.pathParam("table_name", "incident")	
		 //.pathParam("sys_id", "46b66a40a9fe198101f243dfbc79033d")
	   .when()	   
	     .get("/{table_name}/{sys_id}")
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
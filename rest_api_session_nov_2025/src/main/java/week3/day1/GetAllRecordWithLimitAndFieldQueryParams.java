package week3.day1;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;

public class GetAllRecordWithLimitAndFieldQueryParams {

	static Map<String, Object> queryParams = new HashMap<String, Object>();
	
	public static void main(String[] args) {
		queryParams.put("sysparm_limit", 3);
		queryParams.put("sysparm_fields", "sys_id,number,short_description,description,category");
	
		given()	
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("/api/now/table")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("table_name", "incident")		 
		 .queryParams(queryParams)
	   .when()	     
	     .get("/{table_name}")
	   .then()	   
	     .assertThat()
	     .statusCode(200)
	     .statusLine(Matchers.containsString("OK"))
	     .contentType(ContentType.JSON)	    
	     .body("result", Matchers.hasSize(3))
	     .body("result", Matchers.everyItem(Matchers.hasKey("sys_id")))
	     .body("result", Matchers.everyItem(Matchers.hasKey("number")))
	     .body("result", Matchers.everyItem(Matchers.hasKey("short_description")))
	     .body("result", Matchers.everyItem(Matchers.hasKey("description")))
	     .body("result", Matchers.everyItem(Matchers.hasKey("category")))
	     .extract()
	     .response()
	     .prettyPrint();
	}
	
}
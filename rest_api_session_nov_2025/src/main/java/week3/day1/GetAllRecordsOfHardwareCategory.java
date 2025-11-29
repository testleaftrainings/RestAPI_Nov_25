package week3.day1;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;

public class GetAllRecordsOfHardwareCategory {
	
	static Map<String, Object> queryParams = new HashMap<String, Object>();

	public static void main(String[] args) {
		
		queryParams.put("category", "hardware");
		queryParams.put("sysparm_limit", 3);
		queryParams.put("sysparm_fields", "sys_id,number,short_description,description,category");
		
		given()	
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("/api/now/table")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("table_name", "incident")
		 .queryParam("category", "hardware")	
		 //.queryParams(queryParams)
	   .when()	   
	     //.get("/{table_name}?category=hardware&sysparm_limit=3&sysparm_fields=sys_id,number,short_description,description,category")
	     .get("/{table_name}")
	   .then()	   
	     .assertThat()
	     .statusCode(200)
	     .statusLine(Matchers.containsString("OK"))
	     .contentType(ContentType.JSON)	    
	     .body("result", Matchers.hasSize(193))
	     .body("result.category", Matchers.everyItem(Matchers.equalToIgnoringCase("hardware")))
	     .extract()
	     .response()
	     .prettyPrint();

	}

}

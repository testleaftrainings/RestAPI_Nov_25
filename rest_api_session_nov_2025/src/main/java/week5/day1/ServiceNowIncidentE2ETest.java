package week5.day1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import week3.day2.CreateRecordPayload;
import week3.day2.UpdateRecordPayload;

public class ServiceNowIncidentE2ETest {
	
	private CreateRecordPayload create_incident = new CreateRecordPayload();
	private UpdateRecordPayload update_incident = new UpdateRecordPayload();
	private String sysId;
	private String accessToken;
	
	@BeforeClass
	public void beforeClass() {
		accessToken = given()
        .baseUri("https://dev324941.service-now.com")
        .contentType(ContentType.URLENC)
        .when()
        .formParam("grant_type", "password")
        .formParam("client_id", "bbb30964cef04790bb5d715dd53dfac3")
        .formParam("client_secret", "!F:yt?X~Zb")
        .formParam("username", "admin")
        .formParam("password", "e5!pRsPN%lH5")
        .post("/oauth_token.do")
        .then()
        .log().body(true)
        .assertThat()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .extract()
        .jsonPath()
        .getString("access_token");
	}
	
	@Test(priority = 1)
	public void create_a_record() {
		
		create_incident.setShortDescription("RESTAPISESSIONOCT2025");
		
		sysId = given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("/api/now/table")		 
		 .contentType(ContentType.JSON)
		 .header("Authorization", "Bearer "+accessToken)
		 .pathParam("table_name", "incident")
		 .log().all() // Request Log
	   .when()
	     .body(create_incident)
		 .post("/{table_name}")
	   .then()
	     .log().all() // Response Log
	     .assertThat()
	     .statusCode(201)
	     .statusLine(containsString("Created"))
	     .time(lessThan(5000L)) // Used validate the response time should under 5000ms (5Sec).
	     .contentType(ContentType.JSON)
	     .body("result.short_description", equalTo(create_incident.getShortDescription()))
	     .body("result", hasKey("sys_id"))
	     .body("result.sys_id", notNullValue())
	     .body("result.sys_id", not(emptyString()))
	     .extract()
	     .jsonPath()
	     .getString("result.sys_id");
	}
	
	@Test(priority = 2)
	public void get_a_record() {
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("/api/now/table")		
		 .header("Authorization", "Bearer "+accessToken)
		 .pathParam("table_name", "incident")
		 .pathParam("sys_id", sysId)
		 .log().all()
	   .when()
	     .get("/{table_name}/{sys_id}")
	   .then()
	     .assertThat()
	     .statusCode(200)
	     .statusLine(containsString("OK"))
	     .contentType(ContentType.JSON)
	     .body("result.sys_id", equalTo(sysId));
	}
	
	@Test(priority = 3)
	public void update_a_record() {
		
		update_incident.setCategory("hardware");
		
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("/api/now/table")		
		 .header("Authorization", "Bearer "+accessToken)
		 .pathParam("table_name", "incident")
		 .pathParam("sys_id", sysId)
		 .contentType(ContentType.JSON)
		 .log().all()
	   .when()
	     .body(update_incident)
	     .put("/{table_name}/{sys_id}")
	   .then()
	     .assertThat()
	     .statusCode(200)
	     .statusLine(containsString("OK"))
	     .contentType(ContentType.JSON)
	     .body("result.sys_id", equalTo(sysId))
	     .body("result.category", equalTo(update_incident.getCategory()));
	}
	
	@Test(priority = 4)
	public void delete_a_record() {
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("/api/now/table")		
		 .header("Authorization", "Bearer "+accessToken)
		 .pathParam("table_name", "incident")
		 .pathParam("sys_id", sysId)
		 .log().all()
	   .when()
	     .delete("/{table_name}/{sys_id}")
	   .then()
	     .assertThat()
	     .statusCode(204)
	     .statusLine(containsString("No Content"))
	     .time(lessThan(3000L));
	}
	
	@Test(priority = 5)
	public void is_record_deleted_successfully() {
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("/api/now/table")		
		 .header("Authorization", "Bearer "+accessToken)
		 .pathParam("table_name", "incident")
		 .pathParam("sys_id", sysId)
		 .log().all()
	   .when()
	     .get("/{table_name}/{sys_id}")
	   .then()
	     .assertThat()
	     .statusCode(404)
	     .statusLine(containsString("Not Found"))
	     .contentType(ContentType.JSON);
	}

}
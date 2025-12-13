package week5.day1;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PassAccessTokenToGetAllRecords {
	
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

	@Test
	public void getAllRecords() {
		RestAssured.given()
        .auth()
        .oauth2(accessToken)
        .when()
        .get("https://dev324941.service-now.com/api/now/table/incident")
        .then()
        .assertThat()
        .statusCode(200)       
        .extract()
        .response()
        .prettyPrint();
	}

}

package week5.day1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateOauthAccessToken {

	public static void main(String[] args) {
		RestAssured.given()
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
		           .contentType(ContentType.JSON);
	}

}
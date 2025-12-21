package week6.day2;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class GetJiraProjectId {

	public static void main(String[] args) {
		String projectID = RestAssured.given()
		           .baseUri("https://karthistestlab.atlassian.net")
		           .basePath("/rest/api/3")
		           .log().all()
		           .auth()
		           .preemptive()
		           .basic("karthis.testlab@gmail.com", "<API-TOKEN>")
		           .when()
		           .get("/project/REST1234")
		           .then()
		           .log().ifValidationFails(LogDetail.ALL)
		           .assertThat()
		           .statusCode(200)
		           .contentType(ContentType.JSON)
		           .extract()
		           .jsonPath()
		           .getString("id");
		System.out.println(projectID);
	}

}
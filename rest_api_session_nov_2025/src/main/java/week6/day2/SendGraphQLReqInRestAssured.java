package week6.day2;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class SendGraphQLReqInRestAssured {
	
	static String qureryToPass = """
			query {
  viewer {
    followers(first: 10) {
      nodes {
        login
        followers(first: 10) {
          nodes {
            login
          }
        }
      }
    }
  }
}""";
	
	public static void main(String[] args) {
		RestAssured.given()
		           .baseUri("https://api.github.com")
		           .basePath("/graphql")
		           .header("Authorization","Bearer <API-TOKEN>")
		           .contentType(ContentType.JSON)
		           .log().all()
		           .when()
		           .body(convertGraphQLQueryToJsonString(qureryToPass))
		           .post()
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200)
		           .contentType(ContentType.JSON);
	}
	
	private static String convertGraphQLQueryToJsonString(String query) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("query", query);
		return jsonObject.toString();
	}

}
package week6.day1;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.restassured.RestAssured;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class WirMockWithRestAssured {
	
	WireMockServer server = new WireMockServer(8181);
	
	@BeforeSuite
	public void beforeSuite() {
		server.start();
	}
	
	@BeforeClass
	public void createMocking() {
		server.stubFor(get("/api/now/table/incident")
				.withBasicAuth("admin", "e5!pRsPN%lH5")
				.willReturn(aResponse()
		        .withStatus(200)
		        .withHeader("Content-Type", "application/json")
		        .withBodyFile("incident-records.json")));
		
		System.out.println("ServiceNow Incident Table Mocking is created.");
	}	
	
	@Test
	public void getMockTest() {
		RestAssured.given()
		           .baseUri("https://dev324941.service-now.com")
		           .basePath("/api/now/table/incident")
		           .auth()
		           .preemptive()
		           .basic("admin", "e5!pRsPN%lH5")
		           .log().all()
		           .when()
		           .get()
		           .then()
		           .log().headers()
		           .assertThat()
		           .statusCode(200);
	}
	
	@AfterSuite
	public void afterSuite() {
		server.stop();
	}

}
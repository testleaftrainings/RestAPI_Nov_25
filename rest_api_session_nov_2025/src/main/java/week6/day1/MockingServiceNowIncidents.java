package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;


public class MockingServiceNowIncidents {

	public static void main(String[] args) {		
		
		MappingBuilder requestMocking = WireMock.get("/api/now/table/incident");
		
		ResponseDefinitionBuilder responseMocking = WireMock.aResponse()
		        .withStatus(200)
		        .withHeader("Content-Type", "application/json")
		        .withBodyFile("incident-records.json");
		
		WireMock.stubFor(requestMocking.willReturn(responseMocking));
		
		System.out.println("ServiceNow Incident Table Mocking is created.");

	}

}

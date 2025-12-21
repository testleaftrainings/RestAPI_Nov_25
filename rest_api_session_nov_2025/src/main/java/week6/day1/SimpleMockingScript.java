package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class SimpleMockingScript {

	public static void main(String[] args) {
		
		// Request Mocking
		MappingBuilder requstMocking = WireMock.get("/welcome-message");
		
		// Response Mocking
		ResponseDefinitionBuilder responseMocking = WireMock.aResponse()
		        .withStatus(201)
		        .withBody("Welcome to Wirmock session!");
		
		WireMock.stubFor(requstMocking.willReturn(responseMocking));
		
		System.out.println("WireMock is created on mocking.");

	}

}
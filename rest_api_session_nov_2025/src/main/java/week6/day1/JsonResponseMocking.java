package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class JsonResponseMocking {

	public static void main(String[] args) {
		
		String jsonResPonse = """
				              {
				                "message": "WireMock is created on mocking."
				              }
				              """;
		
		MappingBuilder requestMocking = WireMock.get("/json/response");
		ResponseDefinitionBuilder responseMocking = WireMock.aResponse().withStatus(200)
		                    .withHeader("Content-Type", "application/json")
		                    .withBody(jsonResPonse);
		WireMock.stubFor(requestMocking.willReturn(responseMocking));
		System.out.println("Wiremock created JSON Mocking response.");
	}

}
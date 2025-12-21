package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class MockingPostHttpMethod {

	public static void main(String[] args) {
		
		String requestPayload = """
				                {
				                  "firstname": "Tom",
				                  "lastname": "Akehurst",
				                  "emailaddress": "tom.akehurst@eample.com"
				                }
				                """;
		String response = """
				          {
				            "message": "User registered successfully"
				          }
			              """;
		
		MappingBuilder requestMoking = WireMock.post("/create/user")
		        .withHeader("Content-Type", WireMock.equalTo("application/json"))
		        .withRequestBody(WireMock.equalToJson(requestPayload));
		
		ResponseDefinitionBuilder responseMocking = WireMock.aResponse().withStatus(201)
		                    .withHeader("Content-Type", "application/json")
		                    .withBody(response);
		
		WireMock.stubFor(requestMoking.willReturn(responseMocking));

	}

}

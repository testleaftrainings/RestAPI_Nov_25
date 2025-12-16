package com.testleaf.makaia.uibank.api.som;

import com.testleaf.makaia.uibank.serialization.pojos.CreateToken;

import io.restassured.http.ContentType;

public class CreateTokenService extends UiBank {

	private static final String LOGINSERVICE = "users/login";
	private String token;
	private String userId;

	public CreateTokenService() {
		requestBuilder = globalRequest();
	}

	public CreateTokenService createToken(CreateToken createToken) {
		response = apiClient.post(requestBuilder.setContentType(ContentType.JSON), LOGINSERVICE, createToken);
		return this;
	}
	
	public CreateTokenService extractId() {
		token = response.then()
				        .assertThat()
				        .statusCode(200)
				        .contentType(ContentType.JSON)
				        .extract().jsonPath().getString("id");
		extract.put("token", token);
		return this;
	}
	
	public CreateTokenService extractUserId() {
		userId = response.then()
				         .assertThat()
		                 .statusCode(200)
		                 .contentType(ContentType.JSON)
				         .extract().jsonPath().getString("userId");
		extract.put("userId", userId);
		return this;
	}	
	
	public AccountService callAccountService() {
		return new AccountService();
	}

}
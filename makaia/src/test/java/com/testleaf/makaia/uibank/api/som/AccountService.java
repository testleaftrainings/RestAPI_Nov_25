package com.testleaf.makaia.uibank.api.som;

import java.util.List;

public class AccountService extends UiBank {
	
	private static final String ACCOUNTSERVICE = "/accounts";
	
	public AccountService() {
		requestBuilder = globalRequest();
	}
		
	public AccountService getAccountsByUserId() {
		response = apiClient.get(requestBuilder
				      .addHeader("Authorization", "Bearer "+extract.get("token"))
				      .addQueryParam("filter[where][userId]", extract.get("userId")) 
				      , ACCOUNTSERVICE);
		return this;
	}
	
	public int getTotalAccountsCount() {
		List<Object> accounts = response.then().extract().jsonPath().getList("$");
		return accounts.size();
	}

}
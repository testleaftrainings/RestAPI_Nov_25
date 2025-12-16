package com.testleaf.makaia.uibank.e2e.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.testleaf.makaia.general.utils.PropertiesHandler.*;
import com.testleaf.makaia.testng.hooks.TestNGHooks;
import com.testleaf.makaia.uibank.api.som.AccountService;
import com.testleaf.makaia.uibank.api.som.CreateTokenService;
import com.testleaf.makaia.uibank.serialization.pojos.CreateToken;
import com.testleaf.makaia.uibank.ui.pages.LoginPage;

public class UiBankE2ETests extends TestNGHooks {
	
	private int numberOfAccounts;
	
	@BeforeClass
	public void beforeClass() {
		CreateToken createToken = new CreateToken();
		createToken.setUsername("FebApiuser");
		createToken.setPassword("Eagle@123");		
		
		new CreateTokenService()
		    .createToken(createToken)
		    .extractId()
		    .extractUserId();
	}
	
	@Test(priority = 1)
	public void loginPageTestInUI() {
		numberOfAccounts = new LoginPage()
		     .enterUserName(config("uibank.username"))
		     .enterPassword(secret("uibank.password"))
		     .clickSignInButton()
		     .clickAgreePrivacyPolicyPopUp()
		     .fetchAccountsCount()
		     .getNumberOfAccounts();
	}
	
	@Test(priority = 2)
	public void countNumberOfAccountIdsInAPI() {
		int actual = new AccountService().getAccountsByUserId().getTotalAccountsCount();
		Assert.assertEquals(actual, numberOfAccounts);
	}

}
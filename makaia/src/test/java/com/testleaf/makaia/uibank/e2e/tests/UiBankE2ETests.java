package com.testleaf.makaia.uibank.e2e.tests;

import org.testng.annotations.Test;

import static com.testleaf.makaia.general.utils.PropertiesHandler.*;
import com.testleaf.makaia.testng.hooks.TestNGHooks;
import com.testleaf.makaia.uibank.ui.pages.LoginPage;

public class UiBankE2ETests extends TestNGHooks {
	
	@Test
	public void loginPageTestInUI() {
		int numberOfAccounts = new LoginPage()
		     .enterUserName(config("makaia.uibank.username"))
		     .enterPassword(secret("uibank.password"))
		     .clickSignInButton()
		     .clickAgreePrivacyPolicyPopUp()
		     .fetchAccountsCount()
		     .getNumberOfAccounts();
		System.out.println(numberOfAccounts);
	}

}
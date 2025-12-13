package com.testleaf.makaia.uibank.ui.pages;

import static com.testleaf.makaia.general.utils.PropertiesHandler.*;
import com.testleaf.makaia.testng.hooks.TestNGHooks;
import com.testleaf.makaia.ui.design.Locators;

public class LoginPage extends TestNGHooks {
	
	public LoginPage() {
		loadUrl(config("makaia.aut.url"));
	}
	
	public LoginPage enterUserName(String username) {
		type(locateElement(Locators.ID, "username"), username);
		return this;
	}
	
	public LoginPage enterPassword(String password) {
		type(locateElement(Locators.ID, "password"), password);
		return this;
	}
	
	public LoginPage clickSignInButton() {
		click(locateElement(Locators.XPATH, "//button[text()='Sign In']"));
		return this;
	}
	
	public AccountPage clickAgreePrivacyPolicyPopUp() {
		click(locateElement(Locators.XPATH, "//button[contains(text(),'I agree to the Privacy Policy')]"));
		return new AccountPage();
	}

}
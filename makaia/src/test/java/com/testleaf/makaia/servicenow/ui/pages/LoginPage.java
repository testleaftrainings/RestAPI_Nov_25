package com.testleaf.makaia.servicenow.ui.pages;

import static com.testleaf.makaia.general.utils.PropertiesHandler.config;

import com.testleaf.makaia.testng.hooks.TestNGHooks;
import com.testleaf.makaia.ui.design.Locators;

public class LoginPage extends TestNGHooks {
	
	public LoginPage() {
		loadUrl(config("makaia.aut.url"));
	}
	
	public LoginPage enterUserName(String userName) {
		type(locateElement(Locators.ID, "user_name"), userName);
		return this;
	}

	public LoginPage enterPassword(String password) {
		type(locateElement(Locators.ID, "user_password"), password);
		return this;
	}

	public LoginPage clickLoingButton() {
		click(locateElement(Locators.ID, "sysverb_login"));
		return this;
	}
	
	public IncidentPage gotoIncidentPage() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		return new IncidentPage();
	}
	
	public ListofIncidents gotoListofIncidentsPage() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		return new ListofIncidents();
	}

}
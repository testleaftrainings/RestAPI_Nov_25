package com.testleaf.makaia.uibank.ui.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.testleaf.makaia.testng.hooks.TestNGHooks;
import com.testleaf.makaia.ui.design.Locators;

public class AccountPage extends TestNGHooks {
	
	public AccountPage() {
		try {
			Thread.sleep(Duration.ofSeconds(5));
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
	private int numberOfAccounts;
	
	private void setNumberOfAccounts(int numberOfAccounts) {
		this.numberOfAccounts = numberOfAccounts;		
	}
	
	public int getNumberOfAccounts() {
		return numberOfAccounts;
	}
	
	public AccountPage fetchAccountsCount() {
		List<WebElement> accounts = locateElements(Locators.CSS_SELECTOR, "div.account-row");
		setNumberOfAccounts(accounts.size());
		return this;
	}

}
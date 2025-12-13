package com.testleaf.makaia.testng.hooks;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.testleaf.makaia.general.utils.AllureHandler;
import com.testleaf.makaia.selenium.base.SeleniumBase;

import io.qameta.allure.Allure;

public class TestNGHooks extends SeleniumBase  {
	
	protected static String incidentNumber;

	@BeforeMethod
	public void beforeMethod(Method method) {
		if (method.getName().contains("UI")) {
			browserLaunch("chrome");
		}
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {

		if (!result.isSuccess()) {

			if (result.getMethod().getMethodName().contains("API")) {				
				
				// When API Test got failed				
				
			} else {

				try {
					Allure.addAttachment("Failure Screen Snapshot",
							FileUtils.openInputStream(new File(takeSnap(result.getName()))));
				} catch (IOException e) {
					e.printStackTrace();
				}

				// When UI Test got failed, Attached screenshot in the allure report				

			}

		}
		
		if(result.getMethod().getMethodName().contains("UI")) {
			quit();
		}

	}
	
	@AfterSuite
	public void afterSuite() {
		AllureHandler.moveHistoryFolderToAllureResults();
	}

}
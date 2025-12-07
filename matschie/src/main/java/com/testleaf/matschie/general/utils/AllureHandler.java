package com.testleaf.matschie.general.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.testleaf.matschie.general.utils.FilesHandler.*;

public class AllureHandler {
	
	private static final String ALLURE_RESULTS_DIR = "allure-results";
	private static final String ALLURE_REPORT_DIR = "allure-report";

	public static void moveHistoryFolderToAllureResults() {
		Path targetDir = Paths.get(System.getProperty("user.dir") + File.separator + ALLURE_RESULTS_DIR + File.separator + "history");
		Path sourceDir = Paths.get(System.getProperty("user.dir") + File.separator + ALLURE_REPORT_DIR + File.separator + "history");
	    if(isFolderExists(sourceDir.getParent()) && isFolderExists(targetDir.getParent()) && isFolderExists(targetDir)) {
	    	  if(isFolderExists(targetDir))	{
	    		  deleteFolder(targetDir.getParent()+File.separator+targetDir.getFileName());
	    	  }
	    	  moveFolder(sourceDir, targetDir);
	    }
	}

}
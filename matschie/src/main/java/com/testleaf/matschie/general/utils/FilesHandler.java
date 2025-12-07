package com.testleaf.matschie.general.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FileUtils;

public class FilesHandler {
	
	public static boolean isFolderExists(Path directoryLocation) {
		return (Files.exists(directoryLocation) && Files.isDirectory(directoryLocation));
	}
	
	public static void deleteFolder(String folderPath) {
		try {
			FileUtils.deleteDirectory(new File(folderPath));
			System.out.println(folderPath+" folder successfully deleted.");
		} catch (IOException e) {
			throw new RuntimeException("Unable to delete give"+folderPath+" folder due to: "+e.getLocalizedMessage());
		}
	}
	
	public static void moveFolder(Path moveFrom, Path moveTo) {
		try {
			Files.move(moveFrom, moveTo, StandardCopyOption.REPLACE_EXISTING)	;
			System.out.println("Folder inside "+moveFrom+" folder moved successfully to "+moveTo+" folder.");
		} catch (IOException e) {			
			System.err.println("Error moving folder: " + e.getLocalizedMessage());
			e.printStackTrace();
		}	
	}

}
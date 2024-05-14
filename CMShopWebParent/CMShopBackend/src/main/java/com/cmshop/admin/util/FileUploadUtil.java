package com.cmshop.admin.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		// get path of uploadDir
		Path uploadPath = Paths.get(uploadDir);
		
		//check existent of uploadPath, if not exist, create new directory
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		// inputStream allow to read data from file input
		try(InputStream inputStream = multipartFile.getInputStream()){
			//The resolve() method is used to concatenate two paths together.
			Path filePath = uploadPath.resolve(fileName);
			// copy data from inputStream to filePath, if file is existing, overring data
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		}catch(IOException e) {
			throw new IOException("Could not save file " + fileName,e);
		}
	}
	
	public static void cleanDir(String dir) {
		Path dirPath = Paths.get(dir);
		
		try {
			Files.list(dirPath).forEach(file -> {
				if(!Files.isDirectory(file))
					try {
						Files.delete(file);
					} catch (IOException e) {
						System.out.println("Could not delete file: " + file);
					}
			});
		}catch (IOException e) {
			System.out.println("Could not list directory: " + dirPath);
		}
	}
}

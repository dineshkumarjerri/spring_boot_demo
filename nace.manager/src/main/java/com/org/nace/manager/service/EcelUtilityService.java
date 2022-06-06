package com.org.nace.manager.service;

import org.springframework.web.multipart.MultipartFile;

public interface EcelUtilityService {

	String readExcelfile(MultipartFile multipartFile);
	
}

package com.org.nace.manager.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.org.nace.manager.domain.NaceData;
import com.org.nace.manager.dtos.NaceDataDto;

public interface NaceDataService {
	
	NaceDataDto getNaceDataRecord(Integer order);
	
	NaceDataDto createNaceData(NaceDataDto naceDataDto);

	List<NaceDataDto> getAllNaceDetails(int pageNo, int pageSize, String[] sortBy);
	
	String createNaceDetailsFromExcel(MultipartFile multipartFile);

}

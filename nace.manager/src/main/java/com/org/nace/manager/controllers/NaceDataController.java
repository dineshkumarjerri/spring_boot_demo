package com.org.nace.manager.controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.org.nace.manager.dtos.NaceDataDto;
import com.org.nace.manager.exceptions.NaceDetailsFileException;
import com.org.nace.manager.service.NaceDataService;
import com.org.nace.manager.service.NaceDetailsExcelServiceImpl;

@RestController
public class NaceDataController {
	
	@Autowired
	private NaceDataService naceDataService;
	
	@Autowired
	private NaceDetailsExcelServiceImpl naExcelServiceImpl;

	
	@GetMapping("/getNaceDetails")
	public NaceDataDto getNaceDetails(@RequestParam Integer orderId) {
		NaceDataDto naceDataDto = naceDataService.getNaceDataRecord(orderId);
		return naceDataDto;
	}
	
	@GetMapping("/getAllNaceDetails")
	public List<NaceDataDto> getAllNaceDetails(
			  @RequestParam(defaultValue = "0") int pageNo,
		      @RequestParam(defaultValue = "20") int pageSize,
		      @RequestParam(defaultValue = "orderId") String[] sortBy) {
		List<NaceDataDto> naceDataDtoList = naceDataService.getAllNaceDetails(pageNo, pageSize,sortBy);
		return naceDataDtoList;
	}
	
	@PostMapping("/putNaceDetails")
	public NaceDataDto putNaceDetails(@RequestBody NaceDataDto naceDataDto) {    
		return naceDataService.createNaceData(naceDataDto);
	}
	
	@PostMapping("/uploadNaceFile")
	public String uploadNaceFile(@RequestParam MultipartFile naceDataFile) { 
		long startTime = System.nanoTime();
		 String fileName = naceDataFile.getOriginalFilename();
		 if (fileName.substring(fileName.length() - 5, fileName.length()).equals(".xlsx")) {
			 String result = naceDataService.createNaceDetailsFromExcel(naceDataFile);
			 long endTime   = System.nanoTime();
			 long totalTime = endTime - startTime;
			 System.out.println(totalTime);
			 return result;
		 }else {
		         throw new NaceDetailsFileException("Please uplaod Excelsheet");
		     }
	}

}

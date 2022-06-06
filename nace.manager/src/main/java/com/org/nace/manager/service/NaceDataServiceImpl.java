package com.org.nace.manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.org.nace.manager.converters.NaceDataDtoToNaceData;
import com.org.nace.manager.converters.NaceDataToNaceDataDto;
import com.org.nace.manager.domain.NaceData;
import com.org.nace.manager.dtos.NaceDataDto;
import com.org.nace.manager.exceptions.RecordAlreadyExistsException;
import com.org.nace.manager.exceptions.RecordNotFoundException;
import com.org.nace.manager.repositories.NaceDataRepository;

@Service
public class NaceDataServiceImpl implements NaceDataService {

	
	NaceDataRepository naceDataRepo;
	NaceDataToNaceDataDto naceDataDtoConverter;
	NaceDataDtoToNaceData naceDtoToNaceDataConverter;
	NaceDetailsExcelServiceImpl naceExcelServiceImpl;
	
	public NaceDataServiceImpl(NaceDataRepository naceDataRepo, 
								NaceDataToNaceDataDto naceDataDtoConverter,
								NaceDataDtoToNaceData naceDtoToNaceDataConverter,
								NaceDetailsExcelServiceImpl naceExcelServiceImpl) {
		this.naceDataRepo = naceDataRepo;
		this.naceDataDtoConverter = naceDataDtoConverter;
		this.naceDtoToNaceDataConverter = naceDtoToNaceDataConverter;
		this.naceExcelServiceImpl = naceExcelServiceImpl;
	}

	@Override
	public NaceDataDto getNaceDataRecord(Integer order) {
		List<NaceData>  naceDataList = naceDataRepo.findByOrderId(order);
		if(naceDataList.size() > 0) {
			return naceDataDtoConverter.convert(naceDataList.get(0));
		}  else {
			throw new RecordNotFoundException("Record not found", NaceData.class.getName());
		}
	}

	@Override
	public NaceDataDto createNaceData(NaceDataDto naceDataDto) {
		List<NaceData>  naceDataList = naceDataRepo.findByOrderId(naceDataDto.getOrderId());
		NaceData naceData = null;
		if(naceDataList.size() == 0) {
			 naceData = naceDataRepo.save(naceDtoToNaceDataConverter.convert(naceDataDto));
		}  else {
			throw new RecordAlreadyExistsException("Record already Exists with unique Key", NaceData.class.getName());
		}
		 return naceDataDtoConverter.convert(naceData);
		
//		return "Record Added Successfully";
	}

	@Override
	public List<NaceDataDto> getAllNaceDetails(int pageNo, int pageSize, String[] sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<NaceData> pagedResult = naceDataRepo.findAll(pageable);
		 if(pagedResult.hasContent()) {
	            return pagedResult.getContent().stream().map(
	            		(naceData) -> naceDataDtoConverter.convert(naceData)).collect(Collectors.toList());
	        } else {
	            return new ArrayList<NaceDataDto>();
	        }
	}

	@Override
	public String createNaceDetailsFromExcel(MultipartFile multipartFile) {
	String result = naceExcelServiceImpl.readExcelfile(multipartFile);
		return "Nace Details Uploaded Successfully!";
	}
	
	
}

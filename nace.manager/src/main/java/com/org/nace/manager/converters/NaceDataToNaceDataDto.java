package com.org.nace.manager.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.org.nace.manager.domain.NaceData;
import com.org.nace.manager.dtos.NaceDataDto;


@Component
public class NaceDataToNaceDataDto {

	
	ModelMapper modelMapper;
	
	public NaceDataToNaceDataDto(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public NaceDataDto convert(NaceData naceData) {
		NaceDataDto naceDataDto = modelMapper.map(naceData, NaceDataDto.class);
		return naceDataDto;
		
	}
	
}

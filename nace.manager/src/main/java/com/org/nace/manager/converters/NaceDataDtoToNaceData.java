package com.org.nace.manager.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.org.nace.manager.domain.NaceData;
import com.org.nace.manager.dtos.NaceDataDto;

@Component
public class NaceDataDtoToNaceData {
	
	ModelMapper modelMapper;
	
	public NaceDataDtoToNaceData(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public NaceData convert(NaceDataDto naceDataDto) {
		NaceData naceData = modelMapper.map(naceDataDto, NaceData.class);
		return naceData;
	}

	
}

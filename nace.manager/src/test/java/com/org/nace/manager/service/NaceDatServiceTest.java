package com.org.nace.manager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.org.nace.manager.converters.NaceDataDtoToNaceData;
import com.org.nace.manager.converters.NaceDataToNaceDataDto;
import com.org.nace.manager.domain.NaceData;
import com.org.nace.manager.dtos.NaceDataDto;
import com.org.nace.manager.exceptions.RecordAlreadyExistsException;
import com.org.nace.manager.repositories.NaceDataRepository;

@ExtendWith(MockitoExtension.class)
public class NaceDatServiceTest {

	@Mock
	NaceDataRepository naceRepository;
	
	@Mock 
	NaceDataToNaceDataDto naceToNaceDataDto;
	
	@Mock
	NaceDataDtoToNaceData naceDtoToNaceDataConverter;
	
   @InjectMocks
   NaceDataServiceImpl naceService;
   
   private NaceData naceData;
   
   private NaceDataDto naceDataDto;
   
   @DisplayName("Unit test for createNaceData method")
   @Test
   public void givenNaceDataObject_whencreateNaceData_thenReturnEmployeeObject(){
       // given - precondition or setup
       given(naceRepository.findByOrderId(naceDataDto.getOrderId()))
               .willReturn(new ArrayList<NaceData>());
       
       given(naceDtoToNaceDataConverter.convert(naceDataDto)).willReturn(naceData);
       given(naceRepository.save(naceData)).willReturn(naceData);
       given(naceToNaceDataDto.convert(naceData)).willReturn(naceDataDto);

    // when -  action or the behaviour that we are going test
       NaceDataDto savedNaceData = naceService.createNaceData(naceDataDto);

       // then - verify the output
       assertThat(savedNaceData).isNotNull();
   }
   
   @DisplayName("Unit test for creataNaceData method which throws exception")
   @Test
   public void givenExistingorderId_whencreataNaceData_thenThrowsException(){
	   List<NaceData> naceDataList = new ArrayList<NaceData>();
	   naceDataList.add(naceData);
       given(naceRepository.findByOrderId(naceDataDto.getOrderId()))
               .willReturn(naceDataList);

   
      assertThrows(RecordAlreadyExistsException.class, () -> {
           naceService.createNaceData(naceDataDto);
       });
       verify(naceRepository, never()).save(any(NaceData.class));
   }
   
   @DisplayName("Unit test for getAllNaceDetails method")
   @Test
   public void givenNaceDataList_whenGetAllNaceDetails_thenReturnNaceDataList(){

	   NaceData naceData2 = setupNaceData2();
	   NaceDataDto naceData2Dto = setupNaceData2Dto();
	   List<NaceData> naceDataList = new ArrayList<NaceData>();
	   naceDataList.add(naceData);
	   naceDataList.add(naceData2);
	   Page<NaceData> naceDatas = new PageImpl<>(naceDataList);
	   String[] sortBy = {"orderId"};
	   Pageable pageable = PageRequest.of(0, 10, Sort.by(sortBy));
       given(naceRepository.findAll(pageable)).willReturn(naceDatas);
       given(naceToNaceDataDto.convert(naceData)).willReturn(naceDataDto);
       given(naceToNaceDataDto.convert(naceData2)).willReturn(naceData2Dto);


       List<NaceDataDto> naceDataDTOList = naceService.getAllNaceDetails(0, 10, sortBy);
       assertThat(naceDataDTOList).isNotNull();
       assertThat(naceDataDTOList.size()).isEqualTo(2);
   }
   
   @DisplayName("Unit test for getAllNaceDetails method (negative scenario)")
   @Test
   public void givenNaceDataList_whenGetAllNaceDetails_thenReturnEmptyNaceDataList(){
	   String[] sortBy = {"orderId"};
	   Pageable pageable = PageRequest.of(0, 10, Sort.by(sortBy));
	   Page<NaceData> naceDatas = new PageImpl<>(Collections.emptyList());
       given(naceRepository.findAll(pageable)).willReturn(naceDatas);

       List<NaceDataDto> naceDataDTOList = naceService.getAllNaceDetails(0, 10, sortBy);
       assertThat(naceDataDTOList).isEmpty();;
       assertThat(naceDataDTOList.size()).isEqualTo(0);
   }

       
   
   private NaceDataDto setupNaceData2Dto() {
	   NaceDataDto naceData2Dto = new NaceDataDto();
	   naceData2Dto.setId(2L);
	   naceData2Dto.setOrderId(1235);
	   naceData2Dto.setLevel(2);
	   naceData2Dto.setCode("A");
	   naceData2Dto.setParent("B");
	   naceData2Dto.setDescription("Growing of non-perennial crops");
	   naceData2Dto.setItemIncludes("This group includes the growing of non-perennial crops");
	   naceData2Dto.setItemAlsoIncludes("also includes");
	   naceData2Dto.setRulings("rullings are good");
	   naceData2Dto.setItemExcludes("i am exlcuded");
	   naceData2Dto.setIsicReference("11");
	   return naceData2Dto;
}

private NaceData setupNaceData2() {
	   NaceData naceData2 = new NaceData();
	   naceData2.setId(2L);
	   naceData2.setOrderId(1235);
	   naceData2.setLevel(2);
	   naceData2.setCode("A");
	   naceData2.setParent("B");
	   naceData2.setDescription("Growing of non-perennial crops");
	   naceData2.setItemIncludes("This group includes the growing of non-perennial crops");
	   naceData2.setItemAlsoIncludes("also includes");
	   naceData2.setRulings("rullings are good");
	   naceData2.setItemExcludes("i am exlcuded");
	   naceData2.setIsicReference("11");
	   return naceData2;
}

@BeforeEach
   public void setup() {
	   naceData = new NaceData();
	   naceData.setId(1L);
	   naceData.setOrderId(1234);
	   naceData.setLevel(2);
	   naceData.setCode("A");
	   naceData.setParent("B");
	   naceData.setDescription("Growing of non-perennial crops");
	   naceData.setItemIncludes("This group includes the growing of non-perennial crops");
	   naceData.setItemAlsoIncludes("also includes");
	   naceData.setRulings("rullings are good");
	   naceData.setItemExcludes("i am exlcuded");
	   naceData.setIsicReference("11");
	   
	   naceDataDto = new NaceDataDto();
	   naceDataDto.setId(1L);
	   naceDataDto.setOrderId(1234);
	   naceDataDto.setLevel(2);
	   naceDataDto.setCode("A");
	   naceDataDto.setParent("B");
	   naceDataDto.setDescription("Growing of non-perennial crops");
	   naceDataDto.setItemIncludes("This group includes the growing of non-perennial crops");
	   naceDataDto.setItemAlsoIncludes("also includes");
	   naceDataDto.setRulings("rullings are good");
	   naceDataDto.setItemExcludes("i am exlcuded");
	   naceDataDto.setIsicReference("11");
	   
   }
	
}

package com.org.nace.manager.controller.integration;


import com.org.nace.manager.dtos.NaceDataDto;
import com.org.nace.manager.service.NaceDataServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NaceDataServiceIntegrationTest {

	@Autowired
	NaceDataServiceImpl naceDataService;
	
	NaceDataDto naceDataDto;
    
   @Test
   public void test_createNaceData(){
	   setupNaceDataDto();
       NaceDataDto savedNaceData = naceDataService.createNaceData(naceDataDto);

       assertThat(savedNaceData).isNotNull();
       assertNotNull(savedNaceData.getId());
       assertEquals(naceDataDto.getOrderId(), savedNaceData.getOrderId());
       assertEquals(naceDataDto.getCode(), savedNaceData.getCode());
   }
	 
    public void setupNaceDataDto() {
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

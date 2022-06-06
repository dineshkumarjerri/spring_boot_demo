package com.org.nace.manager.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.org.nace.manager.domain.NaceData;
import com.org.nace.manager.exceptions.NaceDetailsFileException;
import com.org.nace.manager.repositories.NaceDataRepository;

@Component
public class NaceDetailsExcelServiceImpl implements EcelUtilityService{
	
	@Autowired
	private NaceDataRepository naceRepository;

	@Override
	public String readExcelfile(MultipartFile naceDataFile) {
		 try (InputStream excelIs = naceDataFile.getInputStream()) {
             Workbook wb = WorkbookFactory.create(excelIs); 
             Sheet sheet = wb.getSheetAt(0);
             insertNaceRecords(sheet);
             return "File Upload Success";
         } catch(IOException e) {
             throw new NaceDetailsFileException("Failed to process");
         } catch(Exception ex) {
        	 throw new NaceDetailsFileException("File data is invalid");
         }
	}
	
	public void insertNaceRecords(Sheet sheet) { 
         int numberOfRows = sheet.getPhysicalNumberOfRows();
         List<NaceData> naceDataList = new ArrayList();
        for(int i=1;i<numberOfRows; i++) { 
             Row currentRow = sheet.getRow(i);
             if(currentRow != null && currentRow.getCell(0) != null) {
            	 NaceData naceData = new NaceData();  
                 naceData.setOrderId((int) currentRow.getCell(0).getNumericCellValue());
                 naceData.setLevel((int) currentRow.getCell(1).getNumericCellValue());
                 if(currentRow.getCell(2).getCellType().equals(CellType.NUMERIC)) {
                	 naceData.setCode(currentRow.getCell(2).getNumericCellValue() + "");
                 } else {
                	 naceData.setCode(currentRow.getCell(2).getStringCellValue());
                 }  
                 if(currentRow.getCell(3) != null) {
	        		 if( currentRow.getCell(3).getCellType().equals(CellType.NUMERIC)) {
	                	 naceData.setCode(currentRow.getCell(3).getNumericCellValue() + "");
	                 } else {
	                	 naceData.setCode(currentRow.getCell(3).getStringCellValue());
	                 }  
                 } 
                 naceData.setDescription(currentRow.getCell(4) != null ? currentRow.getCell(4).getStringCellValue() : null );
                 naceData.setItemIncludes(currentRow.getCell(5) != null ? currentRow.getCell(5).getStringCellValue() : null );
                 naceData.setItemAlsoIncludes(currentRow.getCell(6) != null ? currentRow.getCell(6).getStringCellValue() : null );
                 if(currentRow.getCell(7) != null &&  !currentRow.getCell(7).getCellType().equals(CellType.FORMULA)) {
	                	 naceData.setCode(currentRow.getCell(7).getStringCellValue());
                 }
                 naceData.setItemExcludes(currentRow.getCell(8) != null ? currentRow.getCell(8).getStringCellValue() : null );
                 if(currentRow.getCell(9) != null) {
	        		 if( currentRow.getCell(9).getCellType().equals(CellType.NUMERIC)) {
	                	 naceData.setCode(currentRow.getCell(9).getNumericCellValue() + "");
	                 } else {
	                	 naceData.setCode(currentRow.getCell(9).getStringCellValue());
	                 }  
                 } 
                 naceDataList.add(naceData); 
             }
         }
        insertDatainBatch(naceDataList);
	}

	private void insertDatainBatch(List<NaceData> naceDataList) {
		int totalObjects = naceDataList.size();
		int batchSize = 500;
		for (int i = 0; i < totalObjects; i = i + batchSize) {
			    if( i+ batchSize > totalObjects){
			        List<NaceData> naceDatas = naceDataList.subList(i, totalObjects);
			        naceRepository.saveAll(naceDatas);
			        
			        break;
			    }
			    List<NaceData> naceDatas = naceDataList.subList(i, i + batchSize);
			    naceRepository.saveAll(naceDatas);
			}
	}
}

package com.telstra.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ReadDataExcel {
	
	public String readExcel(String sheetName, String variable) throws IOException{
		DataFormatter formatter = new DataFormatter();
	  	String filePath = System.getProperty("user.dir");
	    File file = new File(filePath+"/src/test/resources/TestData.xlsx");
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook testDataUIWorkbook = new XSSFWorkbook(inputStream);
	    Sheet testDataUISheet = testDataUIWorkbook.getSheet(sheetName);
	   
	    int rowCount = testDataUISheet.getLastRowNum()-testDataUISheet.getFirstRowNum();

	    for (int i = 0; i < rowCount+1; i++) {
	        Row row = testDataUISheet.getRow(i);
	        if(row.getCell(0).getStringCellValue().equals(variable))
	        {
	        	String inputText;
	        	if(row.getCell(1).getCellType()== Cell.CELL_TYPE_NUMERIC)
	        	{
	        		inputText = formatter.formatCellValue(row.getCell(1));
	        		
	        	}
	        	else 
	        	{
	        		inputText = row.getCell(1).getStringCellValue();
	        		
	        	}
	            return inputText;
	        }
	        else
	        	continue;   
	    }

	    return "default";

	}


}

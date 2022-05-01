package week6.day2.assignments.exceldata;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	public static String[][] readExcel(String filename) throws IOException {
		
		XSSFWorkbook wb = new XSSFWorkbook("./Exceldata/"+filename+".xlsx");
		
		XSSFSheet sheet = wb.getSheetAt(0);
		
		XSSFRow row = sheet.getRow(1);
		
		XSSFCell cell = row.getCell(1);
		
		int rowCount = sheet.getLastRowNum();
		
		int colCount = row.getLastCellNum();
		
		String data[][] = new String[rowCount][colCount];
		
		for (int i = 1; i <= rowCount; i++) {
			
			for (int j = 0; j < colCount; j++) {
				
				String stringCellValue = sheet.getRow(i).getCell(j).getStringCellValue();
				
				data[i-1][j]=stringCellValue;
			}
			
		}
		
	
		wb.close();
		
		return data;
		

	}

}

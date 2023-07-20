package com.gopal.MoneyWorkflow.utility;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.gopal.MoneyWorkflow.entities.TransactionDetail;

@Component
public class ExcelHelper {

	

	public byte[] appendInExcel(List<TransactionDetail> users) {
		
		// Create an Excel workbook
		Workbook workbook = new XSSFWorkbook();

		// Create a sheet in the workbook
		Sheet sheet = workbook.createSheet("Sheet1");

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {
			int startRow = 0;
			
			
			

			Field[] fields = TransactionDetail.class.getDeclaredFields();

			// Create the header row
			Row dataRow = sheet.createRow(startRow++);
			for (int i = 0; i < fields.length; i++) {
				Cell cell = dataRow.createCell(i);
				cell.setCellValue(fields[i].getName());
				
			}

			// Create the data rows
			for (TransactionDetail user : users) {
				dataRow = sheet.createRow(startRow++);

				for (int i = 0; i < fields.length; i++) {
					Cell cell = dataRow.createCell(i);
					fields[i].setAccessible(true);
					Object value = fields[i].get(user);
//					String data = String.valueOf(value);
//					System.out.println("data : "+ data);
//					cell.setCellValue(value.toString());
//					cell.setCellValue(String.valueOf(data));
					 if (value instanceof String) {
		                    cell.setCellValue((String) value);
		                } else if (value instanceof Integer) {
		                    cell.setCellValue((Integer) value);
		                } else if (value instanceof Double) {
		                    cell.setCellValue((Double) value);
		                } else if (value instanceof Long) {
		                    cell.setCellValue((Long) value);
		                }

				}

			}
			
			for (int i = 0; i < fields.length; i++) {
				sheet.autoSizeColumn(i);
				
			}


			workbook.write(outputStream);
			workbook.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Not able to generate excel sheet");
		}

		return outputStream.toByteArray();
	}

}

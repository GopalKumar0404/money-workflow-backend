package com.gopal.MoneyWorkflow.controller;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopal.MoneyWorkflow.utility.ExcelHelper;

@RestController
@RequestMapping("/user")
public class ExcelController{

    @GetMapping("{userId}/download")
    public ResponseEntity<byte[]> downloadExcel() throws IOException {
        // Create an Excel workbook
        Workbook workbook = new XSSFWorkbook();

        // Create a sheet in the workbook
        Sheet sheet = workbook.createSheet("Sheet1");

        // Create some sample data (you can replace this with your dynamic data)
        String[] headers = {"Name", "Age", "Email"};
        String[][] data = {
                {"John Doe", "30", "john.doe@example.com"},
                {"Jane Smith", "25", "jane.smith@example.com"},
                {"Bob Johnson", "35", "bob.johnson@example.com"}
        };

        // Create the header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Create the data rows
        for (int i = 0; i < data.length; i++) {
            Row dataRow = sheet.createRow(i + 1);
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = dataRow.createCell(j);
                cell.setCellValue(data[i][j]);
            }
        }

     // Adjust column width based on content length
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        // Write the workbook content to a byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        
        workbook.close();

        // Prepare the response with the Excel content
        HttpHeaders headers1 = new HttpHeaders();
        headers1.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers1.setContentDispositionFormData("attachment", "data.xlsx");

        return new ResponseEntity<>(outputStream.toByteArray(), headers1, 200);
    }
}

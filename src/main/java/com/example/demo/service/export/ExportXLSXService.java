package com.example.demo.service.export;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.FactureDTO;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class ExportXLSXService {

    public static final int HEADER_SIZE = 1;

    public void export(OutputStream os, List<ClientDTO> clients) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("clients");

        XSSFRow rowHeader = sheet.createRow(0);

        XSSFFont defaultFont = workbook.createFont();
        defaultFont.setFontHeightInPoints((short) 10);
        defaultFont.setFontName("Arial");
        defaultFont.setBold(true);
        defaultFont.setItalic(true);

        XSSFCell headerNom = rowHeader.createCell(0);
        headerNom.getCellStyle().setFont(defaultFont);
        headerNom.setCellValue("Nom");

        XSSFCell headerPrenom = rowHeader.createCell(1);
        headerPrenom.setCellValue("Prénom");

        for (int index = 0; index < clients.size(); index++) {
            XSSFRow row1 = sheet.createRow(index + HEADER_SIZE);
        }

        int rowNum = 1;
        for (ClientDTO client : clients) {
            XSSFRow row1 = sheet.createRow(rowNum);

            XSSFCell cellNom = row1.createCell(0);
            cellNom.setCellValue(client.getNom());

            XSSFCell cellPrenom = row1.createCell(1);
            cellPrenom.setCellValue(client.getNom());

            rowNum++;
        }


        workbook.write(os);
        workbook.close();
    }

    public void exportFacturesDUnClient(ServletOutputStream outputStream, List<FactureDTO> factures) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (FactureDTO facture : factures) {
            XSSFSheet sheet = workbook.createSheet("facture" + facture.getId());
        }
        workbook.write(outputStream);
        workbook.close();
    }
}

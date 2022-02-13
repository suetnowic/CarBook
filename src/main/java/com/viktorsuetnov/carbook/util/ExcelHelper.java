package com.viktorsuetnov.carbook.util;

import com.viktorsuetnov.carbook.model.Car;
import com.viktorsuetnov.carbook.model.Event;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String [] HEADERS = {"id", "car", "dateEvent", "typeOfWork", "consumables", "numberOfLitres", "price", "odometerReading", "note"};
    static String SHEET = "car";

    public static boolean hasExcelFormat(MultipartFile file){
        if (!TYPE.equals(file.getContentType())){
            return false;
        }
        return true;
    }

    public static List<Event> excelToEvent(Car car, InputStream inputStream){
            try {
                Workbook workbook = new XSSFWorkbook(inputStream);
                Sheet sheet = workbook.getSheet(SHEET);
                Iterator<Row> rows = sheet.iterator();
                List<Event> events = new ArrayList<>();
                int rowNumber = 0;
                while (rows.hasNext()){
                    Row currentRow = rows.next();
                    if (rowNumber == 0){
                        rowNumber++;
                        continue;
                    }
                    Iterator<Cell> cellsInRow = currentRow.iterator();
                    Event event = new Event();
                    int cellIdx = 0;
                    while (cellsInRow.hasNext()){
                        Cell currentCell = cellsInRow.next();
                        switch (cellIdx) {
                            case 0:
                                event.setDateEvent((Date) currentCell.getDateCellValue());
                                break;
                            case 1:
                                event.setTypeOfWork(currentCell.getStringCellValue());
                                break;
                            case 2:
                                event.setConsumables(currentCell.getStringCellValue());
                                break;
                            case 3:
                                event.setNumberOfLitres(currentCell.getNumericCellValue());
                                break;
                            case 4:
                                event.setPrice(currentCell.getNumericCellValue());
                                break;
                            case 5:
                                event.setOdometerReading(currentCell.getNumericCellValue());
                                break;
                            case 6:
                                event.setNote(currentCell.getStringCellValue());
                                break;
                            case 7:
                                event.setCar(car);
                            default:
                                break;
                        }
                        cellIdx++;
                    }
                    events.add(event);
                }
                workbook.close();
                return events;
            } catch (IOException e) {
                throw new RuntimeException("fail to parse Excel file " + e.getMessage());
            }
    }

    public static ByteArrayInputStream eventsToExcel(List<Event> events){
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();){
            Sheet sheet = workbook.createSheet(SHEET);
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADERS.length; col++){
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
            }

            int rowIdx = 1;
            for (Event event : events){
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(event.getId());
                row.createCell(1).setCellValue(event.getCar().getId());
                row.createCell(2).setCellValue(event.getDateEvent());
                row.createCell(3).setCellValue(event.getTypeOfWork());
                row.createCell(4).setCellValue(event.getConsumables());
                row.createCell(5).setCellValue(event.getNumberOfLitres());
                row.createCell(6).setCellValue(event.getPrice());
                row.createCell(7).setCellValue(event.getOdometerReading());
                row.createCell(8).setCellValue(event.getNote());
            }
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e){
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }

    }


}

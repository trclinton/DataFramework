package utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;

public class ReadExcel {

    public static Object primaryKey, secondaryKey, secondaryValue;
    public static LinkedHashMap<Object, LinkedHashMap<Object, Object>> excelUtils = new LinkedHashMap<Object, LinkedHashMap<Object, Object>>();
    public LinkedHashMap<Object, LinkedHashMap<Object, Object>> excelFileReader(String filePath, String sheetName){

        File file  = new File(filePath);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workBook = WorkbookFactory.create(inputStream);
            Sheet sheet = workBook.getSheet(sheetName);
            int lastRowCount = sheet.getPhysicalNumberOfRows();
            int lastColumnCount = sheet.getRow(0).getPhysicalNumberOfCells();
            for (int row=1; row<lastRowCount; row++){
                primaryKey = getCellValue(sheet.getRow(row).getCell(0));
                LinkedHashMap<Object, Object> secondaryMap = new LinkedHashMap<Object, Object>();
                for (int col=1; col<lastColumnCount;col++){
                    secondaryKey = getCellValue(sheet.getRow(0).getCell(col));
                    secondaryValue = getCellValue(sheet.getRow(row).getCell(col));
                    secondaryMap.put(secondaryKey, secondaryValue);
                }
                excelUtils.put(primaryKey, secondaryMap);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelUtils;
    }

    public static Object getCellValue(Cell cell) {
        Object cellValue = null;
        switch(cell.getCellType()){
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case BLANK:
                cell.setBlank();
                break;
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
        }
        return cellValue;
    }

}

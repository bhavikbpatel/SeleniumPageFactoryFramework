package com.pagefactory.framework.excelReader;

import java.io.FileInputStream;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static final Logger log = Logger.getLogger(ExcelReader.class.getName());
	

	public String path;
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;

	public ExcelReader(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public String[][] getDataFromSheet(String sheetName, String Excelname) {
		String datasets[][] = null;
		try {
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int totalRow = sheet.getLastRowNum() + 1;
			int totalCol = sheet.getRow(0).getLastCellNum();

			datasets = new String[totalRow - 1][totalCol];

			for (int i = 1; i < totalRow; i++) {

				XSSFRow rows = sheet.getRow(i);

				for (int j = 0; j < totalCol; j++) {
					XSSFCell cell = rows.getCell(j);

					if (cell.getCellType() == cell.CELL_TYPE_STRING)
						datasets[i - 1][j] = cell.getStringCellValue();
					else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
						String cellText = String.valueOf(cell.getNumericCellValue());
						datasets[i - 1][j] = cellText;
					} else
						datasets[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
				}

			}
			return datasets;
		} catch (Exception e) {
			log.info("Error reading file " + e.getMessage());
		}
		return datasets;
	}
	
	@SuppressWarnings("static-access")
	public String getCellData(String sheetname, String colName, int rowNum){
		try {
			int col_Num=0;
			int index=workbook.getSheetIndex(sheetname);
			sheet=workbook.getSheetAt(index);
			XSSFRow row=sheet.getRow(0);
			for (int i=1; i<row.getLastCellNum(); i++)
				if (row.getCell(i).getStringCellValue().equals(colName)) {
					col_Num=i;
					break;
				}
			row = sheet.getRow(rowNum -1);
			
			XSSFCell cell=row.getCell(col_Num);
			if (cell.getCellType()==cell.CELL_TYPE_STRING){
				return cell.getStringCellValue();
			} else if (cell.getCellType()==cell.CELL_TYPE_BLANK){
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

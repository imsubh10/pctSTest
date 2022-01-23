package utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadConfigClass {
	public String fileName = "./resources/config.xlsx";
	public String sheetname = "configuration";
	Object[][] data = null;

	public Object[][] readConfig() {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			String fileExtensionName = fileName.substring(fileName.indexOf("."));

//	if(fileExtensionName.equals(".xlsx"))
			XSSFWorkbook wb = new XSSFWorkbook(fis);
//	else if(fileExtensionName.equals(".xls")){		       
//		wb = new HSSFWorkbook(fis);
//    } 
			Sheet sh = wb.getSheet(sheetname);
			Row row = sh.getRow(0);
			int noOfRows = sh.getPhysicalNumberOfRows();
			int noOfCols = row.getLastCellNum();
			Cell cell;
			data = new Object[noOfRows - 1][noOfCols];
			for (int i = 1; i < noOfRows; i++) {
				for (int j = 0; j < noOfCols; j++) {
					row = sh.getRow(i);
					cell = row.getCell(j); // 1,1

					switch (cell.getCellType()) {
					case STRING:
						data[i - 1][j] = cell.getStringCellValue(); // 1,1
						break;
					case NUMERIC:
						data[i - 1][j] = (int) cell.getNumericCellValue();
						break;
					case BLANK:
						data[i - 1][j] = "";
						break;
					default:
						data[i - 1][j] = null;
						break;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}

}

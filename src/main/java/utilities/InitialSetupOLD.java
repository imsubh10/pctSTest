package utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class InitialSetupOLD {

	public class GetExcelData {

//		private static final String NUMERIC = null;
//		private static final String STRING = null;
//		private static final String BLANK = null;

		@DataProvider(name = "excel-data")
		public Object[][] excelDP() {
			Object[][] arrObj = getExcelData("./resources/datasheet.xlsx", "data");
			System.out.println("calling getExcel data");
			return arrObj;
		}

		public Object[][] getExcelData(String fileName, String sheetName) {

			Object[][] data = null;
			Workbook wb = null;
			try {
				System.out.println("In side try ");
				FileInputStream fis = new FileInputStream(fileName);
				String fileExtensionName = fileName.substring(fileName.indexOf("."));

//				if(fileExtensionName.equals(".xlsx"))
				wb = new XSSFWorkbook(fis);
//				else if(fileExtensionName.equals(".xls")){		       
//					wb = new HSSFWorkbook(fis);
//			    } 
				Sheet sh = wb.getSheet(sheetName);
				Row row = sh.getRow(0);
				int noOfRows = sh.getPhysicalNumberOfRows();
				int noOfCols = row.getLastCellNum();
				Cell cell;
				data = new Object[noOfRows - 1][noOfCols];
				for (int i = 1; i < noOfRows; i++) {
					for (int j = 0; j < noOfCols; j++) {
						row = sh.getRow(i);
						cell = row.getCell(j); 

						switch (cell.getCellType()) {
						case STRING:
							data[i - 1][j] = cell.getStringCellValue();
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
			}

			catch (Exception e) {
				System.out.println("The exception is: " + e.getMessage());
			}

			return data;
		}
	}
}

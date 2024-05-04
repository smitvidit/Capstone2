
package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class UpdateCredentialsUtility {

	public static String filePath = System.getProperty("user.dir") + "/src/main/java/testdata/credentials.xlsx";
	public static String sheetName = "Sheet1"; // Specify the name of the sheet

	public static void updateExcel(String firstName, String lastName, String email, String password)
			throws IOException, InvalidFormatException {
		FileInputStream fis = null;
		Workbook workbook = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(filePath);
			workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet(sheetName);

			// Find the next available row
			int rowNum = sheet.getLastRowNum() + 1;
			Row row = sheet.createRow(rowNum);

			// Set the cell values for the new row
			row.createCell(0).setCellValue(firstName);
			row.createCell(1).setCellValue(lastName);
			row.createCell(2).setCellValue(email);
			row.createCell(3).setCellValue(password);

			// Save the changes to the Excel file
			fos = new FileOutputStream(filePath);
			workbook.write(fos);

			System.out.println("Credentials are Updated Successfully");
		} finally {
			if (fis != null) {

				fis.close();
			}
			if (fos != null) {

				fos.close();
			}
		}
	}
}

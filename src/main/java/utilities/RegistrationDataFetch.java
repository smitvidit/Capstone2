package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class RegistrationDataFetch {

	public static String ConfirmPassword;
	public static String Password;
	public static String LastName;
	public static String FirstName;

	public static String Email;
	public static String filePath = System.getProperty("user.dir")
			+ "/src/main/java/testdata/automation.xlsx";

	public static void main(String[] args) throws InvalidFormatException {

		try {
			List<Map<String, String>> allRowsData = readAllRowsData(filePath);
			// vidit(readAllRowsData(filePath));
			// Map<String, String> myData = allRowsData.get(0);

			// String hello = myData.get("first_name");
			// System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhh " + hello);
			for (Map<String, String> rowData : allRowsData) {

				for (Map.Entry<String, String> entry : rowData.entrySet()) {
					// System.out.println(entry.getKey() + ": " + entry.getValue());

				}
				System.out.println(rowData);
			}
			vidit(filePath);
			System.out.println(FirstName + LastName + Password + ConfirmPassword + Email);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void reg() throws InvalidFormatException, IOException {
		vidit(filePath);
	}

	public static Map<String, String> vidit(String al) throws InvalidFormatException, IOException {
		List<Map<String, String>> myRowsData = readAllRowsData(al);
		Map<String, String> myData = myRowsData.get(0);
		FirstName = myData.get("first_name");
		LastName = myData.get("last_name");
		Password = myData.get("password");
		ConfirmPassword = myData.get("confirm_password");
		Email = myData.get("email");
		// System.out.println(FirstName+LastName+Password+ConfirmPassword+Email);

		return myData;
	}

	public static String getFirstName() {

		return FirstName;
	}

	public static String getLastName() {
		return LastName;
	}

	public static String getPassword() {
		return Password;
	}

	public static String getConfirmPassword() {
		return ConfirmPassword;
	}

	public static String getEmail() {
		return Email;
	}

	public static List<Map<String, String>> readAllRowsData(String filePath)
			throws IOException, InvalidFormatException {
		List<Map<String, String>> allRowsData = new ArrayList<>();
		FileInputStream fis = null;
		Workbook workbook = null;

		try {
			fis = new FileInputStream(filePath);
			workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

			Row headerRow = sheet.getRow(0);

			int lastCellNum = headerRow.getLastCellNum();
			int lastrownum = sheet.getLastRowNum();
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					Map<String, String> rowData = new HashMap<>();
					for (int j = 0; j < lastCellNum; j++) {
						String key = headerRow.getCell(j).toString();
						// System.out.println("key is : " + key);
						// System.out.println(row.getCell(j));
						String value = getStringValue(row.getCell(j));
						// System.out.println("values is : " + value);
						rowData.put(key, value);
						// System.out.println(rowData);
					}
					// System.out.println("Row added");
					allRowsData.add(rowData);

				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e; // Re-throwing the exception
		} catch (IOException e) {
			e.printStackTrace();
			throw e; // Re-throwing the exception
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return allRowsData;
	}

	private static String getStringValue(Cell cell) {
		if (cell == null) {
			return "";
		} else {
			return cell.toString();
		}
	}
}

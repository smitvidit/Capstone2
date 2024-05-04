package utilities;

// OtherClass.java

public class exceldata  {

	public static void main(String[] args) {
		// Call the getTestData method from the class containing it
		String sheetName = "registration"; // Specify the name of your Excel sheet
		Object[][] testData = TestUtil.getTestData(sheetName);

		// Now you can use the testData array in this class as needed
		// For example, printing the data
		for (Object[] row : testData) {
			for (Object cell : row) {
				System.out.print(cell.toString() + "\t");
			}
			//System.out.println();
		}
	}
}

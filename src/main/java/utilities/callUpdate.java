package utilities;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class callUpdate {

	public static void main(String[] args) throws InvalidFormatException, IOException {
		UpdateCredentialsUtility.updateExcel("hello", "patel", "hello@gmail.com", "Hello@123");
	}

}

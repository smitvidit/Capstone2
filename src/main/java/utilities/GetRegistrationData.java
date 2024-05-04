package utilities;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class GetRegistrationData {

	public static void main(String[] args) throws InvalidFormatException, IOException {

		RegistrationDataFetch.reg();
		String FirstName = RegistrationDataFetch.FirstName;
		String LastName = RegistrationDataFetch.LastName;
		String Password = RegistrationDataFetch.Password;
		String ConfirmPassword = RegistrationDataFetch.ConfirmPassword;
		String Email = RegistrationDataFetch.Email;

		System.out.println(FirstName + " : " + LastName + " : " + Password + " : " + ConfirmPassword + " : " + 
		Email);
	}

}

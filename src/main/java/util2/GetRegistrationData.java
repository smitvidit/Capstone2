package util2;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class GetRegistrationData {

	public static void main(String[] args) throws InvalidFormatException, IOException {

		CredentialsFetchExcel.reg();
		String FirstName = CredentialsFetchExcel.FirstName;
		String LastName = CredentialsFetchExcel.LastName;
		String Password = CredentialsFetchExcel.Password;
		String Email = CredentialsFetchExcel.Email;

		System.out.println(FirstName + " : " + LastName + " : " + Email + " : " + Password);
	}

}

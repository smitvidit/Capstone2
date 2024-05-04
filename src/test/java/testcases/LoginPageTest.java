
package testcases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import util2.CredentialsFetchExcel;

public class LoginPageTest extends BaseTest {

	public static String FirstName;
	public static String LastName;
	public static String Email;
	public static String Password;

	@Test
	public void LoginSuccessful() throws InvalidFormatException, IOException, InterruptedException {

		homePage.navigateToHomePage();
		homePage.clickSignIn();

		CredentialsFetchExcel.reg();
		FirstName = CredentialsFetchExcel.FirstName;
		LastName = CredentialsFetchExcel.LastName;
		Password = CredentialsFetchExcel.Password;
		Email = CredentialsFetchExcel.Email;

		System.out.println(FirstName + " : " + LastName + " : " + Email + " : " + Password);

		loginPage.enterRegisteredEmail(Email);
		loginPage.enterRegisteredPassword(Password);
		loginPage.clickSignInButton();

		String welcomeUser = myAccountPage.isLoggedIn();

		System.out.println("Welcome User Name is : " + welcomeUser);

		System.out.println("First User Name is : " + FirstName);
		Assert.assertTrue(welcomeUser.contains(FirstName), "Not Logged In");
	}

	@Test
	public void LoginWithInvalidCredentials() {
		homePage.navigateToHomePage();
		homePage.clickSignIn();
		loginPage.enterRegisteredEmail("asdasd@gmail.com");
		loginPage.enterRegisteredPassword(Password);
		loginPage.clickSignInButton();

		String invalidLoginActual = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
		Assert.assertEquals(invalidLoginActual, loginPage.incorrectLoginMethod(), "");
		Assert.assertTrue(invalidLoginActual.equals(loginPage.incorrectLoginMethod()), "");
		;
	}

	@Test
	public void ForgotFunctionality() throws InvalidFormatException, IOException {

		homePage.navigateToHomePage();
		homePage.clickSignIn();
		loginPage.clickForgotPasswordButton();
		CredentialsFetchExcel.reg();

		Email = CredentialsFetchExcel.Email;
		System.out.println("Above step passed again");
		boolean a = forgotPasswordPage.isForgotElementPresent();
		boolean b = forgotPasswordPage.isResetMyPasswordElementPresent();

		System.out.println("isForgotElementPresent value is : " + a);
		System.out.println("isResetMyPasswordElementPresent value is : " + b);

		if (a) {
			System.out.println("Forgot your password is present");
		}
		if (b) {
			System.out.println("Reset My password Element is present");
		}
		Assert.assertTrue(a);
		Assert.assertTrue(b);

		forgotPasswordPage.typeEmail(Email);
		forgotPasswordPage.resetButtonClick();

		Assert.assertEquals(loginPage.resetPasswordSuccessTextMethod(), "If there is an account associated with "
				+ Email + " you will receive an email with a link to reset your password.");

		// If there is an account associated with hello@hello.com you will receive an
		// email with a link to reset your password.
		// If there is an account associated with user1712399742848@example.com you will
		// receive an email with a link to reset your password.
		// loginPage.resetPasswordSuccessTextMethod();
	}

}

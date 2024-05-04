package testcases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.UpdateCredentialsUtility;

public class CreateAccountPageTest extends BaseTest {

	
	static Logger log = Logger.getLogger(CreateAccountPageTest.class);
	@Test
	public void CreateAccount() throws InvalidFormatException, IOException {

		
		log.info("CreateAccount Called");
		homePage.navigateToHomePage();
		homePage.clickCreateAccount();
	
		createAccountPage.enterFirstName(getRandomFirstName);
		createAccountPage.enterLastName(getRandomLastName);
		createAccountPage.enterEmail(getRandomEmail);
		createAccountPage.enterPassword(getRandomPassword);
		createAccountPage.enterConfirmPassword(getRandomPassword);
		createAccountPage.clickCreateAccountButton();

		Assert.assertEquals(myAccountPage.getSuccessMessage(), "Thank you for registering with Main Website Store.",
				"You have not successfully created the account");

		Assert.assertTrue(myAccountPage.isOnMyAccountPage(), "You are not on My Accounts Page");
		Assert.assertEquals(myAccountPage.getAccountContactInformation().replaceAll("\\s+", " ").trim(),
				getRandomFirstName + " " + getRandomLastName + " " + getRandomEmail,
				"The 'Account Information' displayed does not match the expected user information.");

		// Update the credentials sheet with current user details
		UpdateCredentialsUtility.updateExcel(getRandomFirstName, getRandomLastName, getRandomEmail, getRandomPassword);
	}

	@Test
	public void validateMandatoryFieldsOnCreateAccountPage() {

		homePage.navigateToHomePage();
		homePage.clickCreateAccount();

		createAccountPage.clickCreateAccountButton();
		// This is a required field.

		// Assert.assertEquals(createAccountPage.FirstNameErrorField(),"This is a
		// required field.", null);
		Assert.assertTrue(createAccountPage.firstNameErrorField().equals("This is a required field."));
		Assert.assertTrue(createAccountPage.lastNameErrorField().equals("This is a required field."));
		Assert.assertTrue(createAccountPage.emailErrorField().equals("This is a required field."));
		Assert.assertTrue(createAccountPage.passwordErrorField().equals("This is a required field."));
		Assert.assertTrue(createAccountPage.confirmPasswordErrorField().equals("This is a required field."));

	}

}

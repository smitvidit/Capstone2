package testcases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class demo extends BaseTest {

	public static String FirstName;
	public static String LastName;
	public static String Email;
	public static String Password;

	@Test
	public void demo() throws InvalidFormatException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		homePage.navigateToHomePage();
		homePage.clickSignIn();
		loginPage.enterRegisteredEmail("asdasd@gmail.com");
		loginPage.enterRegisteredPassword("HELLO@AWE");
		loginPage.clickSignInButton();

		String windowhandle = driver.getWindowHandle();
		Set<String> windowhandle1 = driver.getWindowHandles();

		for (String s : windowhandle1) {
			driver.switchTo().window(s);
		}

		SoftAssert s = new SoftAssert();
		s.assertTrue(true, "this is failing");

		Iterator<String> itr = windowhandle1.iterator();

		String invalidLoginActual = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
		Assert.assertEquals(invalidLoginActual, loginPage.incorrectLoginMethod(), "");
		Assert.assertTrue(invalidLoginActual.equals(loginPage.incorrectLoginMethod()), "");
		;
	}

}

package testcases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.DriverSetup;
import pages.BasePage;
import pages.CheckoutPaymentsPage;
import pages.CheckoutShippingPage;
import pages.CheckoutSuccessPage;
import pages.CreateAccountPage;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MenJacketsPage;
import pages.MyAccountPage;
import utilities.CredentialsGenerator;

public class BaseTest {

	// Declaring WebDriver, WebDriverWait, and Actions for browser and user
	// interaction
	protected WebDriver driver;
	private WebDriverWait wait;
	private Actions actions;

	DriverSetup driverSetup;
	private final String browserName = "chrome";

	// Page Objects representing various pages in the application.
	BasePage basePage;
	HomePage homePage;
	CreateAccountPage createAccountPage;
	MyAccountPage myAccountPage;
	LoginPage loginPage;
	ForgotPasswordPage forgotPasswordPage;
	MenJacketsPage menJacketsPage;
	CheckoutShippingPage checkoutShippingPage;
	CheckoutPaymentsPage checkoutPaymentsPage;
	CheckoutSuccessPage checkoutSuccessPage;

	// Variables to store generated data for testing.
	public String getRandomEmail;
	public String getRandomPassword;
	public String getRandomFirstName;
	public String getRandomLastName;

	public BaseTest() {
		// Generate random data once for the entire test class.

		getRandomPassword = CredentialsGenerator.getRandomPassword1();
		getRandomFirstName = CredentialsGenerator.generateRandomFirstName(5);
		getRandomLastName = CredentialsGenerator.generateRandomLastName(7);
		getRandomEmail = CredentialsGenerator.getRandomEmail();
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@BeforeMethod
	public void setUp() {
		driverSetup = new DriverSetup();
		// Initiate the WebDriver, WebDriverWait and Actions
		driver = driverSetup.initiateDriver(browserName);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		actions = new Actions(driver);

		// Initialize Page Objects
		basePage = new BasePage(driver, wait, actions);
		homePage = new HomePage(driver, wait);
		createAccountPage = new CreateAccountPage(driver, wait);
		myAccountPage = new MyAccountPage(driver, wait);
		loginPage = new LoginPage(driver, wait);
		forgotPasswordPage = new ForgotPasswordPage(driver, wait);

		menJacketsPage = new MenJacketsPage(driver, wait, actions);
		checkoutShippingPage = new CheckoutShippingPage(driver, wait);
		checkoutPaymentsPage = new CheckoutPaymentsPage(driver, wait);
		checkoutSuccessPage = new CheckoutSuccessPage(driver, wait);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}
}
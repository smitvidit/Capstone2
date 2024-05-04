package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;
	private WebDriverWait wait;

	private By emailLoginPageField = By.xpath("//*[@id='email']");
	private By passwordLoginPageField = By.xpath("(//*[@id='pass'])[1]");
	private By signInButtonLoginPage = By.xpath("(//*[@id='send2']/span)[1]");

	private By incorrectLoginError = By
			.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']");
	private By forgotPassword = By.xpath("//a[@class='action remind']//span[contains(text(),'Forgot Your Password?')]");

	private By resetPasswordSuccessText = By
			.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");

	public LoginPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void enterRegisteredEmail(String enterEmail) {

		WebElement emailLoginPageField_WebElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(emailLoginPageField));
		emailLoginPageField_WebElement.sendKeys(enterEmail);
	}

	public void enterRegisteredPassword(String enterPassword) {
		WebElement passwordLoginPageField_WebElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(passwordLoginPageField));
		passwordLoginPageField_WebElement.sendKeys(enterPassword);
	}

	public void clickSignInButton() {
		WebElement signInButtonLoginPage_WebElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(signInButtonLoginPage));
		signInButtonLoginPage_WebElement.click();
	}

	public void clickForgotPasswordButton() {
		WebElement ForgotPassword_webELement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(forgotPassword));
		ForgotPassword_webELement.click();
	}

	public String resetPasswordSuccessTextMethod() {
		WebElement resetPasswordSuccessText_webELement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(resetPasswordSuccessText));
		return resetPasswordSuccessText_webELement.getText();
	}

	private final String expectedCustomerLoginPage = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/";

	// Method to compare the current URL with the expected URL
	public boolean isOnLoginPage() {
//        return isOnPage(expectedCustomerLoginPage);

		// Get the actual URL
		wait.until(ExpectedConditions.urlToBe(expectedCustomerLoginPage));
		String actualUrl = driver.getCurrentUrl();

		// Compare the actual URL with the expected URL
		return actualUrl.equals(expectedCustomerLoginPage);
	}

	public String incorrectLoginMethod() {
		WebElement incorrectLoginError_WebElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(incorrectLoginError));
		String invalidLoginExpected = incorrectLoginError_WebElement.getText();
		return invalidLoginExpected;
	}
}
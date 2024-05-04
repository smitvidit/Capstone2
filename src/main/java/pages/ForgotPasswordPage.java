package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {

	private WebDriver driver;
	private WebDriverWait wait;

	private By forgotPasswordTextDisplay = By.xpath("//span[@class='base']");
	private By ResetMyPasswordButton = By.xpath("//button[@class='action submit primary']");
	private By emailText = By.xpath("//input[@id='email_address']");
	public WebElement element1;
	// input[@id='email_address']

	public ForgotPasswordPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public boolean isForgotElementPresent() {
		try {
			// Find the element
			WebElement element0 = driver.findElement(forgotPasswordTextDisplay);

			// Check if the element is displayed
			return element0.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// Element not found
			return false;
		}
	}

	public boolean isResetMyPasswordElementPresent() {
		try {
			// Find the element
			element1 = driver.findElement(ResetMyPasswordButton);

			// Check if the element is displayed
			return element1.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// Element not found
			return false;
		}
	}

	public void typeEmail(String s) {
		WebElement element3 = driver.findElement(emailText);
		element3.sendKeys(s);
	}

	public void resetButtonClick() {
		element1.click();
	}
}

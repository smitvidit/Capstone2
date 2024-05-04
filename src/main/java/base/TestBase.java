package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
//	public static EventFiringWebDriver e_driver;
//	public static WebEventListener eventListener;

	public TestBase() {
		try {

			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/crm" + "/qa/config/config.properties");
			prop.load(ip);
			String ss1 = prop.getProperty("browser");

		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

//	public static void initialization() {
//		System.out.println("Initialization started");
//		String browserName = prop.getProperty("browser");
//
//		if (browserName.equals("chrome")) {
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//
//			System.out.println("Browser initialised : " + browserName);
//		}
////		e_driver = new EventFiringWebDriver(driver);
////		eventListener = new WebEventListener();
////		e_driver.register(eventListener);
////		driver = e_driver;
//
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		// driver.manage().timeouts().pageLoadTimeout(20, Durat)
//
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//
//		driver.get(prop.getProperty("url"));
//		;
//
//	}
}

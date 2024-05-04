package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class testProperties {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			Properties prop = new Properties();
			
			//C:\Users\vidit.patel\eclipse-workspace\FreeCRMTest\src\main\java\com\crm\qa\config\config.properties
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/crm" + "/qa/config/config.properties");
			prop.load(ip);
			String ss1 = prop.getProperty("browser");
			System.out.println(ss1);
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}

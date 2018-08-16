package demo.MeraTransport.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import demo.MeraTransport.tools.WebDriverCommand;

public class TestBaseSetUp {

	protected static WebDriver driver;

	static PropertiesFile prop = new PropertiesFile();
	static String appUrl = prop.getProperty("BASE_URL").trim();
	static String browserType = prop.getProperty("BROWSER_TYPE").trim().toLowerCase();
	static String platform = prop.getProperty("PLATFORM").trim();
	static String directory = null;

	public WebDriver getDriver() {
		return driver;
	}

	@BeforeClass
	public void initializeTestBaseSetup() {

		String driverPath = getDriverDirectory();
		System.out.println("Driver path while initializing is ::: " + driverPath);
		if (browserType.equalsIgnoreCase("chrome")) {
			System.out.println("Launching google chrome with new profile.." + driverPath.replace("driverType", "chromedriver"));
			System.setProperty("webdriver.chrome.driver", driverPath.replace("driverType", "chromedriver"));
			driver = new ChromeDriver();
		} else if (browserType.equalsIgnoreCase("internetexplorer")) {
			System.out.println("Launching internet explorer chrome with new profile.." + driverPath.replace("driverType", "IEDriverServer"));
			System.setProperty("webdriver.ie.driver", driverPath.replace("driverType", "IEDriverServer"));
			driver = new InternetExplorerDriver();
		} else if (browserType.equalsIgnoreCase("firefox")) {
			System.out.println("Launching firefox with new profile.." + driverPath.replace("driverType", "geckodriver"));
			System.setProperty("webdriver.gecko.driver", driverPath.replace("driverType", "geckodriver"));
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverCommand.openUrl(appUrl);
	}

	public String getDriverDirectory() {
		if (platform.equalsIgnoreCase("windows")) {
			directory = System.getProperty("user.dir") + "/Drivers/driverType.exe";
		} else if (platform.equalsIgnoreCase("linux")) {
			directory = System.getProperty("user.dir") + "/Drivers/driverType";
		}
		return directory;
	}

	@AfterClass
	public static void tearDown() {
		WebDriverCommand.close();
	}
}

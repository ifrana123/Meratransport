package demo.MeraTransport.tools;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;

import demo.MeraTransport.base.TestBaseSetUp;

public class WebDriverCommand extends TestBaseSetUp {

	public static WebDriverWait wait;

	public static void openUrl(String appUrl) {
		// TODO Auto-generated method stub
			driver.get(appUrl);
			try {
				Thread.sleep(5000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void close() {
		// TODO Auto-generated method stub
		try {
			driver.quit();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
	}
	
	public static void delay(Long delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

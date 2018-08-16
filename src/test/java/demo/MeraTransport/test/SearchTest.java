package demo.MeraTransport.test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import demo.MeraTransport.base.TestBaseSetUp;
import demo.MeraTransport.tools.WebDriverCommand;

public class SearchTest extends TestBaseSetUp {

	String searchText = "Men's T-Shirts";
	String expectedTitle = "Amazon.in: Men's T-shirts - T-Shirts & Polos / Men: Clothing & Accessories";

	@BeforeMethod
	public void searchRequiredProducts() {

		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(searchText);
		driver.findElement(By.xpath("//input[@value='Go']")).click();
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase(expectedTitle));

	}

	@Test
	public void filterByColors() {
		
		// List of the colors we selected (Orange, White & Green)
		List<String> listOfSelectedColors = listOfSelectedColors();
		
		System.out.println("List of selected Colors :  "+listOfSelectedColors);
		
		// Scroll the screen to color selections and click Orange element
		scrollToColorElement();
		driver.findElement(By.xpath("//a[@title='Orange']//div[@class='colorsprite s-ref-text-link']")).click();
		WebDriverCommand.delay(2000L);
		
		// Scroll the screen to color selections and click White element
		scrollToColorElement();
		driver.findElement(By.xpath("//a[@title='White']//div[@class='colorsprite s-ref-text-link']")).click();
		WebDriverCommand.delay(2000L);
		
		// Scroll the screen to color selections and click Green element
		scrollToColorElement();
		driver.findElement(By.xpath("//a[@title='Green']//div[@class='colorsprite s-ref-text-link']")).click();
		WebDriverCommand.delay(2000L);
		
		// Get the list of hex color code of all the displayed items
		List<String> listOfColorsOfFilteredItems = new ArrayList();
		List<WebElement> elements = driver.findElements(By.xpath("//img[@class='s-access-image cfMarker']"));
		for(int i =0; i<=elements.size()-1; i++) {
			String colorElements = elements.get(i).getCssValue("color");
			listOfColorsOfFilteredItems.add(Color.fromString(colorElements).asHex());			
		}
		
		Assert.assertTrue(listOfColorsOfFilteredItems.containsAll(listOfSelectedColors));
	}
	
	public List<String> listOfSelectedColors() {
		
		List<String> selectedColorlist = new ArrayList();

		// Scroll the screen to color selections
		scrollToColorElement();
		WebDriverCommand.delay(2000L);
		// Color elements hex codes for orange, green and White
		String orangeColor = driver.findElement(By.xpath("//a[@title='Orange']//div[@class='colorsprite s-ref-text-link']"))
				.getCssValue("color");
		
		String whiteColor = driver.findElement(By.xpath("//a[@title='White']//div[@class='colorsprite s-ref-text-link']"))
				.getCssValue("color");
		
		String greenColor = driver.findElement(By.xpath("//a[@title='Green']//div[@class='colorsprite s-ref-text-link']"))
				.getCssValue("color");
		
		
		selectedColorlist.add(Color.fromString(orangeColor).asHex());
		selectedColorlist.add(Color.fromString(whiteColor).asHex());
		selectedColorlist.add(Color.fromString(greenColor).asHex());
		
		return selectedColorlist;
	}
	
	public void scrollToColorElement() {
		// Scroll the screen to color selections
		WebElement ele = driver.findElement(By.xpath("//div[@id='leftNavContainer']//ul[5]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	}

}

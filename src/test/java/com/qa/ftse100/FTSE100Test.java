package com.qa.ftse100;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FTSE100Test {
	
	private static WebDriver driver;
	private WebDriverWait wait;

	@BeforeAll
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());
	}

	@Test
	public void test() throws InterruptedException {
		driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100");
		wait = new WebDriverWait(driver, 10);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("acceptCookie")));
		driver.findElement(By.id("acceptCookie")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[@title='View risers']")));
		driver.findElement(By.xpath("//a[@title='View risers']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[@title='View fallers']")));
		driver.findElement(By.xpath("//a[@title='View fallers']")).click();
		

		// end wait
		Thread.sleep(5000);
	
	} 
		


	@AfterAll
	public static void cleanUp() {
		driver.quit();
		System.out.println("The selenium driver has been cleaned up!");
	}

	// Designed to return ChromeOptions to configure new ChromeDrivers in Selenium
	public static ChromeOptions chromeCfg() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions cOptions = new ChromeOptions();

		// Settings
		prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("network.cookie.cookieBehavior", 2);
		prefs.put("profile.block_third_party_cookies", true);

		// Create ChromeOptions to disable Cookies pop-up
		cOptions.setExperimentalOption("prefs", prefs);

		return cOptions;
	}
}

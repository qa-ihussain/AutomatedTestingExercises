package com.qa.demosite;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DemoSiteTest {

	private static WebDriver driver;

	@BeforeAll
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());
	}	
	
	
	@Test
	public void test() throws InterruptedException {
		driver.get("http://thedemosite.co.uk");
		String username = "guest";
		String password = "guest";
		
		// Go to add user
		driver.findElement(By.xpath("//a[@href='addauser.php']")).click();
		
		// creating username and password 
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='save']")).submit();
		
		// Go to login page
		driver.findElement(By.xpath("//a[@href='login.php']")).click();
		
		// enter login details
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Test Login']")).submit();

		// end wait
		Thread.sleep(5000);
	}
	
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
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
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
		String uName = "guest";
		String pwd = "guest";
		
		
		driver.findElement(By.xpath("//a[@href='addauser.php']")).click();
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(uName);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pwd);
		
		driver.findElement(By.xpath("//input[@value='save']")).submit();
		driver.findElement(By.xpath("//a[@href='login.php']")).click();
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(uName);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pwd);
		driver.findElement(By.xpath("//input[@value='Test Login']")).submit();
		
		
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
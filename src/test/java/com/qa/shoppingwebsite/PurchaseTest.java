package com.qa.shoppingwebsite;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PurchaseTest {
	private static WebDriver driver;
	private static WebElement targ;

	@BeforeAll
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());
	}

	@Test
	public void Purchase() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.className("login")).click();

		// WAIT for email box to load
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

		driver.findElement(By.id("email")).sendKeys("guest@mail.com");
		driver.findElement(By.id("passwd")).sendKeys("guest");
		driver.findElement(By.id("SubmitLogin")).click();

		targ = driver.findElement(By.id("search_query_top"));
		targ.sendKeys("dress");
		targ.submit();

		assertEquals("Search - My Store", driver.getTitle());

		List<WebElement> products = driver.findElements(By.className("product-container"));
		System.out.println(products.size());
		products.get(0).click(); // Click first product

		// On product page, add to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add_to_cart")));
		driver.findElement(By.id("add_to_cart")).click();

		// Proceed To Checkout button
		driver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?controller=order\']")).click();

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
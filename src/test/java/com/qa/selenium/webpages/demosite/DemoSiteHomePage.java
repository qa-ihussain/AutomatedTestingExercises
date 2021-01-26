package com.qa.selenium.webpages.demosite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoSiteHomePage {
	
	@FindBy(xpath = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")
	private WebElement addUserPage;
	
	@FindBy(xpath = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")
	private WebElement loginUserPage;
	
	public DemoSiteHomePage(WebDriver driver) {
		super();
	}
	
	public void navAddUser() {
		addUserPage.click();
	}
	
	public void navLogin() {
		loginUserPage.click();
	}

}

package com.qa.selenium.webpages.demosite.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoAddUsersPage {

	@FindBy(name = "username")
	private WebElement username;
	
	@FindBy(name = "password")
	private WebElement password;
	
	@FindBy(name = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")
	private WebElement submit;
	
	public DemoAddUsersPage(WebElement username, WebElement password, WebElement submit) {
		super();
	}
	
	public void signUp(String username, String password) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.submit.click();
	}
	
}

package com.sc.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects {

	public HomePageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(text(),'Begin')]")
	private WebElement letsBeginBtn;

	@FindBy(xpath = "//input[@type='email']")
	private WebElement emailIdTextbox;

	@FindBy(xpath = "//input[@data-testid='signup_password']")
	private WebElement passwordTextbox;

	@FindBy(xpath = "//div[contains(@class,'Dropdown-control postedby_selector')]")
	private WebElement createProfileForDropDown;

	@FindBy(xpath = "//div[contains(@class,'Dropdown-option')]")
	private List<WebElement> createProfileForDropDownOptions;
	
	@FindBy(xpath = "//*[contains(@id,'gender_female')]")
	private WebElement femaleRdioOption;
	
	@FindBy(xpath = "//input[@id='gender_male']")
	private WebElement maleRdioOption;
	
	
	@FindBy(xpath = "//button[contains(text(),'Next')]")
	private WebElement nextBtn;
	
	
	@FindBy(xpath = "//form//div[contains(text(),'Marathi')]")
	private WebElement communityName;

	public WebElement getLetsBeginBtn() {
		return letsBeginBtn;
	}

	public WebElement getEmailIdTextbox() {
		return emailIdTextbox;
	}

	public WebElement getPasswordTextbox() {
		return passwordTextbox;
	}

	public WebElement getCreateProfileForDropDown() {
		return createProfileForDropDown;
	}

	public List<WebElement> getCreateProfileForDropDownOptions() {
		return createProfileForDropDownOptions;
	}
	
	public WebElement getFemaleRdioOption(){
		return femaleRdioOption;
	}
	
	public WebElement getMaleRdioOption(){
		return maleRdioOption;
	}
	
	public WebElement getNextBtn(){
		return nextBtn;
	}

	public WebElement getCommunityNameFromDropDown(){
		return communityName;
	}
}

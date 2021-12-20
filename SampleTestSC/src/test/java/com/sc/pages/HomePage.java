package com.sc.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.TimeoutException;

import com.sc.pageObjects.HomePageObjects;
import com.sc.utilities.enums.SCCommonOptions;

public class HomePage {
	WebDriver driver;
	HomePageObjects homePageObjects;
	File file;
	FileInputStream fileInput = null;
	FileOutputStream fileOutput = null;
	Properties prop;
	Actions actions;
	SoftAssert softAssert;
	WebDriverWait wait;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		homePageObjects = new HomePageObjects(driver);
		actions = new Actions(driver);
		softAssert = new SoftAssert();
		
	}

	public void launchHomePage() {
		
		//to read URL/text from property file
		File propFile = new File(
				System.getProperty("user.dir") + "/src/test/resources/propertyFile/configDetails.properties");
		try {
			fileInput = new FileInputStream(propFile);
			prop = new Properties();
			prop.load(fileInput);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		//to wait till page gets loaded
		waitForElementToBeClickable(homePageObjects.getLetsBeginBtn(), 20);

	}

	public void clickLetsBeginBtn() {
		waitForElementToBeClickable(homePageObjects.getLetsBeginBtn(), 20);
		homePageObjects.getLetsBeginBtn().click();
	}

	public void enterUserEmailAndPwdDetails(String userId) throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		try {
			FileReader reader = new FileReader("./src/test/resources/TestData/UserDetails.json");
			
			//Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray usersList = (JSONArray) obj;
            System.out.println("Users List-> "+usersList);
            for(int i=0;i<usersList.size();i++) {
                JSONObject users = (JSONObject) usersList.get(i);
                System.out.println("Users -> "+users);//This prints every block - one json object
              //  JSONObject user = (JSONObject) users.get();
                if(users.containsValue(userId)){
               
                String emailId = (String) users.get("EmailId");
                String password = (String) users.get("password");
               
                enterEmailId(emailId);
        		enterPwd(password);
                }
            }
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		

	}
	public void enterEmailId(String id){
		waitForElementToBeClickable(homePageObjects.getLetsBeginBtn(), 20);
   		homePageObjects.getEmailIdTextbox().sendKeys(id);
	}
	public void enterPwd(String pwd) {
		// waitForElementToBeClickable(homePageObjects.getLetsBeginBtn(), 20);
		homePageObjects.getPasswordTextbox().sendKeys(pwd);

	}

	public void clickCreateProfileForDropDown() {
		homePageObjects.getCreateProfileForDropDown().click();
	}

	public void selectValuefromProfileforDrpDwn(SCCommonOptions profileFor) {

		clickCreateProfileForDropDown();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> allOptionsElement = new ArrayList<WebElement>();
		allOptionsElement.addAll(homePageObjects.getCreateProfileForDropDownOptions());
		for (WebElement ele : allOptionsElement) {

			if (ele.getText().equals(profileFor.getText())) {
				ele.click();
				break;
			}
		}
		// actions.click(Arrays.asList(homePageObjects.getCreateProfileForDropDownOptions()),
		// Arrays.asList("self"), 0);
		
	}

	public void waitForElementToBeClickable(WebElement elementVal, int secTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// new WebDriverWait(driver,
		// Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(elementVal));
		wait.until(ExpectedConditions.visibilityOf(elementVal));
		wait.until(ExpectedConditions.elementToBeClickable(elementVal));
	}

	public void selectGender(SCCommonOptions gender) {
	//	wait.until(ExpectedConditions.visibilityOf(homePageObjects.getFemaleRdioOption()));
		if(homePageObjects.getFemaleRdioOption().isDisplayed()){
	
		waitForElementToBeClickable(homePageObjects.getFemaleRdioOption(), 10);
		switch (gender) {
		case GENDER_FEMALE:
			homePageObjects.getFemaleRdioOption().click();
			break;
		case GENDER_MALE:
			homePageObjects.getMaleRdioOption().click();
			break;
		default:
			break;
		}
		}
	}

	public void clickNextBtn() {
		homePageObjects.getNextBtn().click();
	}

	public void verifyCommunityNameAsPerWebSiteName(String tagName, boolean expectedVal) {
		String CommName = homePageObjects.getCommunityNameFromDropDown().getText();
		System.out.println(CommName);
		boolean actualVal = tagName.contains(CommName);
		softAssert.assertEquals(actualVal, expectedVal);
		softAssert.assertAll();
	}
}

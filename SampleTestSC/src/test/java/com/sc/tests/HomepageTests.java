package com.sc.tests;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.sc.configuration.BaseTestWeb;
import com.sc.pages.HomePage;
import com.sc.utilities.enums.SCCommonOptions;

public class HomepageTests extends BaseTestWeb{
	
	@Test(enabled = true, priority =1)
	public void Tc_01() throws IOException, ParseException {
		HomePage home = new HomePage(driver);
		
		home.launchHomePage();
		String websiteName = driver.getTitle();
		home.clickLetsBeginBtn();
		home.enterUserEmailAndPwdDetails("User_002");
		home.selectValuefromProfileforDrpDwn(SCCommonOptions.FRIEND);
		home.selectGender(SCCommonOptions.GENDER_FEMALE);
		
		home.clickNextBtn();
		home.verifyCommunityNameAsPerWebSiteName(websiteName, true);
	}
}

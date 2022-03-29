package com.qa.pages;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CheckOut extends BaseTest{

	@AndroidFindBy(xpath="//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: COMPLETE!\"]/android.view.ViewGroup/android.widget.TextView[1]")
	private MobileElement confirmationMsg;
	
	public String getconfirmationText() {
		String confmMessage=getText(confirmationMsg);
		return confmMessage;
	}
	
	
	
}

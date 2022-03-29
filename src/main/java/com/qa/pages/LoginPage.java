package com.qa.pages;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest{

	@AndroidFindBy(accessibility="test-Username")
	private MobileElement userNameTxtField;
	
	@AndroidFindBy(accessibility="test-Password")
	private MobileElement passwordTxtField;
	
	@AndroidFindBy(accessibility="test-LOGIN")
	private MobileElement loginBtn;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
	private MobileElement errorMsg;
	
	
	public void enterUserName(String userName) {
		clear(userNameTxtField);
		sendKeys(userNameTxtField, userName);
	}
	
	public void enterPassword(String password) {
		clear(passwordTxtField);
		sendKeys(passwordTxtField, password);
	}
	
	public void pressLogin() {
		click(loginBtn);
	}
	
	public void login(String userName, String password) {
		enterUserName(userName);
		enterPassword(password);
		pressLogin();
	}
	
	public String getErrorText() {
		String errMessage=getText(errorMsg);
		return errMessage;
	}

	
	
}

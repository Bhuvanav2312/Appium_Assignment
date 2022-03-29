package com.qa.pages;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductPage extends BaseTest{

	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
	private MobileElement productTitleText;
	
	@AndroidFindBy(xpath="(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
	private MobileElement SLBTitle;
	
	@AndroidFindBy(xpath="(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
	private MobileElement SLBPrice;
	
	@AndroidFindBy(xpath="(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]")
	private MobileElement addToCart;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.view.ViewGroup")
	private MobileElement viewProduct;
	
	@AndroidFindBy(accessibility="test-CHECKOUT")
	private MobileElement checkOutBtn;
	
	@AndroidFindBy(accessibility="test-First Name")
	private MobileElement firstNameTxtField;
	
	@AndroidFindBy(accessibility="test-Last Name")
	private MobileElement lastNameTxtField;
	
	@AndroidFindBy(accessibility="test-Zip/Postal Code")
	private MobileElement postalCodeTxtField;
	
	@AndroidFindBy(accessibility="test-CONTINUE")
	private MobileElement continueBtn;
	
	@AndroidFindBy(accessibility="test-FINISH")
	public MobileElement finishBtn;
	
	@AndroidFindBy(accessibility="test-REMOVE")
	public MobileElement removeBtn;
	
	@AndroidFindBy(accessibility="test-CONTINUE SHOPPING")
	public MobileElement continueShoppingBtn;
	
	
	
	public String getTitle() {
		String title=getText(productTitleText);
		return title;
	}
	
	public String getSLBTitle() {
		String title=getText(SLBTitle);
		return title;
	}
	
	public String getSLBPrice() {
		String title=getText(SLBPrice);
		return title;
	}
	
	public void pressSLBTitle(){
		click(SLBTitle);
	}
	
	public void clickAddToCartBtn() {
		click(addToCart);
	}
	
	public void clickViewProduct() {
		click(viewProduct);
	}
	
	public void clickCheckOutBtn() {
		click(checkOutBtn);
	}
	
	public void enterFirstName(String firstName) {
		clear(firstNameTxtField);
		sendKeys(firstNameTxtField, firstName);
	}
	
	public void enterLastName(String lastName) {
		clear(lastNameTxtField);
		sendKeys(lastNameTxtField, lastName);
	}
	
	public void enterPostalCode(String postalCode) {
		clear(postalCodeTxtField);
		sendKeys(postalCodeTxtField, postalCode);
	}
	
	public void pressContinueBtn() {
		click(continueBtn);
	}
	
	public void login(String firstName, String lastName, String postalCode) {
		enterFirstName(firstName);
		enterLastName(lastName);
		enterPostalCode(postalCode);
		pressContinueBtn();
	}
	
	public void pressFinishBtn() {
		click(finishBtn);
	}
	
	public void pressRemoveBtn() {
		click(removeBtn);
	}
	
	public void pressContinueShoppingBtn() {
		click(continueShoppingBtn);
	}
	
	
	
}

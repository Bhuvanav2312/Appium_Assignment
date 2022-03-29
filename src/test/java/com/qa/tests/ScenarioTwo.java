package com.qa.tests;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.time.Duration;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pages.CheckOut;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailPage;
import com.qa.pages.ProductPage;
import com.qa.pages.CheckOut;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import com.qa.BaseTest;

public class ScenarioTwo extends BaseTest{

	LoginPage loginPage;
	ProductPage productPage;
	ProductDetailPage productDetailPage;
	CheckOut checkOut;
	InputStream details;
	JSONObject loginUsers;
	
	@BeforeClass
	public void beforeClass() throws FileNotFoundException {
		
		details=new FileInputStream("C:\\Users\\ibmadmin\\eclipse-workspace\\SauceLabTools\\src\\test\\resources\\Data\\data.json");
		
		JSONTokener jsonToken=new JSONTokener(details);
		loginUsers=new JSONObject(jsonToken);
		closeApp();
		launchApp();		
	}
	
	@BeforeMethod
	public void beforeMethod(Method m) {
		System.out.println("**********Starting Test:"+m.getName()+" *************");
		
		loginPage=new LoginPage();
		productPage=new ProductPage();
		checkOut=new CheckOut();
	}
	
	@Test
	public void verifyAddingAndRemovingOptionsFromCartAndCheckout() {
		
		loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
		loginPage.pressLogin();
		
		System.out.println("Logged in Successfully");

		productPage.clickAddToCartBtn();
		System.out.println("Added Product to Cart");

		productPage.clickViewProduct();
		productPage.pressRemoveBtn();
		System.out.println("Viewing removing product");

		productPage.pressContinueShoppingBtn();
		System.out.println("Continuing the shopping");

		productPage.clickAddToCartBtn();
		System.out.println("Added Product to Cart");

		productPage.clickViewProduct();
		productPage.clickCheckOutBtn();
		System.out.println("Viewing Product and continuing to Checkout");

		productPage.enterFirstName("abc");
		productPage.enterLastName("def");
		productPage.enterPostalCode("40087");
		productPage.pressContinueBtn();
		
		TouchAction t = new TouchAction(driver);
		Dimension size = driver.manage().window().getSize();
        int X = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
		for (int i = 0; i < 5; i++) {

			System.out.println(i);
			try {
				if (productPage.finishBtn.isDisplayed()) {
					break;
				}

			} catch (Exception e) {
				t.press(PointOption.point(X, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
						.moveTo(PointOption.point(X, endY)).release().perform();

			}

		}
		
		productPage.pressFinishBtn();
		System.out.println("Confirmed Order");
		
		 String orderConfirmationText=checkOut.getconfirmationText(); 
		 String expectedConfirmationText="THANK YOU FOR YOU ORDER";

		 Assert.assertEquals(orderConfirmationText, expectedConfirmationText);
	}
	
	
	@AfterClass
	public void afterClass() throws IOException {
		details.close();
	}
	
}

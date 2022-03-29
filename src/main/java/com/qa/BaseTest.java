package com.qa;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseTest {

	public static AppiumDriver driver;
	protected static Properties prop;
	InputStream inputStream;
	private static AppiumDriverLocalService server;

	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@BeforeSuite
	@Parameters({ "platformName", "udid", "deviceName" })
	public void setup(String platformName, String udid, String deviceName) throws IOException {

		server = getAppiumServerDefault();

		if (!checkIfAppiumServerIsRunning(4723)) {
			server.start();
			server.clearOutPutStreams();
			System.out.println("Appium Server Started");
		} else {
			System.out.println("Appium Server is already running");
		}

		prop = new Properties();
		inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
		prop.load(inputStream);

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		caps.setCapability(MobileCapabilityType.UDID, udid);
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("andriodAutomationName"));
		caps.setCapability(MobileCapabilityType.APP, prop.getProperty("andriodAppLocation"));
		caps.setCapability("appActivity", prop.getProperty("andriodAppActivity"));
		caps.setCapability("appPackage", prop.getProperty("andriodAppPackage"));

		URL url = new URL(prop.getProperty("appiumURL"));
		driver = new AndroidDriver(url, caps);

	}

	private boolean checkIfAppiumServerIsRunning(int port) {
		boolean flag = false;
		ServerSocket socket;

		try {
			socket = new ServerSocket(port);
		} catch (Exception e) {
			flag = true;
		} finally {
			socket = null;
		}

		return flag;
	}

	public AppiumDriverLocalService getAppiumServerDefault() {
		return AppiumDriverLocalService.buildDefaultService();

	}

	@AfterSuite
	public void tearDown() {
		server.stop();

	}

	void waitForVisibility(MobileElement e) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(e));

	}

	public void sendKeys(MobileElement element, String userName) {
		waitForVisibility(element);
		element.sendKeys(userName);
	}

	public void clear(MobileElement element) {
		waitForVisibility(element);
		element.clear();
	}

	public void click(MobileElement element) {
		waitForVisibility(element);
		element.click();
	}

	public String getText(MobileElement e) {
		String str = null;
		str = getAttribute(e, "text");
		return str;
	}

	private String getAttribute(MobileElement e, String txt) {
		waitForVisibility(e);
		return e.getAttribute(txt);
	}

	public void closeApp() {
		((InteractsWithApps) driver).closeApp();
	}

	public void launchApp() {
		((InteractsWithApps) driver).launchApp();
	}
	
}

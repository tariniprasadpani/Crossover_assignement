package com.crossover.e2e;

import java.io.File;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.Properties;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import com.GmailPages.GmailPage;
import com.GmailPages.LoginPage;

public class GMailTest extends TestCase {
	public static WebDriver driver;
	public static Properties properties = new Properties();

	public void setUp() throws Exception {
		properties.load(new FileReader(new File("test.properties")));
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\tarinip\\eclipse-workspace\\Crossover_assignement\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	/*public void tearDown() throws Exception {
		driver.quit();
	}*/

	@Test
	public void testSendEmail() throws Exception {
		GmailPage gmailPage = new GmailPage();
		LoginPage loginPage = new LoginPage();
		loginPage.login(properties.getProperty("URL"), properties.getProperty("username"),
				properties.getProperty("password"));
		gmailPage.testSendEmail();
	}
}

package com.GmailPages;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.crossover.e2e.GMailTest;



public class LoginPage {

//	Properties properties = new Properties();
	
	/* public LoginPage(WebDriver driver){
			this.driver=driver;
			PageFactory.initElements(driver, this);	
		}*/
	 
	
	 public void login(String url, String Username, String password) throws InterruptedException {
		 
		    
		    GMailTest.driver.get(url); 
		    GMailTest.driver.manage().window().maximize();
		    GMailTest.driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
			WebElement userElement = GMailTest.driver.findElement(By.id("identifierId"));
			userElement.sendKeys(Username);

			GMailTest.driver.findElement(By.id("identifierNext")).click();

			Thread.sleep(1000);

			WebElement passwordElement = GMailTest.driver.findElement(By.name("password"));
			passwordElement.sendKeys(password);
			GMailTest.driver.findElement(By.id("passwordNext")).click();

			Thread.sleep(1000);
	 }

	
	
}

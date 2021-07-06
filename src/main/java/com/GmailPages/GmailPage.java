package com.GmailPages;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.crossover.e2e.GMailTest;

public class GmailPage {

	 
	 public static void setClipboardData(String string) {
			StringSelection stringSelection = new StringSelection(string);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}

		public static void uploadFile(String fileLocation) {
			try {
				setClipboardData(fileLocation);
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}
	
public void testSendEmail() throws Exception {
		

		WebElement composeElement = GMailTest.driver.findElement(By.xpath(".//*[text()='Compose']"));
		composeElement.click();

		GMailTest.driver.findElement(By.name("to")).clear();
		GMailTest.driver.findElement(By.name("to")).sendKeys(String.format("%s@gmail.com", GMailTest.properties.getProperty("username")));
		WebElement subjectbox = GMailTest.driver.findElement(By.name("subjectbox"));
		subjectbox.clear();
		subjectbox.sendKeys(GMailTest.properties.getProperty("Mail_Subject")); 

		WebElement messagebody = GMailTest.driver.findElement(By.xpath(".//div[@aria-label='Message Body']"));
		messagebody.clear();
		messagebody.sendKeys(GMailTest.properties.getProperty("MessageBody")); 

		WebElement attachFile_button = GMailTest.driver.findElement(By.xpath("//div[@aria-label='Attach files']"));
		attachFile_button.click();
		Thread.sleep(3000);
		uploadFile(String.format("D:\\Tarini Back up files\\Personal\\%s", GMailTest.properties.getProperty("attachment")));
		Thread.sleep(3000);
		GMailTest.driver.findElement(By.xpath("//*[@role='button' and text()='Send']")).click();
		Thread.sleep(10000);

		List<WebElement> unreademail = GMailTest.driver.findElements(By.xpath("//*[@class='zF']"));
		List<WebElement> unreademail_subject = GMailTest.driver.findElements(By.xpath("//*[@class='bqe']"));

		String MyMailerName = GMailTest.properties.getProperty("mailerName");
		String MyMailerEmail = String.format("%s@gmail.com", GMailTest.properties.getProperty("username"));
		String Mail_subject= GMailTest.properties.getProperty("Mail_Subject");
		String Messagebody=GMailTest.properties.getProperty("MessageBody");
		String attachmentname=GMailTest.properties.getProperty("attachment");
		
		//Checking the mail is received or not after search
		for (WebElement emails: unreademail) {
				
					if (emails.getAttribute("name").equals(MyMailerName) && emails.getAttribute("email").equals(MyMailerEmail) ) {
						System.out.println("Yes we have got mail form: " + MyMailerEmail);
						break;
					} else {
						System.out.println("No mail form " + MyMailerEmail);
					}
			
			}
		
		//checking the content of the email
		try {
			for(int k=0;k<unreademail_subject.size();k++) {
				if(unreademail_subject.get(k).getText().equals(Mail_subject)) {
					System.out.println(unreademail_subject.get(k).getText()+"::"+k);
					System.out.println("Subject matches");
					break;
				}
				else {
					System.out.println("Subject didn't match with the subject of the email sent");
				}
				
			}
		} catch (Exception e3) {
			System.out.println(e3.getMessage());
		}
		
		
		try {
			String ReceivedEmail_address=GMailTest.driver.findElement(By.xpath("(.//*[@class='zF'])[1]")).getAttribute("email");
			if(ReceivedEmail_address.equals(MyMailerEmail)) {
				System.out.println("EmailID matches");
			}
			else {
				System.out.println("EmailID didn't mactch with the emailID who has sent the email");
			}
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			Thread.sleep(8000);
			List<WebElement> EmailBody=GMailTest.driver.findElements(By.xpath(".//*[@class='y2']"));
			for(int k=0;k<EmailBody.size();k++) {
				if(EmailBody.get(k).getText().contains(Messagebody)) {
					System.out.println(EmailBody.get(k).getText()+"::"+k);
					System.out.println("EmailBody matches");
					break;
			}
			else {
				System.out.println("EmailBody didn't mactch with the EmailBody what has sent in the email");
				System.out.println(EmailBody.get(k).getText());
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			List <WebElement> attachmentName=GMailTest.driver.findElements(By.xpath(".//*[@class='brg']"));
			for(int k=0;k<attachmentName.size();k++) {
					if(attachmentName.get(k).getText().equals(attachmentname)) {
						System.out.println(attachmentName.get(k).getText()+"::"+k);
						System.out.println("attachmentname matches");
						break;
			}
			else {
				System.out.println("attachmentname didn't match with the attachmentname what has sent in the email");
			}
				}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		
		}
	
}

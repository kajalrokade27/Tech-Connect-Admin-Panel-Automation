package com.TechConnect.Base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.UserPOM.LoginPage;
import com.tech_Connect.Action.ActionClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UserBaseClass extends BaseDriver {
	
	public boolean performValidLogin = false; // default true

	@BeforeClass
	public void preCondition() throws IOException, InterruptedException
	{
		String browser = GetPropertyData.propData("browser");
		String web_url = GetPropertyData.propData("userApp_url");
		String email = GetPropertyData.propData("userApp_email");
		String password = GetPropertyData.propData("userApp_password");
			
		if(browser.equals("chrome"))
		{
			//open the browser
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			
		}
		if(browser.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
		 driver = new EdgeDriver();
		}
		if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		//maximize the window
       driver.manage().window().maximize();
       //implicit waiting condition
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       //Enter into web application
       driver.navigate().to(web_url);
   
        //Login
       if (performValidLogin) {
           LoginPage lp = new LoginPage(driver);
      	  lp.login_link.click();
      	  lp.email_field.sendKeys(email);
      	  lp.password_field.sendKeys(password);
      	  lp.submit_button.click();
      	  System.out.println("Valid login performed from base class.");
       }
       else {
           System.out.println("Login skipped from base class (test will handle it).");
       }

 		
 		Thread.sleep(2000); // Wait for the page to load after login
       }
	
//	@BeforeMethod
//	public void login() throws IOException, InterruptedException
//	{
//		String email = GetPropertyData.propData("email");
//		String password = GetPropertyData.propData("password");
//		
//	  LoginPage lp = new LoginPage(driver);
//	  lp.login_link.click();
//	  lp.email_field.sendKeys(email);
//	  lp.password_field.sendKeys(password);
//	  lp.submit_button.click();
//	  
//		
//		Thread.sleep(2000); // Wait for the page to load after login
//	}
//	
//	@AfterMethod
//	public void logout() throws InterruptedException
//	{
//		// Assuming there's a logout link or button to click
//		LoginPage lp = new LoginPage(driver);
//		Actions act = new Actions(driver);
//		act.moveToElement(lp.user_icon).build().perform();
//		ActionClass.click(lp.logout_link);
//		ActionClass.click(lp.confirm_logout_button);
//		Thread.sleep(2000); // Wait for the logout process to complete
//		
//		
//	}
	
	
	@AfterClass
	public void postCondition() throws InterruptedException
		{
		
	
		     driver.quit();
	     }
	     
	 
}

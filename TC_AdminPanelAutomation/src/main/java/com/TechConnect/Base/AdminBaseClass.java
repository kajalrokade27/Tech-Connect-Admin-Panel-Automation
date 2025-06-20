package com.TechConnect.Base;

import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.TechConnect.FileUtility.GetPropertyData;
import com.tc.AdminPOM.LoginPage;
import com.tech_Connect.Action.ActionClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AdminBaseClass extends BaseDriver
{
	@BeforeClass
	public void preCondition() throws IOException, InterruptedException
	{
		String browser = GetPropertyData.propData("browser");
		String web_url = GetPropertyData.propData("admin_url");
		String email = GetPropertyData.propData("admin_email");
		String password = GetPropertyData.propData("admin_password");
			
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
       LoginPage lp = new LoginPage(driver);
      ActionClass.enterText(lp.email_field, email);
      ActionClass.enterText(lp.password_field, password);
      ActionClass.click(lp.submit_button);
	  
	   
      
      
       
	
		
	}
//	@AfterClass
//	public void postCondition()
//		{
//		driver.quit();
//	}
	
}

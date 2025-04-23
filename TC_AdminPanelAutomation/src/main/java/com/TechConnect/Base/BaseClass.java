package com.TechConnect.Base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.POM.LoginPage;
import com.tech_Connect.Action.ActionClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass extends BaseDriver
{
	@BeforeMethod
	public void preCondition() throws IOException, InterruptedException
	{
		String browser = GetPropertyData.propData("browser");
		String web_url = GetPropertyData.propData("url");
				
		
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
       lp.email_field.sendKeys("admin@tech-connect.space");
       lp.password_field.sendKeys("Admin@123");
       lp.submit_button.click();
       
      
      
      
       
	
		
	}
//	@AfterMethod
//	public void postCondition()
//		{
//		driver.quit();
//	}
	
}

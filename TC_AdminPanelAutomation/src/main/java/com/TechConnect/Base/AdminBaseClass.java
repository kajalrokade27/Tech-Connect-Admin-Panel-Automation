package com.TechConnect.Base;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.tc.AdminPOM.LoginPage;
import com.tech_Connect.Action.ActionClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AdminBaseClass extends BaseDriver
{
	@BeforeClass
	public void preCondition() throws IOException, InterruptedException
	{
	    String browser = GetPropertyData.propData("browser").trim();
	    String web_url = GetPropertyData.propData("admin_url");
	    String email = GetPropertyData.propData("admin_email");
	    String password = GetPropertyData.propData("admin_password");

	    if(browser.equalsIgnoreCase("chrome"))
	    {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	    }
	    else if(browser.equalsIgnoreCase("edge"))
	    {
	        WebDriverManager.edgedriver().setup();
	        driver = new EdgeDriver();
	    }
	    else if(browser.equalsIgnoreCase("firefox"))
	    {
	        WebDriverManager.firefoxdriver().setup();
	        driver = new FirefoxDriver();
	    }
	    else
	    {
	        throw new RuntimeException("Unsupported browser: " + browser);
	    }
        driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	    driver.navigate().to(web_url);

	    LoginPage lp = new LoginPage(driver);
	    ActionClass.enterText(lp.email_field, email);
	    ActionClass.enterText(lp.password_field, password);
	    ActionClass.click(lp.submit_button);
	    Reporter.log("Admin login successful", true);
	}
	
	@AfterClass
	public void postCondition()
	{
		driver.quit();
	}

}

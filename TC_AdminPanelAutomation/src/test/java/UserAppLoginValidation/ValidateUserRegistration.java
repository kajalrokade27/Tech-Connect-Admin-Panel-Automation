package UserAppLoginValidation;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.TechConnect.Base.UserBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.UserPOM.HomePage;
import com.TechConnect.UserPOM.LoginPage;
import com.tech_Connect.Action.ActionClass;

public class ValidateUserRegistration extends UserBaseClass 
{
	

	// DataProvider to get multiple data sets from Properties file

	@DataProvider(name = "invalidLoginData")
	public Object[][] getInvalidLoginData() throws IOException {
	    List<Object[]> dataList = new ArrayList<>();
	    String[] keys = {"Valid"};
	    for (String key : keys) {
	        String value = GetPropertyData.propData(key);
	        String[] parts = value.split(":");
	        if (parts.length == 6) {
	            dataList.add(parts); // add all 6 fields
	        }
	    }
	    return dataList.toArray(new Object[0][0]);
	}


    // ✅ NEW: Test that runs with multiple invalid credentials

@Test(dataProvider = "invalidLoginData")
    public void verifyLoginWithInvalidCredentials(String name, String email, String companyName, String jobTitle, String password, String confirm_pass) throws InterruptedException
{
	LoginPage lp = new LoginPage(driver);
	HomePage hp = new HomePage(driver);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(lp.login_link));
	ActionClass.click(lp.login_link);

    ActionClass.click(lp.Register_link);
    
    ActionClass.enterText(lp.register_fields.get(0), name);
    ActionClass.enterText(lp.register_fields.get(1), email);
    ActionClass.enterText(lp.register_fields.get(2), companyName);
    ActionClass.scrollToElement(lp.register_fields.get(1)); // Scroll to the job title field
    ActionClass.enterText(lp.register_fields.get(3), jobTitle);
    ActionClass.enterText(lp.register_fields.get(4), password);
    ActionClass.enterText(lp.register_fields.get(5), confirm_pass);
    driver.switchTo().frame(0); // Switch to the reCAPTCHA iframe
    ActionClass.click(lp.recaptcha_checkbox);
    
    driver.switchTo().defaultContent(); // Switch back to the main content
    ActionClass.click(hp.signUp_button);
    Thread.sleep(7000);
    
    
}
}

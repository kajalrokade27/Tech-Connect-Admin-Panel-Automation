package adminLoginPageValidationScripts;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.tc.AdminPOM.LoginPage;
import com.tech_Connect.Action.ActionClass;
@Listeners(com.TechConnect.Listeners.ListenersClass.class)
public class LoginValidations extends AdminBaseClass
{
  @Test
  public void loginValidation() throws InterruptedException
  {
	//Wait for the page to load
	   Thread.sleep(2000);
	   
	   //Verify the title of the page
	   String expectedTitle = "Tech Connect | Admin panel";
	   String actualTitle = driver.getTitle();
	   if(actualTitle.equals(expectedTitle))
	   {
		   System.out.println("Login successful, Title verified: " + actualTitle);
	   }
	   else
	   {
		   System.out.println("Login failed, Title mismatch: " + actualTitle);
	   }
     
  }
  @Test
  public void loginWithInvalidCredentials() throws InterruptedException, IOException
  {
     driver.navigate().back();
    
      // Attempt login with invalid credentials
      LoginPage lp = new LoginPage(driver);
      ActionClass.enterText(lp.email_field, GetPropertyData.propData("Ad__Invalid_email")); 
      ActionClass.enterText(lp.password_field, GetPropertyData.propData("Ad__Invalid_password"));
      ActionClass.click(lp.submit_button);

      Thread.sleep(2000);
      String expectedTitle = "Tech Connect | Admin panel";
      String actualTitle = driver.getTitle();
      if(!actualTitle.equals(expectedTitle))
      {
          System.out.println("Invalid login correctly failed. Title: " + actualTitle);
      }
      else
      {
          System.out.println("Invalid login test failed. Unexpectedly logged in.");
      }
  }


}

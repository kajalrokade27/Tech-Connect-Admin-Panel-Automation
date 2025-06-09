package adminLoginPageValidationScripts;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TechConnect.Base.AdminBaseClass;
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
}

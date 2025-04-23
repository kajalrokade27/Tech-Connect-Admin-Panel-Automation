package UsersValidationScripts;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TechConnect.Base.BaseClass;
import com.TechConnect.JavaUtility.RandomNumClass;
import com.TechConnect.POM.UsersPage;
import com.tech_Connect.Action.ActionClass;
@Listeners(com.TechConnect.Listeners.ListenersClass.class)
public class UsersModuleValidation extends BaseClass
{
     @Test(priority=1)
     public void addUsersWithValidInput() throws EncryptedDocumentException, IOException
     {
    	 UsersPage up = new UsersPage(driver);
    	 up.users.click();
    	 ActionClass.waitUptoVisible(up.add_users);
    	 up.add_users.click();
    	 
    	 up.u_name.sendKeys("kajal");
    	 up.u_email.sendKeys("kajal"+RandomNumClass.randomNum()+"@gmail.com");
    	 up.u_company.sendKeys("metapercept");
    	 up.u_jobTitle.sendKeys("QA");
    	 
    	 up.submitButton.click();
    	 
    	 assertTrue(up.success_msg.isDisplayed());
    	 Reporter.log("User is added successfully with valid data");
    	 
    	 up.delete_user.click();
     }
     
     @Test(priority=2)
     public void addUserWithInvalidInput()
     {
    	 UsersPage up = new UsersPage(driver);
    	 up.users.click();
    	 ActionClass.waitUptoVisible(up.add_users);
    	 up.add_users.click();
    	 
    	 up.u_name.sendKeys("kajal");
    	 up.u_email.sendKeys("kajalmail.com");
    	 up.u_company.sendKeys("metapercept");
    	 up.u_jobTitle.sendKeys("QA");
    	 
    	 up.submitButton.click();
    	 
    	 try 
    	 {
			assertTrue(up.success_msg.isDisplayed());
		} 
    	 catch (Exception e) {
			Reporter.log("User is not added with invalid data");
		}
    	
     }
}

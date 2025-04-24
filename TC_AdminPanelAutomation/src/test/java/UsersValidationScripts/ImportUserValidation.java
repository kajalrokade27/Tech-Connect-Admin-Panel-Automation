package UsersValidationScripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TechConnect.Base.BaseClass;
import com.TechConnect.POM.UsersPage;
@Listeners(com.TechConnect.Listeners.ListenersClass.class)
public class ImportUserValidation extends BaseClass
{
   @Test
   public void importUserValidtion() throws InterruptedException, AWTException
   {
	   UsersPage up = new UsersPage(driver);
	   up.users.click();
	   up.import_user.click();
	   up.upload_file.click();
	   
	    StringSelection filePathSelection =new StringSelection("C:\\Users\\Admin\\git\\TechConnect\\TC_AdminPanelAutomation\\src\\test\\resources\\TC_UserDetails.xlsx");

	     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathSelection, null);
	     Thread.sleep(3000);
	     
	     Robot rb = new Robot();
	     rb.keyPress(KeyEvent.VK_CONTROL);
	     rb.keyPress(KeyEvent.VK_V);
	     rb.keyRelease(KeyEvent.VK_V);
	     rb.keyRelease(KeyEvent.VK_CONTROL);
	     
	     Thread.sleep(1000);
	     rb.keyPress(KeyEvent.VK_ENTER);
	     rb.keyRelease(KeyEvent.VK_ENTER);
	     Thread.sleep(5000);
	     
	     up.submit_button.click();
	     
	     Reporter.log("Users are added successfully from excel file",true);
   }
}

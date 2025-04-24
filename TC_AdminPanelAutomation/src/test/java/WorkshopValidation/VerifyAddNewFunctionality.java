package WorkshopValidation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TechConnect.Base.BaseClass;
import com.TechConnect.JavaUtility.DateClass;
import com.TechConnect.POM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;
@Listeners(com.TechConnect.Listeners.ListenersClass.class)
public class VerifyAddNewFunctionality extends BaseClass
{
   @Test 
   public void validateAddNew() throws InterruptedException, AWTException
   {
	   WorkshopPage wp = new WorkshopPage(driver);
	   wp.EventDropdown.click();
	   wp.workshop.click();
	   Thread.sleep(2000);
	   wp.addnew_button.click();
	   Thread.sleep(2000);
	   Select se1 = new Select(wp.event_cat_dropdown);
	   se1.selectByIndex(0);
	   wp.name_field.sendKeys("AI Automation Testing");
	   
	   Select se2 = new Select(wp.event_type_dropdown);
	   se2.selectByIndex(0);
	   
	   Select se3 = new Select(wp.event_scope_dropdown);
	   se3.selectByIndex(1);
	   
	   
	   Select se4 = new Select(wp.powered_by_dropdown);
	   se4.selectByVisibleText("metR");
	   
	   wp.description_field.sendKeys("AI workshop");
	   Thread.sleep(2000);
	   ActionClass.scrollToElement(wp.location_field);
	   Thread.sleep(2000);
	   wp.location_field.sendKeys("Pune");
	   
	   wp.start_date.click();
	  wp.apr25.click();
	  
	  wp.end_date.click();
	  wp.apr30.click();
	  Thread.sleep(2000);
	  wp.related_topics_dropdown.click();
	  Thread.sleep(2000);
	 // wp.related_topics_dropdown.sendKeys("AI");
	  
	  Thread.sleep(2000);
	  Robot rb = new Robot();
	  rb.keyPress(KeyEvent.VK_TAB);
	 // rb.keyPress(KeyEvent.VK_ENTER);
	//  rb.keyRelease(KeyEvent.VK_ENTER);
	  
	  wp.workshop_image.click();
	  StringSelection filePathSelection =new StringSelection("C:\\Users\\Admin\\git\\TechConnect\\TC_AdminPanelAutomation\\src\\test\\resources\\workshopImage.jpg");

	     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathSelection, null);
	     Thread.sleep(3000);
	     
	    
	     rb.keyPress(KeyEvent.VK_CONTROL);
	     rb.keyPress(KeyEvent.VK_V);
	     rb.keyRelease(KeyEvent.VK_V);
	     rb.keyRelease(KeyEvent.VK_CONTROL);
	     
	     Thread.sleep(1000);
	     rb.keyPress(KeyEvent.VK_ENTER);
	     rb.keyRelease(KeyEvent.VK_ENTER);
	     Thread.sleep(5000);
	     
	     wp.reg_benefits.sendKeys("Access of all the sessions");
	     
	     wp.submit_button.click();
	     
	     Reporter.log("Workshop Created successfully",true);
	     
	     
	   
   }
}

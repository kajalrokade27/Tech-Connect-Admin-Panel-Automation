package WorkshopValidation;

import static org.testng.Assert.assertTrue;

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
import com.TechConnect.POM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;
@Listeners(com.TechConnect.Listeners.ListenersClass.class)
public class DetailsValidation extends BaseClass
{
    @Test
    public void validateDetailsModule() throws InterruptedException
    {
    	WorkshopPage wp = new WorkshopPage(driver);
    	wp.EventDropdown.click();
    	wp.workshop.click();
    	wp.re_created_workshop.click();
    	Thread.sleep(1000);
    	wp.details_tab.click();
    	Thread.sleep(1000);
    	wp.eventUrl_field.sendKeys("https://admindev.tech-connect.space/");
    	wp.update_button.click();
    }
    
    @Test
    public void validateSessions() throws AWTException, InterruptedException
    {
    	WorkshopPage wp = new WorkshopPage(driver);
    	
    	wp.EventDropdown.click();
        wp.workshop.click();
        wp.re_created_workshop.click();
        Thread.sleep(2000);
        wp.details_tab.click();
       
    	wp.sessions.click();
    	wp.addnew_session.click();
    	wp.session_title.sendKeys("Session I");
    	Select se = new Select(wp.speaker_id);
    	se.selectByIndex(1);
    	Robot rb = new Robot();
    	rb.keyPress(KeyEvent.VK_TAB);
    	
    	wp.session_start_date.click();
    	wp.apr27.click();
    	
    	wp.session_end_date.click();
    	wp.apr29.click();
    	
    	Thread.sleep(2000);
    	wp.session_description.sendKeys("session desc");
    	
    	wp.se_submitbutton.click();
    	
    	Reporter.log("Session created successfully",true);
    	
    	wp.delete_session.click();
    	
    	wp.yes_remove_session.click();
    	ActionClass.waitUptoVisible(wp.del_session_msg);
    	assertTrue(wp.del_session_msg.isDisplayed());
    	Reporter.log("Session Deleted successfully",true);
    	
     }
    
    @Test
    public void validateExistingSpeakers() throws InterruptedException, AWTException
    {
       WorkshopPage wp = new WorkshopPage(driver);
    	
    	wp.EventDropdown.click();
        wp.workshop.click();
        wp.re_created_workshop.click();
        Thread.sleep(2000);
        wp.details_tab.click();
        
        wp.speakers.click();
        
        wp.add_existing_speaker.click();
        Thread.sleep(2000);
        wp.select_speaker.click();
        Thread.sleep(2000);
       // wp.select_speaker.sendKeys("Kajal");
        Robot rb = new Robot();
    	rb.keyPress(KeyEvent.VK_ENTER);
    	rb.keyRelease(KeyEvent.VK_ENTER);
    	
    	wp.submit_ext_button.click();
    	
    	Reporter.log("Existing speaker is added successfully",true);
    	
    }
    
    @Test
    public void validateNewSpeaker() throws InterruptedException, AWTException
    {
       WorkshopPage wp = new WorkshopPage(driver);
    	
    	wp.EventDropdown.click();
        wp.workshop.click();
        wp.re_created_workshop.click();
        Thread.sleep(2000);
        wp.details_tab.click();
        
        wp.speakers.click();
        
        wp.add_new_speaker.click();
        wp.sp_name.sendKeys("kajal");
        
        wp.sp_position.sendKeys("Test Engineer");
        wp.sp_linkedin.sendKeys("https://in.linkedin.com/");
        Select se  = new Select(wp.sp_category);
        se.selectByIndex(1);
        
        wp.sp_profilephoto.click();
        StringSelection filePathSelection =new StringSelection("C:\\Users\\Admin\\git\\TechConnect\\TC_AdminPanelAutomation\\src\\test\\resources\\Profileimages.jpg");

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
	     
	     wp.sp_aboutSpeaker.sendKeys("Testing admin panel of tech connect");
	     
	     wp.sp_new_submitButton.click();
        
    }
}

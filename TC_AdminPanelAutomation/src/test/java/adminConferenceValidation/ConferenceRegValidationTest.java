package adminConferenceValidation;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.JavaUtility.DateClass;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;
@Listeners(com.TechConnect.Listeners.ListenersClass1.class)
public class ConferenceRegValidationTest extends AdminBaseClass
{
	    private ConferencePage cp;
	    private WorkshopPage wp;
	   
	    
	    private void initPages()
	    {
	        if (cp == null) cp = new ConferencePage(driver);
	        if (wp == null) wp = new WorkshopPage(driver);
	    }
	    
	    @BeforeMethod
	    public void navigateToConferencePage() {
	        initPages();
	        ActionClass.click(cp.EventDropdown);
	        ActionClass.click(cp.conferenceLink);
	        ActionClass.click(cp.eventCardName);
	    }
	    @AfterMethod
	    public void navigateToDashboard()
	    {
	    	try {
	    	ActionClass.click(cp.techLogo);
	    	}
	    	catch (Exception e) {
	    		Reporter.log("Failed to navigate to dashboard: " + e.getMessage(), true);
	    	}
	    }
	    private void verifySuccess(org.openqa.selenium.WebElement element, String msg) {
	    	try {
	    		
	        ActionClass.waitUptoVisible(element);
	        assertTrue(element.isDisplayed(), msg + " message not displayed");
	        Reporter.log(msg + ".", true);
	    	}
	    	catch (Exception e) {
				Reporter.log("Verification failed: " + msg, true);
				throw e; // Re-throw the exception to fail the test
			}
	    }
	    
   
   public void fillTicketForm(String prefix) throws IOException, InterruptedException
   {
	  
	  ActionClass.enterText(cp.ticketName, GetPropertyData.propData(prefix+"TicketName"));
	  ActionClass.enterText(cp.ticketDesc, GetPropertyData.propData(prefix+"TicketDesc"));
	  ActionClass.enterText(cp.ticketPrice, GetPropertyData.propData(prefix+"TicketPrice"));
	  ActionClass.click(cp.ticketExpData);
	  DateClass.selectDate(cp.start_monthElem, cp.start_nextButton, cp.dateElements, GetPropertyData.propData(prefix + ".expMonth"), GetPropertyData.propData(prefix + ".expDay"));
	  ActionClass.enterText(cp.ticketQuantity, GetPropertyData.propData(prefix+"TicketQuantity"));
	  ActionClass.click(cp.submitButton);
	 }
   
   @Test(priority = 1)
   public void ticketAddTest() throws IOException, InterruptedException
   {
	   
	     ActionClass.click(cp.registrationsSection);
		  ActionClass.click(cp.addNewTicketButton);
		  fillTicketForm("add");
		  
		  verifySuccess(cp.ticketAddSuccessMessage, "Ticket added successfully");
		 
	   }
  

   @Test(priority = 2)
   public void ticketEditTest() throws IOException, InterruptedException
   {
	   ActionClass.click(cp.registrationsSection);
	   ActionClass.click(cp.editTicketButton);
	   fillTicketForm("edit");
	   
	   verifySuccess(cp.ticketUpdateSuccessMessage, "Ticket edited successfully");
	   
	   
   }
   @Test(priority = 3)
   public void ticketDeleteTest() throws IOException, InterruptedException
   {
	   ActionClass.click(cp.registrationsSection);
	   ActionClass.click(cp.deleteSessionButton);
	   ActionClass.click(cp.confirmDeleteButton);
	   verifySuccess(cp.ticketDeleteSuccessMessage, "Ticket deleted successfully");
   }
   
   
}

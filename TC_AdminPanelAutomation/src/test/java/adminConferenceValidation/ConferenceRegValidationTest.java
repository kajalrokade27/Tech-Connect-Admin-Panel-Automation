package adminConferenceValidation;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.JavaUtility.DateClass;
import com.tc.AdminPOM.ConferencePage;
import com.tech_Connect.Action.ActionClass;
//@Listeners(com.TechConnect.Listeners.ListenersClass1.class)
public class ConferenceRegValidationTest extends AdminBaseClass
{
	    private ConferencePage cp;
	    
	    @BeforeClass
	    public void setup() throws IOException {
	        cp = new ConferencePage(driver);
	        ActionClass.click(cp.EventDropdown);
	        ActionClass.click(cp.conferenceLink);
	        ActionClass.enterText(cp.searchEventField, GetPropertyData.propData("ConferenceDetails").split("\\|")[1]);
	        ActionClass.pressEnter();
	        ActionClass.click(cp.eventCardName);
	        ActionClass.click(cp.registrationsSection);
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
    	ActionClass.click(cp.addNewTicketButton);
		fillTicketForm("add");
		ActionClass.verifySuccessMsg(cp.ticketAddSuccessMessage, "Ticket added successfully");
    }
   @Test(priority = 2)
   public void ticketEditTest() throws IOException, InterruptedException
   {
	  ActionClass.click(cp.editTicketButton);
	   fillTicketForm("edit");
	   ActionClass.verifySuccessMsg(cp.ticketUpdateSuccessMessage, "Ticket edited successfully");
	    }
    @Test(priority = 3)
   public void ticketDeleteTest() throws IOException, InterruptedException
   {
	   ActionClass.click(cp.deleteSessionButton);
	   ActionClass.click(cp.confirmDeleteButton);
	   ActionClass.verifySuccessMsg(cp.ticketDeleteSuccessMessage, "Ticket deleted successfully");
   }
   @Test(priority = 4)
   public void changePaymentStatus() throws IOException
   {
	   ActionClass.click(cp.purchasedInfoSection);
	   ActionClass.click(cp.editPaymentStatusButton);
	   ActionClass.selectByVisibleText(cp.statusDropdown, GetPropertyData.propData("paymentStatus"));
	   ActionClass.click(cp.changeStatus);
	   ActionClass.verifySuccessMsg(cp.statusUpdateSuccessMessage, "Payment status of user "+cp.attendeeNames.get(0).getText()+"  changed to : " + GetPropertyData.propData("paymentStatus"));
   }
   
}

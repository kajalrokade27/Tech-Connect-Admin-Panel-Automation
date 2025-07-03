package adminConferenceValidation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.TechConnect.Base.AdminBaseClass;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

public class ConferenceCountValidations extends AdminBaseClass
{
	  private ConferencePage cp;
	   
	    @BeforeClass
	   public void setup() {
	        cp = new ConferencePage(driver);
	        ActionClass.click(cp.EventDropdown);
	        ActionClass.click(cp.conferenceLink);
	    }
   @Test()
   public void validateConferenceCount() 
   {
	   String TotalconferenceCount = cp.filter_Cards.get(0).getText();
	   ActionClass.click(cp.filter_Cards.get(0));
	   String totalConferenceEvents = cp.conferenceEvents.size() + "";
	   System.out.println("Total conference count from UI: " + TotalconferenceCount);
	   System.out.println("Total conference events from UI: " + totalConferenceEvents);
	   if (TotalconferenceCount.equals(totalConferenceEvents)) 
	   {
		   System.out.println("Total conference count matches: " + TotalconferenceCount);
	   } 
	   else 
	   {
		   System.out.println("Total conference count does not match: " + TotalconferenceCount + " != " + totalConferenceEvents);
	   }
   }
}

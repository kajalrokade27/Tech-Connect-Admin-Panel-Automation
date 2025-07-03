package adminConferenceValidation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.tc.AdminPOM.ConferencePage;
import com.tech_Connect.Action.ActionClass;

public class DashboardValidation extends AdminBaseClass
{
	  private ConferencePage cp;
	    
	    @BeforeClass
	    public void setup() {
	        cp = new ConferencePage(driver);
	        ActionClass.click(cp.EventDropdown);
	        ActionClass.click(cp.conferenceLink);
	        ActionClass.click(cp.eventCardName);
	    }
	  @Test
	  public void verifyDashboardData() 
	  {
		String ticketSales= cp.dashboardElements.get(0).getText();
		ActionClass.click(cp.dashboardElements.get(0)); // Click on the first dashboard element
        String totalAmount = cp.amount.get(0).getText();
        if(ticketSales==totalAmount)
		{
			System.out.println("Ticket sales and total amount match: " + ticketSales);
		}
		else
		{
			System.out.println("Ticket sales and total amount do not match: " + ticketSales + " != " + totalAmount);
		}
        
	  }
}

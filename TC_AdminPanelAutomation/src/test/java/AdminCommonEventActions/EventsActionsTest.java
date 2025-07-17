package AdminCommonEventActions;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.ModeratorPage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;
import static org.testng.Assert.assertTrue;
import java.io.IOException;

public class EventsActionsTest extends AdminBaseClass {

	ConferencePage cp;
	WorkshopPage wp;
    ModeratorPage mp;
	
   @BeforeClass
   public void setup() {
		cp = new ConferencePage(driver);
		wp = new WorkshopPage(driver);
		mp = new ModeratorPage(driver);
		ActionClass.click(cp.EventDropdown);
		
		
	}
   
   public void searchEvent(String eventName) throws IOException, InterruptedException {
	   cp = new ConferencePage(driver);
		ActionClass.enterText(cp.searchEventField, eventName);
		ActionClass.pressEnter();
		if (cp.eventList.isEmpty()) {
			Reporter.log("No events found with the name: " + eventName);
		} else {
			Reporter.log("Events found with the name: " + eventName, true);
			
		}
	}

    public void performPublish() throws IOException, InterruptedException{
    	if(cp.publishOrDraftButton.getText().contains("Publish")) {
         ActionClass.click(cp.publishButton);
        ActionClass.click(cp.confirmPublishButton);
    	}
    	else
    	{
    		Reporter.log("Event is already published, no action taken", true);
		}
		
       }

    public void performSaveAsDraft() throws IOException, InterruptedException {
        if(cp.publishOrDraftButton.getText().contains("Save as Draft")) {
			ActionClass.click(cp.saveAsDraftButton);
			 ActionClass.click(cp.confirmSaveAsDraftButton);
		} else {
			Reporter.log("Event is already saved as draft, no action taken", true);
		}
      }
    
    public void performDelete() throws IOException, InterruptedException {
    	ActionClass.click(cp.threeDotsMenu);
        ActionClass.jsClick(cp.deleteButton);
        ActionClass.jsClick(cp.confirmDeleteButton);
   }

}

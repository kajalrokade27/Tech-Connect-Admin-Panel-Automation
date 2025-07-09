package adminConferenceValidation;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

public class ConferenceCountValidations extends AdminBaseClass
{
	  private ConferencePage cp;
	  WorkshopPage wp ;
	   
	    @BeforeClass
	   public void setup() 
	    {
	        cp = new ConferencePage(driver);
	        wp = new WorkshopPage(driver);
	        ActionClass.click(cp.EventDropdown);
	       // ActionClass.click(cp.conferenceLink);
	        ActionClass.click(wp.workshop);
	    }
	    @Test
	    public void validateAllConferenceFilters() throws InterruptedException
	    {
	        String[] filterNames = {"Total Conferences", "Live Conferences", "Upcoming Conferences", "Past Conferences", "Pending Conferences"};
	        
	        for (int i = 0; i < filterNames.length; i++) {
	            // Step 1: Get dash board filter count (e.g., "6 Total Conferences")
	        	ActionClass.waitForPageLoad(1000);// Ensure the page is loaded
	            String countText = cp.filterNumbers.get(i).getText();  // e.g., "6"
	            int dashboardCount = Integer.parseInt(countText.replaceAll("[^0-9]", ""));
	            Reporter.log(filterNames[i] + " → Dashboard Count: " + dashboardCount, true);

	            // Step 2: Click filter box (simulate UI filter selection)
	            cp.filterNumbers.get(i).click();  // assuming you store all filter boxes in a list
	           ActionClass.waitForPageLoad(1000);// Replace with WebDriverWait ideally

	            // Step 3: Count cards across paginated pages
	            int totalEvents = 0;
	            while (true) {
	                totalEvents += cp.allConferenceCards.size();
	                try {
	                    if (cp.nextPageButton.isDisplayed() && cp.nextPageButton.isEnabled()) {
	                        cp.nextPageButton.click();
	                        ActionClass.waitForPageLoad(1000);  // Wait for the next page to load
	                    } else {
	                        break;
	                    }
	                    
	                } catch (NoSuchElementException e) {
	                    break;           // No next button means single page
	                }
	            }
                Reporter.log(filterNames[i] + " → Found Events: " + totalEvents, true);
	            Assert.assertEquals(totalEvents, dashboardCount, "Mismatch in " + filterNames[i]);

	            Reporter.log("✔ " + filterNames[i] + " count matches actual filtered events.", true);
	        }
	    }
   
}

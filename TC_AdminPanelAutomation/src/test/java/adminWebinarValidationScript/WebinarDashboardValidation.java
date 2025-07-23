package adminWebinarValidationScript;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.TechConnect.Base.AdminBaseClass;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

public class WebinarDashboardValidation extends AdminBaseClass
{
	 private ConferencePage cp;
	  WorkshopPage wp ;
	    
	    @BeforeClass
	    public void setup() {
	        cp = new ConferencePage(driver);
	         wp = new WorkshopPage(driver);
	        ActionClass.click(cp.EventDropdown);
	     //   ActionClass.click(cp.conferenceLink);
	        ActionClass.click(cp.webinarsSection);
	        ActionClass.click(cp.eventCardName);
	    }
	  
	  @Test(priority = 7)
	    public void verifyDaysToEventDisplayedCorrectly() {
	        // Step 1: Get "Days to Event" text from UI (e.g., "36 days left")
	        String daysToEventText = cp.dashboardElements.get(1).getText();
	        int displayedDays = Integer.parseInt(daysToEventText.replaceAll("[^0-9]", ""));
	        Reporter.log("Displayed Days to Event: " + displayedDays, true);
	        
	       // Step 2: Parse the known event start date (from UI or database/config)
	        String fullDateRange = cp.fullDateRange.getText();
	        String eventStartDateStr = fullDateRange.split("-")[0].trim();  // from event info
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
	        LocalDate eventDate = LocalDate.parse(eventStartDateStr, formatter);

	        // Step 3: Get current date and calculate days difference
	        LocalDate today = LocalDate.now();
	        long expectedDays = ChronoUnit.DAYS.between(today, eventDate);

	        // Step 4: Compare actual UI vs calculated days
	        System.out.println("Expected Days to Event: " + expectedDays + " | UI Displayed: " + displayedDays);
	        Assert.assertEquals(displayedDays, expectedDays, "Mismatch in Days to Event!");

	        Reporter.log("✔ 'Days to Event' value is correct on UI", true);
	    }
	  @Test(priority = 2)
	  public void verifyAttendeeCount() throws InterruptedException
	  {
		  ActionClass.implicitWait(); // Ensure the dash board is fully loaded
		  String text = cp.dashboardElements.get(0).getText();
		  int dashboardAttendeeCount = Integer.parseInt(text.replaceAll("[^0-9]", ""));
		  ActionClass.click(cp.dashboardElements.get(0)); // Click on the second dashboard element
		  ActionClass.waitForPageLoad(6000);
		  int actualAttendeeCount = cp.attendeesList.size();
		  Reporter.log("Dashboard Attendee Count: " + dashboardAttendeeCount, true);
		  Reporter.log("Actual Attendee Count on Page: " + actualAttendeeCount, true);
		  Assert.assertEquals(actualAttendeeCount, dashboardAttendeeCount, "Attendee count mismatch!");
		  Reporter.log("✔ Attendee count matches between Dashboard and Detail Page", true);
		  driver.navigate().back(); // Navigate back to the dashboard
	 }
		       public static void verifyDashboardToDetailMatch(WebElement card,WebElement numbers, List<WebElement> rows, String label) throws InterruptedException 
		       {
		    	ActionClass.implicitWait();
		    	ActionClass.waitUptoVisible(numbers);
		        String text = numbers.getText();
		        int dashboardCount = Integer.parseInt(text.replaceAll("[^0-9]", ""));
		        ActionClass.waitUptoVisible(numbers);
		        System.out.println(dashboardCount);
		        ActionClass.jsClick(card);
		        ActionClass.waitForPageLoad(6000);
               int actualCount = rows.size();
               System.out.println(actualCount);
             
               Reporter.log(label + ": Dashboard = " + dashboardCount + ", Page = " + actualCount, true);
		        Assert.assertEquals(actualCount, dashboardCount, label + " count mismatch!");
		        Reporter.log("✔ " + label + " count matches between Dashboard and Detail Page", true);
		        driver.navigate().back(); // Navigate back to the dashboard
		        ActionClass.waitUptoVisible(numbers);
		        ActionClass.waitForPageLoad(6000);
		      }
		       
		       @Test(priority = 4)
		       public void sessionCountValidation() throws InterruptedException
		       {
		    	   verifyDashboardToDetailMatch(cp.eventInfoCards.get(1),cp.eventNumbers.get(0) ,cp.sessionList, "Session Count");
		     }
		       @Test(priority = 5)
		       public void verifySpeakerCount() throws InterruptedException
		       {
		    	   verifyDashboardToDetailMatch(cp.eventInfoCards.get(2),cp.eventNumbers.get(1) ,cp.speakerList, "Speaker Count");
		       }
		       
		       @Test(priority = 6 )
		       public void verifySponsorCount() throws InterruptedException
		       {
		    	   verifyDashboardToDetailMatch(cp.eventInfoCards.get(3),cp.eventNumbers.get(2) ,cp.sponsorList, "Sponsor Count");
		       }       
}

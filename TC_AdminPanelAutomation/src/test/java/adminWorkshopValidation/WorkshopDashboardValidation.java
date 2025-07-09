package adminWorkshopValidation;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

public class WorkshopDashboardValidation extends AdminBaseClass
{
	  private ConferencePage cp;
	  WorkshopPage wp ;
	    
	    @BeforeClass
	    public void setup() {
	        cp = new ConferencePage(driver);
	       wp = new WorkshopPage(driver);
	        ActionClass.click(cp.EventDropdown);
	     //   ActionClass.click(cp.conferenceLink);
	        ActionClass.click(wp.workshop);
	        ActionClass.click(cp.eventCardName);
	    }
	  @Test(priority = 1)
	  public void verifyRegTktData() throws InterruptedException 
	  {
		  Thread.sleep(2000); // Ensure the dashboard is fully loaded
		String ticketSales= cp.dashboardElements.get(0).getText().replace("₹", "").replace(",", "").trim();
		double ticketSalesAmount = Double.parseDouble(ticketSales);
		ActionClass.click(cp.dashboardElements.get(0)); // Click on the first dash board element
        
        double totalAttendeePrice = 0.0;
        for (WebElement priceElement : wp.purchasedAmount) {
            String priceText = priceElement.getText().replace("₹", "").replace(",", "").trim();
            totalAttendeePrice += Double.parseDouble(priceText);
        }
       // Print for reporting
        Reporter.log("Ticket Sales Amount (from Dashboard): ₹" + ticketSalesAmount, true);
        Reporter.log("Total of Attendee Prices: ₹" + totalAttendeePrice,true);

        // Assert equality
        Assert.assertEquals(totalAttendeePrice, ticketSalesAmount, 
                "Mismatch: Ticket sales amount doesn't match total attendee prices.");
        
        Reporter.log("✔ Ticket Sales amount matches total attendee prices", true);
        driver.navigate().back(); // Navigate back to the dash board
        }
	  
	  @Test(priority = 7)
	    public void verifyDaysToEventDisplayedCorrectly() {
	        // Step 1: Get "Days to Event" text from UI (e.g., "36 days left")
	        String daysToEventText = cp.dashboardElements.get(2).getText();
	        int displayedDays = Integer.parseInt(daysToEventText.replaceAll("[^0-9]", ""));
	        
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
		  String text = cp.dashboardElements.get(1).getText();
		  int dashboardAttendeeCount = Integer.parseInt(text.replaceAll("[^0-9]", ""));
		  ActionClass.click(cp.dashboardElements.get(1)); // Click on the second dashboard element
		  ActionClass.waitForPageLoad(6000);
		  int actualAttendeeCount = cp.attendeesList.size();
		  Reporter.log("Dashboard Attendee Count: " + dashboardAttendeeCount, true);
		  Reporter.log("Actual Attendee Count on Page: " + actualAttendeeCount, true);
		  Assert.assertEquals(actualAttendeeCount, dashboardAttendeeCount, "Attendee count mismatch!");
		  Reporter.log("✔ Attendee count matches between Dashboard and Detail Page", true);
		  driver.navigate().back(); // Navigate back to the dashboard
	 }
	  @Test(priority = 3)
	  public void verifyRegistrationBreakdownWithAttendeeData() throws InterruptedException {
		  Thread.sleep(4000);// Ensure the dashboard is fully loaded
	      // Step 1: Get counts from Registration Breakdown
	      int dashboardPaid = Integer.parseInt(cp.breakdownList.get(0).getText().replaceAll("[^0-9]", ""));
	      int dashboardRefunded = Integer.parseInt(cp.breakdownList.get(1).getText().replaceAll("[^0-9]", ""));
	      int dashboardCanceled = Integer.parseInt(cp.breakdownList.get(2).getText().replaceAll("[^0-9]", ""));
	      Reporter.log("Dashboard Paid Count: " + dashboardPaid, true);
	      Reporter.log("Dashboard Refunded Count: " + dashboardRefunded, true);
	      Reporter.log("Dashboard Canceled Count: " + dashboardCanceled, true);

	      // Step 2: Navigate to Purchased Info page
	      ActionClass.click(cp.dashboardElements.get(0)); // Click on the second dashboard element
          Thread.sleep(4000); // Wait for the page to load
	      // Step 3: Count status from actual table
	      int paidCount = 0, refundedCount = 0, canceledCount = 0;
	      for (WebElement status : cp.attendeeStatusList) 
	      {
	          String stat = status.getText().trim().toLowerCase();
	          if (stat.equalsIgnoreCase("paid")) paidCount++;
	          else if (stat.equalsIgnoreCase("Refunded")) refundedCount++;
	          else if (stat.equalsIgnoreCase("canceled")) canceledCount++;
	      } 
	          Reporter.log("Paid Attendee Count: " + paidCount, true);
	          Reporter.log("Refunded Attendee Count: " + refundedCount, true);
	          Reporter.log("Canceled Attendee Count: " + canceledCount, true);
	      

	      // Step 4: Validate counts
	      Assert.assertEquals(paidCount, dashboardPaid, "Paid count mismatch!");
	      Assert.assertEquals(refundedCount, dashboardRefunded, "Refunded count mismatch!");
	      Assert.assertEquals(canceledCount, dashboardCanceled, "Canceled count mismatch!");

	      Reporter.log("✔ Registration breakdown data matches attendee statuses.", true);
	      driver.navigate().back(); // Navigate back to the dashboard
	  }
	  
	
		       public static void verifyDashboardToDetailMatch(WebElement card,WebElement numbers, List<WebElement> rows, String label) throws InterruptedException 
		       {
		    	ActionClass.implicitWait();
		    	ActionClass.waitUptoVisible(numbers);
		        String text = numbers.getText();
		        int dashboardCount = Integer.parseInt(text.replaceAll("[^0-9]", ""));
		        ActionClass.waitUptoVisible(numbers);
		        
		        ActionClass.jsClick(card);
		        ActionClass.waitForPageLoad(6000);
                int actualCount = rows.size();
              
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
		    	   verifyDashboardToDetailMatch(cp.eventInfoCards.get(2),cp.eventNumbers.get(0) ,cp.sessionList, "Session Count");
		    	   
		       }
		       
		       @Test(priority = 5)
		       public void verifySpeakerCount() throws InterruptedException
		       {
		    	   verifyDashboardToDetailMatch(cp.eventInfoCards.get(3),cp.eventNumbers.get(1) ,cp.speakerList, "Speaker Count");
		       }
		       
		       @Test(priority = 6 )
		       public void verifySponsorCount() throws InterruptedException
		       {
		    	   verifyDashboardToDetailMatch(cp.eventInfoCards.get(4),cp.eventNumbers.get(2) ,cp.sponsorList, "Sponsor Count");
		       }
	           
}

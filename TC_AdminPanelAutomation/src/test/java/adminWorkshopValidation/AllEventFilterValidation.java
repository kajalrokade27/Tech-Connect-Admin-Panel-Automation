package adminWorkshopValidation;

import com.TechConnect.Base.AdminBaseClass;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class AllEventFilterValidation extends AdminBaseClass {

    protected ConferencePage cp;
    protected WorkshopPage wp;

    public void validateFiltersForEvent(String eventType) throws InterruptedException {
        cp = new ConferencePage(driver);
        wp = new WorkshopPage(driver);

        // Select Event Type
        ActionClass.click(cp.EventDropdown);
        switch (eventType.toLowerCase()) {
            case "conference": ActionClass.click(cp.conferenceLink); break;
            case "workshop": ActionClass.click(wp.workshop); break;
            case "webinar": ActionClass.click(wp.webinarLink); break;
            case "panel": ActionClass.click(wp.panelDiscussionLink); break;
            case "podcast": ActionClass.click(wp.podcastLink); break;
            default: throw new IllegalArgumentException("Invalid event type: " + eventType);
        }

        Thread.sleep(1000);

        // Filter labels to use in logs
        String[] filterLabels = {"Total", "Live", "Upcoming", "Past", "Pending"};

        for (int i = 0; i < filterLabels.length; i++) {
            int dashboardCount = Integer.parseInt(cp.filterNumbers.get(i).getText().replaceAll("[^0-9]", ""));
            Reporter.log("▶ " + eventType + " → " + filterLabels[i] + " Dashboard Count: " + dashboardCount, true);

            cp.filterNumbers.get(i).click();
            Thread.sleep(1000);

            int totalEvents = 0;
            while (true) {
                totalEvents += cp.allConferenceCards.size();
                try {
                    if (cp.nextPageButton.isDisplayed() && cp.nextPageButton.isEnabled()) {
                        cp.nextPageButton.click();
                        Thread.sleep(1000);
                    } else break;
                } catch (NoSuchElementException e) {
                    break;
                }
            }

            Reporter.log("→ Found: " + totalEvents, true);
            Assert.assertEquals(totalEvents, dashboardCount, "❌ Mismatch in " + filterLabels[i] + " count");
            Reporter.log("✔ " + filterLabels[i] + " filter validated\n", true);
        }
    }
    @Test(priority = 1)
    public void runWebinarValidation() throws InterruptedException {
		validateFiltersForEvent("webinar");
	}
	@Test(priority = 2)
	public void runPanelValidation() throws InterruptedException {
		validateFiltersForEvent("panel");
	}
	@Test(priority = 3)
	public void runPodcastValidation() throws InterruptedException {
		validateFiltersForEvent("podcast");
	}
    @Test(priority = 4)
    public void runWorkshopValidation() throws InterruptedException {
        validateFiltersForEvent("workshop");
    }

    @Test(priority = 5)
    public void runConferenceValidation() throws InterruptedException {
        validateFiltersForEvent("conference");
    }
   
}

package adminWorkshopValidation;

import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.FileUtility.SheetName;
import com.TechConnect.FileUtility.UniversalDataProvider;
import com.TechConnect.JavaUtility.DateClass;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.ModeratorPage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

import AdminCommonEventActions.EventsActionsTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;


public class addWorkshop extends AdminBaseClass {
	 WorkshopPage wp ;
     ConferencePage cp;
     EventsActionsTest eventActions;
     ModeratorPage mp;
	@BeforeClass
	public void setup() throws InterruptedException
	{
		   wp = new WorkshopPage(driver);
	       cp = new ConferencePage(driver);
	       mp = new ModeratorPage(driver);
	       eventActions = new EventsActionsTest();
           ActionClass.click(wp.EventDropdown);
	       ActionClass.click(wp.workshop);
	       ActionClass.implicitWait();
	}

	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 1)
	@SheetName("Workshop")
	public void addWorkshopTest(String[] testData) throws InterruptedException, AWTException {
	    WorkshopPage wp = new WorkshopPage(driver);
	    ConferencePage cp = new ConferencePage(driver);

	    ActionClass.click(wp.addnew_button);
	    ActionClass.selectByVisibleText(wp.event_cat_dropdown, testData[0]);
	    String eventCategory = testData[0];

	    ActionClass.enterText(wp.name_field, testData[1]);
	    ActionClass.selectByVisibleText(wp.event_type_dropdown, testData[2]);
	    String eventType = testData[2];

	    ActionClass.selectByVisibleText(wp.event_scope_dropdown, testData[3]);
	    ActionClass.selectByVisibleText(wp.powered_by_dropdown, testData[4]);
	    ActionClass.enterText(wp.description_field, testData[5]);

	    ActionClass.typeUsingActions(cp.industryTags, testData[6]);
	    ActionClass.pressEnter();

	    
	    ActionClass.scrollToElement(wp.location_field);
	    ActionClass.enterText(wp.location_field, testData[7]);
        
	    ActionClass.scrollToElement(wp.end_date);
	    ActionClass.click(wp.start_date);
	    DateClass.selectDatePro(cp.start_monthElem, cp.start_nextButton, cp.previousMonthButton, cp.dateElements, "July", 2025, "25");

	    ActionClass.click(wp.end_date);
	    DateClass.selectDatePro(cp.end_monthElem, cp.end_nextButton, cp.previousMonthButton, cp.dateElements, "July", 2025, "30");

	    ActionClass.click(cp.outside_Click);
	    ActionClass.implicitWait();

	    // Image Upload
	    ActionClass.scrollToElement(wp.workshop_image);
	    ActionClass.click(wp.workshop_image);
	    ActionClass.uploadFile(testData[11]);

	    // Registration Benefits
	    ActionClass.scrollToElement(wp.reg_benefits);
	    ActionClass.enterText(wp.reg_benefits, testData[8]);

	    // Only for Internal category → check event-type-specific mandatory fields
	    if (eventCategory.equalsIgnoreCase("Internal")) {
	        if (eventType.equalsIgnoreCase("In-Person")) {
	            ActionClass.scrollToElement(wp.price);
	            ActionClass.enterText(wp.price, testData[9]); // price
	        } else if (eventType.equalsIgnoreCase("Live-Streaming")) {
	            ActionClass.scrollToElement(wp.pre_recordedUrl);
	            ActionClass.enterText(wp.pre_recordedUrl, testData[12]); // streaming URL
	            ActionClass.enterText(wp.price, testData[9]); // price
	        } else if (eventType.equalsIgnoreCase("Pre-Recorded")) {
	            ActionClass.scrollToElement(wp.pre_recordedUrl);
	            ActionClass.enterText(wp.pre_recordedUrl, testData[12]); // pre-recorded URL
	        }

	        ActionClass.scrollToElement(wp.zoom_meeting);
	        ActionClass.enterText(wp.zoom_meeting, testData[10]); // Zoom link
	    }

	    // If External → only one extra mandatory field: Event URL
	    if (eventCategory.equalsIgnoreCase("External")) {
	        ActionClass.scrollToElement(wp.eventUrl);
	        ActionClass.enterText(wp.eventUrl, testData[13]);
	    }

	    // Submit form
	    ActionClass.scrollToElement(wp.submit_button);
	    ActionClass.click(wp.submit_button);

	    // Validate toast
	    ActionClass.verifySuccessMsg(wp.success_msg, testData[1] + " Workshop created successfully");
	}
	
	@Test(priority = 2)
	public void deleteWorkshop() throws IOException, InterruptedException
	{
		eventActions.searchEvent(GetPropertyData.propData("UpdateWorkshop").split("~")[1]);
		eventActions.performDelete();
		ActionClass.verifyToastMessage1(wp.toastMessage, mp.cancelButton, "Workshop", true);
		
	}
     
    
   
}

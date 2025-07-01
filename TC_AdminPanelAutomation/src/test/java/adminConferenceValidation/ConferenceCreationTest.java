package adminConferenceValidation;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.JavaUtility.DateClass;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

public class ConferenceCreationTest extends AdminBaseClass 
{
	@Test
	public void navigateToConferencePage() {
	    ConferencePage cp = new ConferencePage(driver);
	    ActionClass.click(cp.EventDropdown);
	    ActionClass.click(cp.conferenceLink);
	}

    @DataProvider(name = "eventData")
    public Object[][] getEventData() throws IOException 
    {
        List<Object[]> dataList = new ArrayList<>();
        String[] keys = { "ConferenceDetails" }; // Update with your actual keys
        for (String key : keys)
        {
            String value = GetPropertyData.propData(key); 
            String[] parts = value.split("\\|");
            if (parts.length == 8)
            {
                dataList.add(new Object[] { parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7] });
            }
        }
        return dataList.toArray(new Object[0][0]);
    }

    @Test(dataProvider = "eventData", dependsOnMethods = "navigateToConferencePage")
    public void TestWithValidData(String eventName,String description,String location,
    		String industry,String event_url,String poweredBy,String meet_link,String regBenefits) throws InterruptedException, AWTException, IOException 
    {
        ConferencePage cp = new ConferencePage(driver);
        WorkshopPage wp = new WorkshopPage(driver);

        ActionClass.click(wp.addnew_button);
        ActionClass.click(wp.event_cat_dropdown);
        ActionClass.selectByIndex(wp.event_cat_dropdown, 0); // Assuming index 0 is the desired category
        ActionClass.enterText(wp.name_field, eventName);
        ActionClass.selectByIndex(wp.event_type_dropdown, 0); // Assuming index 0 is the desired event type
        ActionClass.selectByIndex(wp.event_scope_dropdown, 1); // Assuming index 1 is the desired event scope
        ActionClass.selectByVisibleText(wp.powered_by_dropdown, poweredBy); // Select powered by option

        ActionClass.enterText(wp.description_field, description);
      
        ActionClass.typeUsingActions(cp.industryTags, industry); // Using Actions to type in the industry tags
      
        ActionClass.pressEnter();
     
        ActionClass.enterText(wp.location_field, location);
       
         ActionClass.scrollToElement(wp.start_date); // Scroll to the end date field
         ActionClass.click(wp.start_date);
       
        DateClass.selectDate(cp.start_monthElem, cp.start_nextButton, cp.dateElements, "June 2025", "29");
        ActionClass.scrollToElement(wp.end_date); 
        ActionClass.click(wp.end_date);
        
        DateClass.selectDate(cp.end_monthElem, cp.end_nextButton, cp.dateElements, "July 2025", "30");
        
        ActionClass.click(cp.outside_Click); // Click outside to close the date picker

        wp.workshop_image.click();
        ActionClass.uploadFile(GetPropertyData.propData("ConferenceImagePath")); // Update with the actual image path
        ActionClass.scrollToElement(wp.reg_benefits);
      
        ActionClass.enterText(wp.reg_benefits, regBenefits);

        ActionClass.click(wp.submit_button);
//        assertTrue(cp.successMessage.isDisplayed(), "Success message not displayed after creating conference");
//        ActionClass.waitUptoVisible(cp.successMessage);
       Reporter.log("Conference created successfully with name: " + eventName,true);
        
  }
    
    @Test(dependsOnMethods = "navigateToConferencePage")
    public void publishConference() throws InterruptedException, IOException 
	{
		String conferenceName = GetPropertyData.propData("Workshop1").split(":")[0];
		ConferencePage cp = new ConferencePage(driver);
		assertTrue(conferenceName.equals(cp.eventCardName.getText()), "Conference name does not match the expected value.");
		System.out.println("Conference name: " + cp.eventCardName.getText());
	    ActionClass.jsClick(cp.eventCardName);
	    ActionClass.click(cp.publishButton);
	    ActionClass.click(cp.confirmPublishButton);
    }
    @Test(dependsOnMethods = "navigateToConferencePage")
    public void saveAsDraftConference() throws InterruptedException, IOException
    {
		String conferenceName = GetPropertyData.propData("Workshop1").split(":")[0];
		ConferencePage cp = new ConferencePage(driver);
		assertTrue(conferenceName.equals(cp.eventCardName.getText()), "Conference name does not match the expected value.");
		System.out.println("Conference name: " + cp.eventCardName.getText());
		ActionClass.jsClick(cp.eventCardName);
		ActionClass.click(cp.saveAsDraftButton);
		ActionClass.click(cp.confirmSaveAsDraftButton);
	}
    @Test(dependsOnMethods = "navigateToConferencePage", invocationCount = 6, 
		  threadPoolSize = 6)
    public void deleteConference() throws InterruptedException, IOException 
	{
		String conferenceName = GetPropertyData.propData("Workshop1").split(":")[0];
		ConferencePage cp = new ConferencePage(driver);
		assertTrue(conferenceName.equals(cp.eventCardName.getText()), "Conference name does not match the expected value.");
		System.out.println("Conference name: " + cp.eventCardName.getText());

		ActionClass.jsClick(cp.threeDotsMenu);
		ActionClass.jsClick(cp.deleteButton);
		ActionClass.jsClick(cp.confirmDeleteButton);
		System.out.println("Conference deleted successfully."+conferenceName);
	}
    
}

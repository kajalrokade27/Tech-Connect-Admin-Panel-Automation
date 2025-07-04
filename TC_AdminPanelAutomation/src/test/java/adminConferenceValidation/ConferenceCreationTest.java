package adminConferenceValidation;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.JavaUtility.DateClass;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

public class ConferenceCreationTest extends AdminBaseClass {
    private ConferencePage cp;
    private WorkshopPage wp;
   
    @BeforeClass
    public void setup() {
        cp = new ConferencePage(driver);
        wp = new WorkshopPage(driver);
        ActionClass.click(cp.EventDropdown);
        ActionClass.click(cp.conferenceLink);
    }

    @DataProvider(name = "eventData")
    public Object[][] getEventData() throws IOException {
        String[] parts = GetPropertyData.propData("ConferenceDetails").split("\\|");
        return new Object[][]{{parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8]}};
    }

    @Test(dataProvider = "eventData",priority = 1)
    public void createConference(String eventCat, String eventName, String description, String location,
                               String industry, String event_url, String poweredBy, String meet_link, String regBenefits) 
                               throws Exception {
        ActionClass.click(wp.addnew_button);
        ActionClass.selectByVisibleText(wp.event_cat_dropdown, eventCat);
        ActionClass.enterText(wp.name_field, eventName);
        ActionClass.selectByIndex(wp.event_type_dropdown, 0);
        ActionClass.selectByIndex(wp.event_scope_dropdown, 1);
        ActionClass.selectByVisibleText(wp.powered_by_dropdown, poweredBy);
        ActionClass.enterText(wp.description_field, description);
        ActionClass.typeUsingActions(cp.industryTags, industry);
        ActionClass.pressEnter();
        ActionClass.enterText(wp.location_field, location);
        ActionClass.scrollToElement(wp.end_date);
        ActionClass.click(wp.start_date);
        DateClass.selectDate(cp.start_monthElem, cp.start_nextButton, cp.dateElements, 
                           GetPropertyData.propData("conference.startMonth"), GetPropertyData.propData("conference.startDay"));
        ActionClass.click(wp.end_date);
        DateClass.selectDate(cp.end_monthElem, cp.end_nextButton, cp.dateElements, 
                           GetPropertyData.propData("conference.endMonth"), GetPropertyData.propData("conference.endDay"));
        ActionClass.click(cp.outside_Click);
        
        wp.workshop_image.click();
        ActionClass.uploadFile(GetPropertyData.propData("ConferenceImagePath"));
        ActionClass.scrollToElement(wp.reg_benefits);
        ActionClass.enterText(wp.reg_benefits, regBenefits);
        ActionClass.click(wp.submit_button);
        ActionClass.verifySuccessMsg(cp.successMessage, eventName+" Conference created successfully");
        
    }
    
    @Test(priority = 2)
    public void publishConference() throws IOException {
        String name = GetPropertyData.propData("ConferenceDetails").split("\\|")[1];
        assertTrue(name.equals(cp.eventCardName.getText()));
        ActionClass.click(cp.eventCardName);
        ActionClass.click(cp.publishButton);
        ActionClass.click(cp.confirmPublishButton);
        ActionClass.verifySuccessMsg(cp.publishSuccessMessage,name+ " Conference published successfully");
        driver.navigate().back();
    }
    
    @Test(priority = 3)
    public void saveAsDraftConference() throws IOException {
    	String name = GetPropertyData.propData("ConferenceDetails").split("\\|")[1];
        assertTrue(name.equals(cp.eventCardName.getText()));
        ActionClass.jsClick(cp.eventCardName);
        ActionClass.click(cp.saveAsDraftButton);
        ActionClass.click(cp.confirmSaveAsDraftButton);
        ActionClass.verifySuccessMsg(cp.saveAsDraftSuccessMessage,name+ " Conference saved as draft successfully");
        driver.navigate().back();
    }
    
    @Test(priority = 4)
    public void deleteConference() throws IOException {
    	String name = GetPropertyData.propData("ConferenceDetails").split("\\|")[1];
        assertTrue(name.equals(cp.eventCardName.getText()));
        ActionClass.jsClick(cp.threeDotsMenu);
        ActionClass.jsClick(cp.deleteButton);
        ActionClass.jsClick(cp.confirmDeleteButton);
        ActionClass.verifySuccessMsg(cp.conferenceDeleteSuccessMessage,name+ " Conference deleted successfully");
     }
    
}

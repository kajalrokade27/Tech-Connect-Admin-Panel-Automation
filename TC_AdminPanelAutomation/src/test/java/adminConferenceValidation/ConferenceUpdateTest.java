package adminConferenceValidation;
import java.awt.AWTException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.FileUtility.SheetName;
import com.TechConnect.FileUtility.UniversalDataProvider;
import com.TechConnect.JavaUtility.DateClass;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;


public class ConferenceUpdateTest extends AdminBaseClass {
	 private ConferencePage cp;
	 private WorkshopPage wp;   
	    
	    @BeforeClass
	    public void setup() {
	        cp = new ConferencePage(driver);
	        wp = new WorkshopPage(driver);
	        ActionClass.click(cp.EventDropdown);
	        ActionClass.click(cp.conferenceLink);
	        ActionClass.click(cp.eventCardName);
	        ActionClass.click(cp.detailsTab);
	    }
    
	   private void fillSessionForm(String prefix) throws IOException, InterruptedException {
        ActionClass.enterText(cp.sessionTitle, GetPropertyData.propData(prefix + "Title"));
        ActionClass.typeUsingActions(cp.sessionSpeakerDropdown, GetPropertyData.propData(prefix + "Speaker"));
        ActionClass.pressEnter();
        ActionClass.click(cp.sessionStartDate);
        DateClass.selectDate(cp.start_monthElem, cp.start_nextButton, cp.dateElements, 
            GetPropertyData.propData(prefix + ".startMonth"), GetPropertyData.propData(prefix + ".startDay"));
        ActionClass.click(cp.sessionEndDate);
        DateClass.selectDate(cp.end_monthElem, cp.end_nextButton, cp.dateElements, 
            GetPropertyData.propData(prefix + ".endMonth"), GetPropertyData.propData(prefix + ".endDay"));
        ActionClass.enterText(cp.sessionDesc, GetPropertyData.propData(prefix + "Desc"));
    }
    
    private void fillSpeakerForm(String prefix) throws IOException, AWTException, InterruptedException {
        ActionClass.enterText(cp.speakerName, GetPropertyData.propData(prefix + "Name"));
        ActionClass.enterText(cp.speakerPosition, GetPropertyData.propData(prefix + "Position"));

        // Skip email field for update
        if (!prefix.equalsIgnoreCase("updateSpeaker")) {
            ActionClass.enterText(cp.speakerEmail, GetPropertyData.propData(prefix + "Email"));
        }

        ActionClass.enterText(cp.speakerLinkedInUrl, GetPropertyData.propData(prefix + "LinkedInUrl"));

        // ✅ Dynamic speaker image element
        if (prefix.equalsIgnoreCase("addSpeaker")) {
            ActionClass.click(cp.speakerImageAdd);
        } else if (prefix.equalsIgnoreCase("updateSpeaker")) {
            ActionClass.click(cp.speakerImageUpdate);
        }
        ActionClass.uploadFile(GetPropertyData.propData(prefix + "ImagePath"));

        ActionClass.enterText(cp.speakerAbout, GetPropertyData.propData(prefix + "About"));
        ActionClass.selectByVisibleText(cp.speakerCategory, GetPropertyData.propData(prefix + "Category"));
    }

    
    private void fillSponsorForm(String prefix) throws IOException, AWTException, InterruptedException {
        ActionClass.enterText(cp.companyName, GetPropertyData.propData(prefix + "Name"));
        ActionClass.enterText(cp.companyWebsiteUrl, GetPropertyData.propData(prefix + "Url"));
        ActionClass.click(cp.sponsorlogoBannerImage.get(0));
        ActionClass.uploadFile(GetPropertyData.propData(prefix.replace("Sponsor", "CompanyLogo")));
        ActionClass.click(cp.sponsorlogoBannerImage.get(1));
        ActionClass.uploadFile(GetPropertyData.propData(prefix.replace("Sponsor", "BannerImage")));
        ActionClass.enterText(cp.sponsorDescription, GetPropertyData.propData(prefix + "Desc"));
        ActionClass.selectByVisibleText(cp.sponsorTier, GetPropertyData.propData(prefix + "Tier"));
        ActionClass.selectByVisibleText(cp.sponsorCategory, GetPropertyData.propData(prefix + "Category"));
    }
   
    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 1)
    @SheetName("UpdateConferenceDetails")
    public void updateConference(String[] testData) throws InterruptedException, AWTException, IOException {
        ActionClass.click(cp.conferenceUpdateImage);
        ActionClass.uploadFile(GetPropertyData.propData("ConferenceImagePath"));
        ActionClass.click(cp.changeImageButton);
        ActionClass.verifySuccessMsg(cp.ConfImageChangeSuccess, "Conference image updated successfully");

        ActionClass.scrollToElement(wp.event_cat_dropdown);
        ActionClass.selectByIndex(wp.event_cat_dropdown, 0);
        ActionClass.enterText(wp.name_field, testData[0]);
        ActionClass.selectByIndex(wp.event_type_dropdown, 0);
        ActionClass.selectByIndex(wp.event_scope_dropdown, 1);
        ActionClass.selectByVisibleText(wp.powered_by_dropdown, testData[5]);
        ActionClass.enterText(wp.description_field, testData[1]);
        ActionClass.typeUsingActions(cp.industryTags, testData[3]);
        ActionClass.pressEnter();
        ActionClass.enterText(wp.location_field, testData[2]);

        ActionClass.scrollToElement(wp.start_date);
        ActionClass.click(wp.start_date);
        DateClass.selectDate(cp.start_monthElem, cp.start_nextButton, cp.dateElements,
                GetPropertyData.propData("conference.startMonth"), GetPropertyData.propData("conference.startDay"));

        ActionClass.scrollToElement(wp.end_date);
        ActionClass.click(wp.end_date);
        DateClass.selectDate(cp.end_monthElem, cp.end_nextButton, cp.dateElements,
                GetPropertyData.propData("conference.endMonth"), GetPropertyData.propData("conference.endDay"));

        ActionClass.scrollToElement(wp.reg_benefits);
        ActionClass.enterText(wp.reg_benefits, testData[7]);
        ActionClass.click(cp.submitButton);
 Assert.assertEquals(wp.toastMessage.getText(), "Conference updated successfully.", "Conference update failed");
        Reporter.log("Conference updated with: " + testData[0], true);
    }


    @Test( priority = 2)
    public void addSessions() throws IOException, InterruptedException {
       
        ActionClass.click(cp.sessionTab);
        ActionClass.click(cp.createSessionButton);
        fillSessionForm("addSession");
        ActionClass.click(cp.submitButton);
        ActionClass.verifyToastMessage(wp.toastMessage, cp.closeButton);
          
        
    }

    @Test( priority = 3)
    public void updateSession() throws IOException, InterruptedException {
       
        ActionClass.click(cp.sessionTab);
        ActionClass.click(cp.editSessionButton);
        fillSessionForm("updateSession");
        ActionClass.click(cp.submitButton);
       ActionClass.verifyToastMessage(wp.toastMessage, cp.closeButton);
    }

    @Test(priority = 4)
    public void deleteSession() throws InterruptedException {
      
        ActionClass.click(cp.sessionTab);
        ActionClass.click(cp.deleteSessionButton);
        ActionClass.waitUptoVisible(cp.confirmDeleteSessionButton);
        ActionClass.click(cp.confirmDeleteSessionButton);
      ActionClass.verifyToastMessage(wp.toastMessage, cp.closeButton);
    }

    @Test(priority = 5)
    public void addNewSpeakers() throws IOException, InterruptedException, AWTException {
       
        ActionClass.click(cp.speakersSection);
        ActionClass.click(cp.addNewSpeakerButton);
        fillSpeakerForm("addSpeaker");
        ActionClass.click(cp.submitButton);
        ActionClass.implicitWait();
      ActionClass.verifyToastMessage(wp.toastMessage, cp.closeButton);
    }
    @Test(priority = 6)
    public void updateSpeaker() throws IOException, InterruptedException, AWTException {
    
        ActionClass.click(cp.speakersSection);
        ActionClass.click(cp.speakerList.get(0));
        if (cp.speakerName.isEnabled()) {
            fillSpeakerForm("updateSpeaker");
            ActionClass.click(cp.submitButton);
            ActionClass.verifyToastMessage(wp.toastMessage, cp.closeButton);
        } else {
            Reporter.log("Speaker not enabled for update.", true);
        }
    }
    @Test( priority = 7)
    public void addExistingSpeakers() throws IOException, InterruptedException {
     
        ActionClass.click(cp.speakersSection);
        ActionClass.click(cp.addExistingSpeakersButton);
        ActionClass.typeUsingActions(cp.existingSpeakersDropdown, GetPropertyData.propData("existingSpeaker"));
        ActionClass.pressEnter();
        ActionClass.click(cp.submitButton2);
       ActionClass.verifyToastMessage(wp.toastMessage, cp.closeButton);
    }
    @Test( priority = 8)
    public void deleteSpeaker() throws InterruptedException {
        ActionClass.click(cp.speakersSection);
        ActionClass.click(cp.speakerList.get(0));
        ActionClass.click(cp.deleteSpeakerIcon.get(0));
        ActionClass.waitUptoVisible(cp.confirmDeleteButton);
        ActionClass.click(cp.confirmDeleteButton);
       ActionClass.verifyToastMessage(wp.toastMessage, cp.closeButton);
    }

    @Test(priority = 9)
    public void addNewSponsor() throws IOException, InterruptedException, AWTException {
    
        ActionClass.click(cp.sponsorsSection);
        ActionClass.click(cp.addNewSponsorButton);
        fillSponsorForm("addSponsor");
        ActionClass.click(cp.submitButton);
         ActionClass.verifyToastMessage(wp.toastMessage, cp.closeButton);
    }
    @Test(priority = 10)
    public void sponsorUpdate() throws IOException, InterruptedException, AWTException 
    {
 	   ActionClass.click(cp.sponsorsSection);
 	   ActionClass.click(cp.sponsorsList.get(0)); // Assuming we are updating the first sponsor in the list
 	   ActionClass.enterText(cp.companyName, GetPropertyData.propData("updateSponsorName"));
 	   ActionClass.enterText(cp.companyWebsiteUrl, GetPropertyData.propData("updateSponsorUrl"));
 	   ActionClass.click(cp.updateSponsorImages.get(0));
 	    ActionClass.uploadFile(GetPropertyData.propData("updateCompanyLogo")); // Assuming sponsor_logo is the new image path
 	   ActionClass.scrollToElement(cp.updateSponsorImages.get(1));
 	   ActionClass.click(cp.updateSponsorImages.get(1));
 	   ActionClass.uploadFile(GetPropertyData.propData("updateBannerImage")); // Assuming sponsor_banner is the new image path
 	   ActionClass.enterText(cp.sponsorDescription, GetPropertyData.propData("updateSponsorDesc"));
 	   ActionClass.selectByVisibleText(cp.sponsorTier, GetPropertyData.propData("updateSponsorTier"));
 	   ActionClass.selectByVisibleText(cp.sponsorCategory, GetPropertyData.propData("updateSponsorCategory"));
 	   ActionClass.click(cp.submitButton);
 	  ActionClass.verifyToastMessage(wp.toastMessage, cp.closeButton);
    }
    @Test(priority = 11)
    public void addExistingSponsors() throws IOException, InterruptedException {
       
        ActionClass.click(cp.sponsorsSection);
        ActionClass.click(cp.addExistingSponsorsButton);
        ActionClass.typeUsingActions(cp.existingSponsorsDropdown, GetPropertyData.propData("addSponsorName"));
        ActionClass.pressEnter();
        ActionClass.click(cp.submitButton);
       ActionClass.verifyToastMessage(wp.toastMessage, cp.closeButton);
    }
    @Test( priority = 12)
    public void deleteSponsor() throws InterruptedException, IOException {
      String sponsorName = GetPropertyData.propData("addSponsorName");
        ActionClass.click(cp.sponsorsSection);
       ActionClass.enterText(cp.searchSpeakerField,sponsorName);
        ActionClass.click(cp.deleteSponsorIcon.get(0));
        ActionClass.waitUptoVisible(cp.confirmDeleteButton);
        ActionClass.click(cp.confirmDeleteButton);
       ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton, sponsorName);
		Reporter.log("Sponsor deleted successfully: " + sponsorName, true);
    	
    }
    @AfterClass
    public void postCondition()
    {
    	driver.quit();
    }
    
    
    
}

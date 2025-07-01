package adminConferenceValidation;

import static org.testng.Assert.assertTrue;
import java.awt.AWTException;
import java.io.IOException;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.JavaUtility.DateClass;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

public class ConferenceUpdateTest extends AdminBaseClass {
    private ConferencePage cp;
    private WorkshopPage wp;
    
    private void initPages() {
        if (cp == null) cp = new ConferencePage(driver);
        if (wp == null) wp = new WorkshopPage(driver);
    }
    
    private void navigateToDetails() {
        ActionClass.click(cp.eventCardName);
        ActionClass.click(wp.details_tab);
    }
    
    private void verifySuccess(org.openqa.selenium.WebElement element, String msg) {
        ActionClass.waitUptoVisible(element);
        assertTrue(element.isDisplayed(), msg + " message not displayed");
        Reporter.log(msg + ".", true);
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
        ActionClass.enterText(cp.speakerEmail, GetPropertyData.propData(prefix + "Email"));
        ActionClass.enterText(cp.speakerLinkedInUrl, GetPropertyData.propData(prefix + "LinkedInUrl"));
        ActionClass.click(cp.speakerImage);
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

    @Test
    public void navigateToConferencePage() {
        initPages();
        ActionClass.click(cp.EventDropdown);
        ActionClass.click(cp.conferenceLink);
    }

    @DataProvider(name = "eventData")
    public Object[][] getEventData() throws IOException {
        String value = GetPropertyData.propData("UpdateConferenceDetails");
        String[] parts = value.split("\\|");
        return parts.length == 8 ? new Object[][]{{parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]}} : new Object[0][0];
    }

    @Test(dataProvider = "eventData", dependsOnMethods = "navigateToConferencePage")
    public void TestWithValidData(String eventName, String description, String location,
            String industry, String event_url, String poweredBy, String meet_link, String regBenefits) throws InterruptedException, AWTException, IOException {
        initPages();
        navigateToDetails();
        
        ActionClass.click(cp.conferenceUpdateImage);
        ActionClass.uploadFile(GetPropertyData.propData("ConferenceImagePath"));
        ActionClass.click(cp.changeImageButton);
        verifySuccess(cp.ConfImageChangeSuccess, "Conference image updated successfully");
        
        ActionClass.scrollToElement(wp.event_cat_dropdown);
        ActionClass.selectByIndex(wp.event_cat_dropdown, 0);
        ActionClass.enterText(wp.name_field, eventName);
        ActionClass.selectByIndex(wp.event_type_dropdown, 0);
        ActionClass.selectByIndex(wp.event_scope_dropdown, 1);
        ActionClass.selectByVisibleText(wp.powered_by_dropdown, poweredBy);
        ActionClass.enterText(wp.description_field, description);
        ActionClass.typeUsingActions(cp.industryTags, industry);
        ActionClass.pressEnter();
        ActionClass.enterText(wp.location_field, location);
        
        ActionClass.scrollToElement(wp.start_date);
        ActionClass.click(wp.start_date);
        DateClass.selectDate(cp.start_monthElem, cp.start_nextButton, cp.dateElements, 
            GetPropertyData.propData("conference.startMonth"), GetPropertyData.propData("conference.startDay"));
        ActionClass.scrollToElement(wp.end_date);
        ActionClass.click(wp.end_date);
        DateClass.selectDate(cp.end_monthElem, cp.end_nextButton, cp.dateElements, 
            GetPropertyData.propData("conference.endMonth"), GetPropertyData.propData("conference.endDay"));
        ActionClass.enterText(cp.zoomLinkField, meet_link);
        ActionClass.scrollToElement(wp.reg_benefits);
        ActionClass.enterText(wp.reg_benefits, regBenefits);
        ActionClass.click(cp.submitButton);
    }

    @Test(dependsOnMethods = "navigateToConferencePage")
    public void addSessions() throws IOException, InterruptedException {
        initPages();
        navigateToDetails();
        ActionClass.click(cp.sessionTab);
        ActionClass.click(cp.createSessionButton);
        fillSessionForm("addSession");
        ActionClass.click(cp.submitButton);
        verifySuccess(cp.sessionAddSuccessMessage, "Session added successfully");
    }

    @Test(dependsOnMethods = "navigateToConferencePage")
    public void updateSession() throws IOException, InterruptedException {
        initPages();
        navigateToDetails();
        ActionClass.click(cp.sessionTab);
        ActionClass.click(cp.editSessionButton);
        fillSessionForm("updateSession");
        ActionClass.click(cp.submitButton);
        verifySuccess(cp.sessionUpdateSuccessMessage, "Session updated successfully");
    }

    @Test(dependsOnMethods = "navigateToConferencePage")
    public void deleteSession() throws InterruptedException {
        initPages();
        navigateToDetails();
        ActionClass.click(cp.sessionTab);
        ActionClass.click(cp.deleteSessionButton);
        ActionClass.waitUptoVisible(cp.confirmDeleteSessionButton);
        ActionClass.click(cp.confirmDeleteSessionButton);
        verifySuccess(cp.sessionDeleteSuccessMessage, "Session deleted successfully");
    }

    @Test(dependsOnMethods = "navigateToConferencePage")
    public void addNewSpeakers() throws IOException, InterruptedException, AWTException {
        initPages();
        navigateToDetails();
        ActionClass.click(cp.speakersSection);
        ActionClass.click(cp.addNewSpeakerButton);
        fillSpeakerForm("addSpeaker");
        ActionClass.click(cp.submitButton);
        verifySuccess(cp.speakerAddedSuccessMessage, "Speaker added successfully");
    }

    @Test(dependsOnMethods = "navigateToConferencePage")
    public void addExistingSpeakers() throws IOException, InterruptedException {
        initPages();
        navigateToDetails();
        ActionClass.click(cp.speakersSection);
        ActionClass.click(cp.addExistingSpeakersButton);
        ActionClass.typeUsingActions(cp.existingSpeakersDropdown, GetPropertyData.propData("existingSpeaker"));
        ActionClass.pressEnter();
        ActionClass.click(cp.submitButton);
        verifySuccess(cp.speakerAddedSuccessMessage, "Existing speaker added successfully");
    }

    @Test(dependsOnMethods = "navigateToConferencePage")
    public void updateSpeaker() throws IOException, InterruptedException, AWTException {
        initPages();
        navigateToDetails();
        ActionClass.click(cp.speakersSection);
        ActionClass.click(cp.speakerList.get(0));
        if (cp.speakerName.isEnabled()) {
            fillSpeakerForm("updateSpeaker");
            ActionClass.click(cp.submitButton);
            verifySuccess(cp.speakerUpdateSuccessMessage, "Speaker updated successfully");
        } else {
            Reporter.log("Speaker not enabled for update.", true);
        }
    }

    @Test(dependsOnMethods = "navigateToConferencePage")
    public void deleteSpeaker() throws InterruptedException {
        initPages();
        navigateToDetails();
        ActionClass.click(cp.speakersSection);
        ActionClass.click(cp.speakerList.get(0));
        ActionClass.click(cp.deleteSpeakerIcon.get(0));
        ActionClass.waitUptoVisible(cp.confirmDeleteButton);
        ActionClass.click(cp.confirmDeleteButton);
        verifySuccess(cp.speakerDeleteSuccessMessage, "Speaker deleted successfully");
    }

    @Test(dependsOnMethods = "navigateToConferencePage")
    public void addNewSponsor() throws IOException, InterruptedException, AWTException {
        initPages();
        navigateToDetails();
        ActionClass.click(cp.sponsorsSection);
        ActionClass.click(cp.addNewSponsorButton);
        fillSponsorForm("addSponsor");
        ActionClass.click(cp.submitButton);
        verifySuccess(cp.sponsorCreateSuccessMessage, "Sponsor created successfully");
    }

    @Test(dependsOnMethods = "navigateToConferencePage")
    public void addExistingSponsors() throws IOException {
        initPages();
        navigateToDetails();
        ActionClass.click(cp.sponsorsSection);
        ActionClass.click(cp.addExistingSponsorsButton);
        ActionClass.typeUsingActions(cp.existingSponsorsDropdown, GetPropertyData.propData("addSponsorName"));
        ActionClass.pressEnter();
        ActionClass.click(cp.submitButton);
        verifySuccess(cp.sponsorsAddedSuccessMessage, "Existing sponsor added successfully");
    }

    @Test(dependsOnMethods = "navigateToConferencePage")
    public void sponsorUpdate() throws IOException, InterruptedException, AWTException {
        initPages();
        navigateToDetails();
        ActionClass.click(cp.sponsorsSection);
        ActionClass.click(cp.sponsorsList.get(0));
        fillSponsorForm("updateSponsor");
        ActionClass.click(cp.submitButton);
        verifySuccess(cp.sponsorUpdateSuccessMessage, "Sponsor updated successfully");
    }

    @Test(dependsOnMethods = "navigateToConferencePage")
    public void deleteSponsor() throws InterruptedException {
        initPages();
        navigateToDetails();
        ActionClass.click(cp.sponsorsSection);
        ActionClass.click(cp.deleteSponsorIcon.get(0));
        ActionClass.waitUptoVisible(cp.confirmDeleteButton);
        ActionClass.click(cp.confirmDeleteButton);
        verifySuccess(cp.sponsorDeleteSuccessMessage, "Sponsor deleted successfully");
    }
}

package adminConferenceValidation;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;
import com.TechConnect.JavaUtility.DateClass;

public class ConferenceUpdateTest1 extends AdminBaseClass {

    private ConferencePage cp;
    private WorkshopPage wp;

    private void navigateToDetailsTab() throws InterruptedException {
        cp = new ConferencePage(driver);
        wp = new WorkshopPage(driver);
        ActionClass.click(cp.EventDropdown);
        ActionClass.click(cp.conferenceLink);
        ActionClass.click(cp.eventCardName);
        ActionClass.click(wp.details_tab);
    }

    private void createOrUpdateSession(boolean isEdit) throws IOException, InterruptedException {
        navigateToDetailsTab();
        ActionClass.click(cp.sessionTab);
        if (isEdit) {
            ActionClass.click(cp.editSessionButton);
        } else {
            ActionClass.click(cp.createSessionButton);
        }
        ActionClass.enterText(cp.sessionTitle, GetPropertyData.propData("sessionTitle"));
        ActionClass.typeUsingActions(cp.sessionSpeakerDropdown, GetPropertyData.propData("sessionSpeaker"));
        ActionClass.pressEnter();
        ActionClass.click(cp.sessionStartDate);
        DateClass.selectDate(cp.start_monthElem, cp.start_nextButton, cp.dateElements, "July 2025", "10");
        ActionClass.click(cp.sessionEndDate);
        DateClass.selectDate(cp.end_monthElem, cp.end_nextButton, cp.dateElements, "July 2025", "20");
        ActionClass.enterText(cp.sessionDesc, GetPropertyData.propData("sessionDesc"));
        ActionClass.click(cp.submitButton);
        ActionClass.waitUptoVisible(isEdit ? cp.sessionUpdateSuccessMessage : cp.sessionAddSuccessMessage);
        assertTrue((isEdit ? cp.sessionUpdateSuccessMessage : cp.sessionAddSuccessMessage).isDisplayed());
        Reporter.log(isEdit ? "Session updated successfully." : "Session added successfully.", true);
    }

    private void createOrUpdateSpeaker(boolean isEdit) throws IOException, InterruptedException, AWTException {
        navigateToDetailsTab();
        ActionClass.click(cp.speakersSection);
        if (isEdit) {
            ActionClass.click(cp.speakerList.get(0));
        } else {
            ActionClass.click(cp.addNewSpeakerButton);
        }
        ActionClass.enterText(cp.speakerName, GetPropertyData.propData("speakerName"));
        ActionClass.enterText(cp.speakerEmail, GetPropertyData.propData("speakerEmail"));
        ActionClass.enterText(cp.speakerPosition, GetPropertyData.propData("speakerPosition"));
        ActionClass.enterText(cp.speakerLinkedInUrl, GetPropertyData.propData("speakerLinkedInUrl"));
        ActionClass.click(cp.speakerImage);
        ActionClass.uploadFile(GetPropertyData.propData("speakerImagePath"));
        ActionClass.enterText(cp.speakerAbout, GetPropertyData.propData("speakerAbout"));
        ActionClass.selectByVisibleText(cp.speakerCategory, GetPropertyData.propData("speakerCategory"));
        ActionClass.click(cp.submitButton);
        ActionClass.waitUptoVisible(isEdit ? cp.speakerUpdateSuccessMessage : cp.speakerAddedSuccessMessage);
        assertTrue((isEdit ? cp.speakerUpdateSuccessMessage : cp.speakerAddedSuccessMessage).isDisplayed());
        Reporter.log(isEdit ? "Speaker updated successfully." : "Speaker added successfully.", true);
    }

    private void createOrUpdateSponsor(boolean isEdit) throws IOException, InterruptedException, AWTException {
        navigateToDetailsTab();
        ActionClass.click(cp.sponsorsSection);
        if (isEdit) {
            ActionClass.click(cp.sponsorsList.get(0));
        } else {
            ActionClass.click(cp.addNewSponsorButton);
        }
        ActionClass.enterText(cp.companyName, GetPropertyData.propData("sponsorName"));
        ActionClass.enterText(cp.companyWebsiteUrl, GetPropertyData.propData("sponsUrl"));
        ActionClass.click(cp.sponsorlogoBannerImage.get(0));
        ActionClass.uploadFile(GetPropertyData.propData("companyLogo"));
        ActionClass.click(cp.sponsorlogoBannerImage.get(1));
        ActionClass.uploadFile(GetPropertyData.propData("bannerImage"));
        ActionClass.enterText(cp.sponsorDescription, GetPropertyData.propData("sponsDesc"));
        ActionClass.selectByVisibleText(cp.sponsorTier, GetPropertyData.propData("sponsTier"));
        ActionClass.selectByVisibleText(cp.sponsorCategory, GetPropertyData.propData("sponsCategory"));
        ActionClass.click(cp.submitButton);
        ActionClass.waitUptoVisible(isEdit ? cp.sponsorUpdateSuccessMessage : cp.sponsorCreateSuccessMessage);
        assertTrue((isEdit ? cp.sponsorUpdateSuccessMessage : cp.sponsorCreateSuccessMessage).isDisplayed());
        Reporter.log(isEdit ? "Sponsor updated successfully." : "Sponsor added successfully.", true);
    }

    @Test
    public void updateConferenceDetails() throws IOException, InterruptedException, AWTException {
        navigateToDetailsTab();
        ActionClass.click(cp.conferenceUpdateImage);
        ActionClass.uploadFile(GetPropertyData.propData("ConferenceImagePath"));
        ActionClass.click(cp.changeImageButton);
        ActionClass.waitUptoVisible(cp.ConfImageChangeSuccess);
        assertTrue(cp.ConfImageChangeSuccess.isDisplayed(), "Image update message missing");

        ActionClass.scrollToElement(wp.event_cat_dropdown);
        ActionClass.selectByIndex(wp.event_cat_dropdown, 0);
        ActionClass.enterText(wp.name_field, GetPropertyData.propData("eventName"));
        ActionClass.selectByIndex(wp.event_type_dropdown, 0);
        ActionClass.selectByIndex(wp.event_scope_dropdown, 1);
        ActionClass.selectByVisibleText(wp.powered_by_dropdown, GetPropertyData.propData("poweredBy"));
        ActionClass.enterText(wp.description_field, GetPropertyData.propData("description"));
        ActionClass.typeUsingActions(cp.industryTags, GetPropertyData.propData("industry"));
        ActionClass.pressEnter();
        ActionClass.enterText(wp.location_field, GetPropertyData.propData("location"));

        ActionClass.scrollToElement(wp.start_date);
        ActionClass.click(wp.start_date);
        DateClass.selectDate(cp.start_monthElem, cp.start_nextButton, cp.dateElements, "June 2025", "29");

        ActionClass.scrollToElement(wp.end_date);
        ActionClass.click(wp.end_date);
        DateClass.selectDate(cp.end_monthElem, cp.end_nextButton, cp.dateElements, "July 2025", "30");

        ActionClass.enterText(cp.zoomLinkField, GetPropertyData.propData("meet_link"));
        ActionClass.scrollToElement(wp.reg_benefits);
        ActionClass.enterText(wp.reg_benefits, GetPropertyData.propData("regBenefits"));

        ActionClass.click(cp.submitButton);
        Reporter.log("Conference details updated successfully.", true);
    }

    @Test()
    public void addOrUpdateSession() throws IOException, InterruptedException {
        createOrUpdateSession(true); // set to true if updating
    }

    @Test()
    public void addOrUpdateSpeaker() throws IOException, InterruptedException, AWTException {
        createOrUpdateSpeaker(true); // set to true if updating
    }

    @Test()
    public void addOrUpdateSponsor() throws IOException, InterruptedException, AWTException {
        createOrUpdateSponsor(true); // set to true if updating
    }
}

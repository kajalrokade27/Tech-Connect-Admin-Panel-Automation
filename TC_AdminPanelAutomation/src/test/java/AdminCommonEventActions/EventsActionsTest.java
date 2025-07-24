package AdminCommonEventActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.JavaUtility.DateClass;
import com.tc.AdminPOM.CommonEventPage;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.ModeratorPage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;
import java.awt.AWTException;
import java.io.IOException;


public class EventsActionsTest extends AdminBaseClass {

	ConferencePage cp;
	WorkshopPage wp;
    ModeratorPage mp;
    CommonEventPage commonEp;
	
   
   public void setup() {
		cp = new ConferencePage(driver);
		wp = new WorkshopPage(driver);
		mp = new ModeratorPage(driver);
		commonEp = new CommonEventPage(driver);
		ActionClass.click(cp.EventDropdown);
	}
   
   public void searchEvent(String eventName) throws IOException, InterruptedException {
	   cp = new ConferencePage(driver);
		ActionClass.enterText(cp.searchEventField, eventName);
		ActionClass.pressEnter();
		if (cp.eventList.isEmpty()) {
			Reporter.log("No events found with the name: " + eventName,true);
		} else {
			Reporter.log("Events found with the name: " + eventName, true);
		}
	}
   public void fillCommonEventForm(String[] data) throws IOException, InterruptedException {
     CommonEventPage commonEventPage = new CommonEventPage(driver);
       ActionClass.enterText(commonEventPage.eventName, data[1]);
       ActionClass.selectByVisibleText(commonEventPage.eventCategory.get(0), data[0]);
       ActionClass.selectByVisibleText(commonEventPage.poweredBy,data[3]);
       ActionClass.enterText(commonEventPage.description, data[4]);
       ActionClass.typeUsingActions(cp.industryTags, data[5]);
       ActionClass.pressEnter();
       ActionClass.enterText(commonEventPage.location, data[6]);
       ActionClass.scrollToElement(commonEventPage.start_date);
       ActionClass.click(commonEventPage.start_date);
       DateClass.selectDate(cp.start_monthElem, cp.start_nextButton, cp.dateElements, 
							data[7], data[8]);
       ActionClass.click(commonEventPage.end_date);
       DateClass.selectDate(cp.end_monthElem, cp.end_nextButton, cp.dateElements,data[9], data[10]);
       ActionClass.scrollToElement(commonEventPage.eventCategory.get(2));
       ActionClass.selectByVisibleText(commonEventPage.eventCategory.get(2), data[2]);
       ActionClass.enterText(commonEventPage.eventUrl, data[12]);
     }
   public void fillCommonEventUpdateForm(String[] data) throws IOException, InterruptedException {
	     CommonEventPage commonEventPage = new CommonEventPage(driver);
	       ActionClass.enterText(commonEventPage.eventName, data[1]);
	       ActionClass.selectByVisibleText(commonEventPage.eventCategory.get(0), data[0]);
	       ActionClass.selectByVisibleText(commonEventPage.eventCategory.get(1), data[2]);
	       ActionClass.selectByVisibleText(commonEventPage.poweredBy,data[3]);
	       ActionClass.enterText(commonEventPage.description, data[4]);
	       ActionClass.typeUsingActions(cp.industryTags, data[5]);
	        ActionClass.enterText(commonEventPage.location, data[6]);
	       ActionClass.scrollToElement(commonEventPage.start_date);
	       ActionClass.click(commonEventPage.start_date);
	       DateClass.selectDate(cp.start_monthElem, cp.start_nextButton, cp.dateElements, 
								data[7], data[8]);
	       ActionClass.click(commonEventPage.end_date);
	       DateClass.selectDate(cp.end_monthElem, cp.end_nextButton, cp.dateElements,data[9], data[10]);
	     ActionClass.enterText(commonEventPage.eventUrl, data[12]);
	     }

    public void performPublish() throws IOException, InterruptedException{
    	wp = new WorkshopPage(driver);
    	if(cp.publishOrDraftButton.getText().contains("Publish")) {
         ActionClass.click(cp.publishButton);
        ActionClass.click(cp.confirmPublishButton);
        ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Publish Message : ", true);
    	}
    	else
    	{
    		Reporter.log("Event is already published, no action taken", true);
		}
  }

    public void performSaveAsDraft() throws IOException, InterruptedException {
    	 wp = new WorkshopPage(driver);

        if(cp.publishOrDraftButton.getText().contains("Save as Draft")) {
			ActionClass.click(cp.saveAsDraftButton);
			 ActionClass.click(cp.confirmSaveAsDraftButton);
			 ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Event saved as draft successfully", true);
		} else {
			Reporter.log("Event is already saved as draft, no action taken", true);
		}
      }
  
    
    public void performDelete() throws IOException, InterruptedException {
    	ActionClass.click(cp.threeDotsMenu);
        ActionClass.jsClick(cp.deleteButton);
        ActionClass.jsClick(cp.confirmDeleteButton);
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
    
    public void fillSpeakerForm(String prefix) throws IOException, AWTException, InterruptedException {
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
    

    public void addSessions(String prefix) throws IOException, InterruptedException {
    	wp = new WorkshopPage(driver);
        ActionClass.click(cp.sessionTab);
        ActionClass.click(cp.createSessionButton);
        fillSessionForm(prefix);
        ActionClass.click(cp.submitButton);
        ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Session : ", true);
     
    }

  
    public void updateSession(String prefix) throws IOException, InterruptedException {
    	wp = new WorkshopPage(driver);
        ActionClass.click(cp.sessionTab);
        ActionClass.click(cp.editSessionButton);
        fillSessionForm(prefix);
        ActionClass.click(cp.submitButton);
        ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Toast Message : ", true);
    }

   public void deleteSession() throws InterruptedException {
	   wp = new WorkshopPage(driver);
        ActionClass.click(cp.sessionTab);
        ActionClass.click(cp.deleteSessionButton);
        ActionClass.waitUptoVisible(cp.confirmDeleteSessionButton);
        ActionClass.click(cp.confirmDeleteSessionButton);
        ActionClass.verifySuccessMsg(cp.sessionDeleteSuccessMessage, "Session Delete Message: ");
     }

  
    public void addNewSpeakers(String prefix) throws IOException, InterruptedException, AWTException {
    	wp = new WorkshopPage(driver);
    	commonEp = new CommonEventPage(driver);
        ActionClass.click(cp.speakersSection);
        ActionClass.click(cp.addNewSpeakerButton);
        fillSpeakerForm(prefix);
        ActionClass.click(cp.submitButton);
        ActionClass.verifyToastMessage1(wp.toastMessage, commonEp.cancelButton,"Speaker : ",true);
    }
   
    public void updateSpeaker(String prefix) throws IOException, InterruptedException, AWTException {
    	wp = new WorkshopPage(driver);
        ActionClass.click(cp.speakersSection);
        ActionClass.enterText(cp.searchSpeakerField, GetPropertyData.propData(prefix));
        if (cp.speakerList.isEmpty()) {
			Reporter.log("No speakers found with the name: " + GetPropertyData.propData(prefix),true);
		}
        else
        {
        ActionClass.click(cp.speakerList.get(0));
        if (cp.speakerName.isEnabled()) {
            fillSpeakerForm(prefix);
            ActionClass.click(cp.submitButton);
            ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Sponsor",true);
           
        } else {
            Reporter.log("Speaker not enabled for update.", true);
        }
        }
    }
   
    public void addExistingSpeakers(String prefix) throws IOException, InterruptedException {
    	wp = new WorkshopPage(driver);
    	commonEp = new CommonEventPage(driver);
    	 String existingSpeaker = GetPropertyData.propData(prefix);
         ActionClass.click(cp.speakersSection);
         ActionClass.click(cp.addExistingSpeakersButton);
         ActionClass.typeUsingActions(cp.existingSpeakersDropdown, existingSpeaker);
         ActionClass.implicitWait();
         ActionClass.pressEnter();
         if(cp.existingSpList.isEmpty()) {
 			Reporter.log("No existing speakers found with the name: " + existingSpeaker, true);
 			//ActionClass.waitUptoVisible(cp.closeButton);
 			ActionClass.jsClick(cp.closeButton);
 		}
 		else
 		{
           ActionClass.click(cp.submitButton2);
           ActionClass.verifyToastMessage1(wp.toastMessage, commonEp.cancelButton,existingSpeaker, true);
 		}}

    public void deleteSpeaker() throws InterruptedException {
    	wp = new WorkshopPage(driver);
        ActionClass.click(cp.speakersSection);
        ActionClass.click(cp.speakerList.get(0));
        ActionClass.click(cp.deleteSpeakerIcon.get(0));
        ActionClass.waitUptoVisible(cp.confirmDeleteButton);
        ActionClass.click(cp.confirmDeleteButton);
        ActionClass.verifySuccessMsg(cp.speakerDeleteSuccessMessage, "Speaker deleted successfully");
    }


    public void addNewSponsor(String prefix) throws IOException, InterruptedException, AWTException {
    	wp = new WorkshopPage(driver);
        ActionClass.click(cp.sponsorsSection);
        ActionClass.click(cp.addNewSponsorButton);
        fillSponsorForm(prefix);
        ActionClass.click(cp.submitButton);
        ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Sponsor",true);
    }

    public void sponsorUpdate(String[]testData) throws IOException, InterruptedException, AWTException 
    {
    	wp = new WorkshopPage(driver);
 	   ActionClass.click(cp.sponsorsSection);
 	   ActionClass.enterText(cp.searchSponsorField, testData[0]); // Assuming testData[0] is the sponsor name to search
 	   if (cp.sponsorsList.isEmpty()) {
 		   Reporter.log("No sponsors found with the name: " + testData[0], true);
 	   }
 	   
 	   else {
 	   ActionClass.click(cp.sponsorsList.get(0)); // Assuming we are updating the first sponsor in the list
 	   ActionClass.enterText(cp.companyName, testData[0]);
 	   ActionClass.enterText(cp.companyWebsiteUrl, testData[1]);
 	   ActionClass.click(cp.updateSponsorImages.get(0));
 	    ActionClass.uploadFile(testData[2]); // Assuming sponsor_logo is the new image path
 	   ActionClass.scrollToElement(cp.updateSponsorImages.get(1));
 	   ActionClass.click(cp.updateSponsorImages.get(1));
 	   ActionClass.uploadFile(testData[3]); // Assuming sponsor_banner is the new image path
 	   ActionClass.enterText(cp.sponsorDescription, testData[4]);
 	   ActionClass.selectByVisibleText(cp.sponsorTier, testData[6]);
 	   ActionClass.selectByVisibleText(cp.sponsorCategory, testData[5]);
 	   ActionClass.click(cp.submitButton);
 	  ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Sponsor", false);
    }}
    
    public void addExistingSponsors(String prefix) throws IOException, InterruptedException {
    	wp = new WorkshopPage(driver);
    	 
    	String existingSponsor = GetPropertyData.propData(prefix);
        ActionClass.click(cp.sponsorsSection);
        ActionClass.click(cp.addExistingSponsorsButton);
        ActionClass.typeUsingActions(cp.existingSponsorsDropdown, existingSponsor);
        ActionClass.pressEnter();
     
         if(cp.existingSpList.isEmpty()) {
         	Reporter.log("No existing sponsors found with the name: " + existingSponsor, true);
         	//ActionClass.waitUptoClickable(cp.closeButton);
         	ActionClass.jsClick(cp.closeButton);
         }
         
         else
 		{
        ActionClass.click(cp.submitButton);
        ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Sponsor",true);
    }}
    
    public void deleteSponsor() throws InterruptedException {
    	wp = new WorkshopPage(driver);
        ActionClass.click(cp.sponsorsSection);
        ActionClass.click(cp.deleteSponsorIcon.get(0));
        ActionClass.waitUptoVisible(cp.confirmDeleteButton);
        ActionClass.click(cp.confirmDeleteButton);
       ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Sponsor deleted successfully", true);
    }
    
    
    public void validateAddVideo(String filePath) throws InterruptedException, AWTException, IOException {
    	wp = new WorkshopPage(driver);
    	commonEp = new CommonEventPage(driver);
        ActionClass.click(cp.addVideoButton);
        ActionClass.uploadFile(filePath);
        ActionClass.click(cp.uploadVideoButton);
        ActionClass.click(cp.confirmUploadVideoButton);
       ActionClass.verifyToastMessage1(wp.toastMessage, commonEp.cancelButton, "Verified : ", false);
    }
        public void validateDeleteVideo() throws InterruptedException, AWTException, IOException {
    	wp = new WorkshopPage(driver);
    	commonEp = new CommonEventPage(driver);
    	if(cp.videoList.isEmpty()) {
    		Reporter.log("No videos found to delete", true);
    	}
    	else {
		ActionClass.waitUptoClickable(cp.deleteVideoButton);
		ActionClass.click(cp.deleteVideoButton);
		ActionClass.click(cp.confirmDeleteVideoButton);
		ActionClass.verifyToastMessage1(wp.toastMessage, commonEp.cancelButton, "Verified : ", false);
	}
    }
    
   
   
   

}

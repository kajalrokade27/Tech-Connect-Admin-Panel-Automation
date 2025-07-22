package adminWorkshopValidation;
import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.JavaUtility.DateClass;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.ModeratorPage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;
import AdminCommonEventActions.EventsActionsTest;

public class WorkshopUpdateTest extends AdminBaseClass  {
	 ConferencePage cp;
	 WorkshopPage wp;
	 EventsActionsTest et;
	 ModeratorPage mp;
	@BeforeClass
	public void setUp() throws IOException, InterruptedException 
	{
		     wp = new WorkshopPage(driver);
		    cp = new ConferencePage(driver); 
		    et = new EventsActionsTest();
		    mp = new ModeratorPage(driver);
		    ActionClass.click(wp.EventDropdown);
		    ActionClass.click(wp.workshop);
		   et.searchEvent(GetPropertyData.propData("UpdateWorkshop").split("~")[1]);
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
	    
	    

	    @DataProvider(name = "eventData")
	    public Object[][] getEventData() throws IOException {
	        String value = GetPropertyData.propData("UpdateWorkshop");
	        String[] parts = value.split("~", -1); // split using ~ instead of :
	        return parts.length == 12 ? new Object[][]{{parts[0],
	        	
	        	parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8],parts[9],parts[10],parts[11]}} : new Object[0][0];
	    }

	    @Test(dataProvider = "eventData", priority = 1)
	    public void TestWithValidData(String category, String name, String type, String scope,
                String poweredBy, String desc, String industryTags, String location, String benefits,
                String price, String zoomLink, String imagePath) throws InterruptedException, AWTException, IOException {
	    
	        WorkshopPage wp = new WorkshopPage(driver);
	 	    ConferencePage cp = new ConferencePage(driver);
	        ActionClass.click(cp.conferenceUpdateImage);
	        ActionClass.uploadFile(GetPropertyData.propData("ConferenceImagePath"));
	        ActionClass.click(cp.changeImageButton);
	        ActionClass.verifySuccessMsg(cp.imageUpdatedMsg, "Workshop image updated successfully");
	        
	        ActionClass.selectByVisibleText(wp.event_cat_dropdown, category);
		    ActionClass.enterText(wp.name_field, name);
		    ActionClass.selectByVisibleText(wp.event_type_dropdown, type);
		    ActionClass.selectByVisibleText(wp.event_scope_dropdown, scope);
		    ActionClass.enterText(wp.description_field, desc);
		    ActionClass.selectByVisibleText(wp.powered_by_dropdown, poweredBy);
		    ActionClass.typeUsingActions(cp.industryTags, industryTags );
		   
	        ActionClass.scrollToElement(wp.location_field);
		    ActionClass.enterText(wp.location_field, location);
	        ActionClass.click(wp.start_date);
	        DateClass.selectDatePro(cp.start_monthElem, cp.start_nextButton, cp.previousMonthButton, cp.dateElements, "August", 2025, "10");
	       
	        ActionClass.click(wp.end_date);
	        DateClass.selectDatePro(cp.end_monthElem, cp.end_nextButton, cp.previousMonthButton, cp.dateElements, "December", 2025, "30");
	        ActionClass.click(cp.outside_Click);
	      
	        ActionClass.scrollToElement(wp.reg_benefits);
		    ActionClass.enterText(wp.reg_benefits, benefits);
		    ActionClass.enterText(wp.price, price);
		    
		    ActionClass.scrollToElement(wp.updateWorkshop);
		    ActionClass.click(wp.updateWorkshop);
		    ActionClass.verifySuccessMsg(cp.eventUpdatedMsg, name + " Workshop created successfully");
	    }

	    @Test( priority = 2)
	    public void addSessions() throws IOException, InterruptedException {
	       
	        ActionClass.click(cp.sessionTab);
	        ActionClass.click(cp.createSessionButton);
	        fillSessionForm("addSession");
	        ActionClass.click(cp.submitButton);
	       
	        ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Session : ",true);
	          
	        
	    }

	    @Test( priority = 3)
	    public void updateSession() throws IOException, InterruptedException {
	       
	        ActionClass.click(cp.sessionTab);
	        ActionClass.click(cp.editSessionButton);
	        fillSessionForm("updateSession");
	        ActionClass.click(cp.submitButton);
	        ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Sesssion : ",true);
	    }

	    @Test(priority = 4)
	    public void deleteSession() throws InterruptedException {
	      
	        ActionClass.click(cp.sessionTab);
	        ActionClass.click(cp.deleteSessionButton);
	        ActionClass.waitUptoVisible(cp.confirmDeleteSessionButton);
	        ActionClass.click(cp.confirmDeleteSessionButton);
	        ActionClass.verifySuccessMsg(cp.sessionDeleteSuccessMessage, "Session deleted successfully");
	    }

	    @Test(priority = 5)
	    public void addNewSpeakers() throws IOException, InterruptedException, AWTException {
	       
	        ActionClass.click(cp.speakersSection);
	        ActionClass.click(cp.addNewSpeakerButton);
	        fillSpeakerForm("addSpeaker");
	        ActionClass.click(cp.submitButton);
	       
	        ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Speaker : ",true);
	    }
	    @Test(priority = 7)
	    public void updateSpeaker() throws IOException, InterruptedException, AWTException {
	    
	        ActionClass.click(cp.speakersSection);
	        ActionClass.enterText(cp.searchSpeakerField, GetPropertyData.propData("updateSpeakerName"));
	        if (cp.speakerList.isEmpty()) {
				Reporter.log("No speakers found with the name: " + GetPropertyData.propData("updateSpeakerName"),true);
			}
	        else
	        {
	        ActionClass.click(cp.speakerList.get(0));
	        if (cp.speakerName.isEnabled()) {
	            fillSpeakerForm("updateSpeaker");
	            ActionClass.click(cp.submitButton);
	            ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Sponsor",false);
	        } else {
	            Reporter.log("Speaker not enabled for update.", true);
	        }
	        }
	    }
	    @Test( priority = 6)
	    public void addExistingSpeakers() throws IOException, InterruptedException {
	     
	    	 String existingSpeaker = GetPropertyData.propData("existingSpeaker");
	    	 ActionClass.implicitWait();
	         ActionClass.click(cp.speakersSection);
	         ActionClass.click(cp.addExistingSpeakersButton);
	         ActionClass.typeUsingActions(cp.existingSpeakersDropdown, existingSpeaker);
	        // ActionClass.pressEnter();
	         if(cp.existingSpList.isEmpty()) {
	 			Reporter.log("No existing speakers found with the name: " + existingSpeaker, true);
	 			ActionClass.waitUptoVisible(cp.closeButton);
	 			ActionClass.click(cp.closeButton);
	 		}
	 		else
	 		{
	           ActionClass.click(cp.submitButton2);
	           ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,existingSpeaker, true);
	 		}}
	    @Test( priority = 8)
	    public void deleteSpeaker() throws InterruptedException {
	        ActionClass.click(cp.speakersSection);
	        ActionClass.click(cp.speakerList.get(0));
	        ActionClass.click(cp.deleteSpeakerIcon.get(0));
	        ActionClass.waitUptoVisible(cp.confirmDeleteButton);
	        ActionClass.click(cp.confirmDeleteButton);
	        ActionClass.verifySuccessMsg(cp.speakerDeleteSuccessMessage, "Speaker deleted successfully");
	    }

	    @Test(priority = 9)
	    public void addNewSponsor() throws IOException, InterruptedException, AWTException {
	    
	        ActionClass.click(cp.sponsorsSection);
	        ActionClass.click(cp.addNewSponsorButton);
	        fillSponsorForm("addSponsor");
	        ActionClass.click(cp.submitButton);
	        ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Sponsor",true);
	    }
//	    @Test(priority = 10)
//	    public void sponsorUpdate() throws IOException, InterruptedException, AWTException 
//	    {
//	 	  ActionClass.click(cp.sponsorsSection);
//	 	  ActionClass.enterText(cp.searchSponsorField, GetPropertyData.propData("updateSponsorName"));
//	 	   if (cp.sponsorsList.isEmpty()) {
//	 		   if(wp.noDataFoundMessage.isDisplayed())
//	 		   {
//	 		   Reporter.log("No sponsors found with the name: " + GetPropertyData.propData("updateSponsorName"), true);
//	 		   }
//	 		   driver.navigate().refresh();
//	 		   return;
//	 	   }
//	 		  
//	 	   ActionClass.click(cp.sponsorsList.get(0)); // Assuming we are updating the first sponsor in the list
//	 	   ActionClass.enterText(cp.companyName, GetPropertyData.propData("updateSponsorName"));
//	 	   ActionClass.enterText(cp.companyWebsiteUrl, GetPropertyData.propData("updateSponsorUrl"));
//	 	   ActionClass.click(cp.updateSponsorImages.get(0));
//	 	   ActionClass.uploadFile(GetPropertyData.propData("updateCompanyLogo")); // Assuming sponsor_logo is the new image path
//	 	   ActionClass.scrollToElement(cp.updateSponsorImages.get(1));
//	 	   ActionClass.click(cp.updateSponsorImages.get(1));
//	 	   ActionClass.uploadFile(GetPropertyData.propData("updateBannerImage")); // Assuming sponsor_banner is the new image path
//	 	   ActionClass.enterText(cp.sponsorDescription, GetPropertyData.propData("updateSponsorDesc"));
//	 	   ActionClass.selectByVisibleText(cp.sponsorTier, GetPropertyData.propData("updateSponsorTier"));
//	 	   ActionClass.selectByVisibleText(cp.sponsorCategory, GetPropertyData.propData("updateSponsorCategory"));
//	 	   ActionClass.click(cp.submitButton);
//	 	   ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Sponsor", false);
//	    }
//	   
	    @Test(priority = 11)
	    public void sponsorUpdate() throws IOException, InterruptedException, AWTException {
	        String sponsorName      = GetPropertyData.propData("updateSponsorName");
	        String sponsorUrl       = GetPropertyData.propData("updateSponsorUrl");
	        String sponsorLogoPath  = GetPropertyData.propData("updateCompanyLogo");
	        String bannerImagePath  = GetPropertyData.propData("updateBannerImage");
	        String sponsorDesc      = GetPropertyData.propData("updateSponsorDesc");
	        String sponsorTier      = GetPropertyData.propData("updateSponsorTier");
	        String sponsorCategory  = GetPropertyData.propData("updateSponsorCategory");

	        ActionClass.click(cp.sponsorsSection);
	        ActionClass.enterText(cp.searchSponsorField, sponsorName);
	        Thread.sleep(1000); // Replace with WebDriverWait if needed

	        // Validate "No sponsors available" message when search yields no results
	        if (cp.sponsorsList.isEmpty()) {
	            if (wp.noSponsorMessage.isDisplayed()) {
	                String actualMessage = wp.noSponsorMessage.getText().trim();
	                Assert.assertEquals(actualMessage, "No sponsors available. Please add a new sponsor.",
	                        "No sponsor message mismatch!");
	                Reporter.log("Verified: " + actualMessage, true);
	            } else {
	                Assert.fail("Sponsor list is empty, but 'No sponsors' message not displayed.");
	            }
	            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("location.reload();");
	            return;
	        }

	        // Update sponsor if found
	        ActionClass.click(cp.sponsorsList.get(0));
	        ActionClass.enterText(cp.companyName, sponsorName);
	        ActionClass.enterText(cp.companyWebsiteUrl, sponsorUrl);

	        if (!cp.updateSponsorImages.isEmpty()) {
	            ActionClass.click(cp.updateSponsorImages.get(0));
	            ActionClass.uploadFile(sponsorLogoPath);
	        }

	        if (cp.updateSponsorImages.size() > 1) {
	            ActionClass.scrollToElement(cp.updateSponsorImages.get(1));
	            ActionClass.click(cp.updateSponsorImages.get(1));
	            ActionClass.uploadFile(bannerImagePath);
	        }

	        ActionClass.enterText(cp.sponsorDescription, sponsorDesc);
	        ActionClass.selectByVisibleText(cp.sponsorTier, sponsorTier);
	        ActionClass.selectByVisibleText(cp.sponsorCategory, sponsorCategory);

	        ActionClass.click(cp.submitButton);
	        ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton, "Sponsor", false);
	    }


	    @Test(priority = 10)
	    public void addExistingSponsors() throws IOException, InterruptedException {
	    	String existingSponsor = GetPropertyData.propData("addSponsorName");
	        ActionClass.click(cp.sponsorsSection);
	        ActionClass.click(cp.addExistingSponsorsButton);
	        ActionClass.typeUsingActions(cp.existingSponsorsDropdown, GetPropertyData.propData("addSponsorName"));
	        ActionClass.pressEnter();
	        ActionClass.implicitWait();
	        List<WebElement> existingSponsors = cp.existingSpList;
	        System.out.println("Existing Sponsors: " + existingSponsors.size());
	         if( existingSponsors.isEmpty()) {
	         	Reporter.log("No existing sponsors found with the name: " + existingSponsor, true);
	         	ActionClass.waitUptoClickable(cp.closeButton);
	         	ActionClass.jsClick(cp.closeButton);
	         }
	         else
	 		{
	        ActionClass.click(cp.submitButton);
	        ActionClass.verifyToastMessage1(wp.toastMessage, cp.closeButton,"Sponsor",true);
	    }}
	    @Test( priority = 12)
	    public void deleteSponsor() throws InterruptedException {
	      
	        ActionClass.click(cp.sponsorsSection);
	        ActionClass.click(cp.deleteSponsorIcon.get(0));
	        ActionClass.waitUptoVisible(cp.confirmDeleteButton);
	        ActionClass.click(cp.confirmDeleteButton);
	        ActionClass.verifySuccessMsg(cp.sponsorDeleteSuccessMessage, "Sponsor deleted successfully");
	    }
	   
	    @Test(priority = 13)
	    public void publishWorkshop() throws IOException, InterruptedException {
	    			    et.performPublish();
	    			    ActionClass.verifyToastMessage1(wp.toastMessage, mp.cancelButton,  " published successfully", false);
	    		        ActionClass.implicitWait();
	    		}
	    @Test(priority = 14)
	    public void saveAsDraftWorkshop() throws IOException, InterruptedException {
	    	et.performSaveAsDraft();
	    	ActionClass.verifyToastMessage1(wp.toastMessage, mp.cancelButton, " saved as draft successfully", false);
	    	ActionClass.implicitWait();
	    }
	   
	    
	    
	    
}
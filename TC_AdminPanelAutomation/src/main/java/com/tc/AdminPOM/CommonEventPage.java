package com.tc.AdminPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TechConnect.Base.AdminBaseClass;

public class CommonEventPage extends AdminBaseClass
{
   public CommonEventPage(WebDriver driver) {
	  PageFactory.initElements(driver, this);
   }
   
   public @FindBy(xpath = "//img[@alt='logo-text']")
	WebElement logoText;
	//WebElements for the Webinars and Events page here
	
			public @FindBy(xpath = "//span[contains(text(),'Events')]")
			WebElement eventsSection;
			public @FindBy(xpath = "//span[contains(text(),'Webinars')]")
			WebElement webinarsSection;
			
			public @FindBy(xpath = "//span[contains(text(),'Panel Discussion')]")
			WebElement panelDiscussionsSection;
			
			public @FindBy(xpath = "//span[contains(text(),'Podcasts')]")
			WebElement podcastSection;
			
			
			public @FindBy(xpath = "//button[text()='Add New']")
			WebElement addNewButton;
			public @FindBy(xpath = "//select[@class='form-control']")
			List<WebElement> eventCategory;
			public @FindBy(name = "name")
			WebElement eventName;
			public @FindBy(name = "poweredBy")
			WebElement poweredBy;
			public @FindBy(xpath = "//span[text()='Create New']")
			WebElement createNewPButton;
			public @FindBy(xpath = "//div[@role='textbox']")
			WebElement description;
			public @FindBy(xpath = "//div[@class=' css-1hwfws3']")
			WebElement industryTags;
			public @FindBy(name = "location")
			WebElement location;
			//Start Date and End Date
			public @FindBy(xpath="(//input[@class='form-control'])[3]")
		    WebElement start_date;
			public @FindBy(xpath="(//input[@class='form-control'])[4]")
		    WebElement end_date;
			public @FindBy(xpath = "(//th[@class='rdtSwitch'])[1]")
		     WebElement start_monthElem;
		     
		     public @FindBy(xpath = "(//th[@class='rdtNext'])[1]")
		     WebElement start_nextButton;
		     
		     public @FindBy(xpath = "//td[@class='rdtDay']")
		     List<WebElement> dateElements;
		     
		     public @FindBy(xpath = "(//th[@class='rdtSwitch'])[2]")
		     WebElement end_monthElem;
		     
		     public @FindBy(xpath = "(//th[@class='rdtNext'])[2]")
		     WebElement end_nextButton;

			public @FindBy(xpath = "//div[@class='fui-avatar-label'][contains(text(),'Select image')]")
			WebElement webinarImage ;
			public @FindBy(xpath = "//div[@class='mb-3'][9]")
		    WebElement outside_Click;
			public @FindBy(name = "eventUrl")
			WebElement eventUrl;
			public @FindBy(name = "zoomLink")
			WebElement zoomLink;
			public @FindBy(xpath = "//button[text()='Submit']")
			WebElement submitButton;
			public @FindBy(xpath = "//button[text()='Reset']")
			WebElement resetButton;
			public @FindBy(xpath = "//div[text()='new webinar created successfully.']")
			WebElement webinar_Add_Sufull;
			
			//Nevigation to the Webinars details/Delete section
			public @FindBy(xpath = "//button[starts-with(@id, 'dropdownMenuButton-') and @type='button']")
			List<WebElement> menuButton;
			public @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-end show']//a[@title='Details'][normalize-space()='Details']")
			WebElement webinarDetails;
			public @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-end show']//button[@title='Delete'][normalize-space()='Delete']")
			WebElement deleteWebinar;
			public @FindBy(xpath = "//button[text()='Yes, remove it!']")
			WebElement confirmDeleteWebinarsection;
			public @FindBy(xpath = "//button[text()='Yes, delete it!']")
			WebElement confirmDeleteButton;
			public @FindBy(xpath = "//div[text()='Webinar deleted successfully.']")
			WebElement webinar_Delete_Sufull;
			public @FindBy(xpath = "//button[text()='Cancel']")
			WebElement cancelButton;
			//WebElements for the webinar details section
			//Webinars Overview Info image upload or chaenge
			public @FindBy(xpath = "//img[@alt='Avatar2']")
			WebElement webinarDetailsImage;
			public @FindBy(xpath = "//button[text()='Change']")
			WebElement changeImageButton;
			public @FindBy(xpath = "//div[text()='Webinar image updated successfully.']")
			WebElement webinarImageChangeSuccess;
			//Create a session to get started
			public @FindBy(xpath = "//small[text()='Sessions']")
			WebElement createSessionButton;
			public @FindBy(xpath = "//input[@name='title']")
			WebElement sessionTitle;
			public @FindBy(xpath = "//select[@name='speakerId']")
			WebElement sessionSpeakerDropdown;
			//WebElements for the session start and end date
			public @FindBy(xpath = "(//input[@class='form-control'])[2]")
			WebElement sessionStartDate;
			public @FindBy(xpath = "(//input[@class='form-control'])[3]")
			WebElement sessionEndDate;
			public @FindBy(xpath = "//div[text()='Session added successfully.']")
			WebElement sessionAddSuccessMessage;
			public @FindBy(xpath = "//div[text()='Session updated successfully.']")
			WebElement sessionUpdateSuccessMessage;
			// Update or edit session 
			public @FindBy(xpath = "//button[@title='Edit']")
			WebElement editSessionButton;
			// Delete session
			public @FindBy(xpath = "//button[@title='Delete']")
			WebElement deleteSessionButton;
			public @FindBy(xpath = "//button[@class='swal2-confirm swal2-styled swal2-default-outline']")
			WebElement confirmDeleteSessionButton;
			public @FindBy(xpath = "//div[text()='Session deleted successfully.']")
			WebElement sessionDeleteSuccessMessage;
			//Navigate to the Speaker section  
			public @FindBy(xpath = "//small[text()='Speakers']")
			WebElement speakersSection;
			// Add new speaker button
			public @FindBy(xpath = "//button[text()='Add New Speaker']")
			WebElement addNewSpeakerButton;
			// Speaker form fields
			public @FindBy(name = "name")
			WebElement speakerName;
			public @FindBy(name = "email")
			WebElement speakerEmail;
			public @FindBy(name = "position")
			WebElement speakerPosition;
			public @FindBy(name = "linkedInUrl")
			WebElement speakerLinkedInUrl;
			public @FindBy(name = "about")
			WebElement speakerAbout;
			// Speaker image upload
			public @FindBy(xpath = "//div[@class='fui-avatar-label'][contains(text(),'Select image')]")
			WebElement speakerImage;
			// Speaker Category *
			public @FindBy(name = "who")
			WebElement speakerCategory;
			//close buttons
			public @FindBy(xpath = "//button[@class='btn-close']")
			WebElement closeButton;
			//Add Existing Speakers in the webinar session
			public @FindBy(xpath = "//button[text()='Add Existing Speakers']")
			WebElement addExistingSpeakersButton;
			public @FindBy(xpath = "//div[@class=' css-yk16xz-control']")
			WebElement existingSpeakersDropdown;
			//Existing Speakers
			public @FindBy(xpath = "//div[text()='Speakers added successfully.']")
			WebElement speakerEAddedSuccessMessage;
			//Create new Speaker
			public @FindBy(xpath = "//div[text()='Speaker added successfully.']")
			WebElement speakerAddedSuccessMessage;
			public @FindBy(xpath = "//div[text()='This email is already registered.']")
			WebElement duplicateEmailErrorMessage;
			//Update Speaker
			public @FindBy(xpath = "//div[text()='Speaker updated successfully.']")
			WebElement speakerUpdateSuccessMessage;
			public @FindBy(xpath = "//img[@width='100%']")
			WebElement speakerImageChenge;
			//Webinar Session Speaker Update Details and Delete 
			public @FindBy(xpath = "//h5[@class='mb-0 text-truncate']")
			List<WebElement> sessionSpeakersList;
			public @FindBy(xpath = "//div[@title='Delete']")
			List<WebElement> deleteSpeakerIcon;
			//All success messages Create, Update, Delete
			public @FindBy(xpath = "//div[text()='Speakers removed successfully.']")
			WebElement speakerDeleteSuccessMessage;
			
			public @FindBy(xpath = "//button[text()='Update']")
			WebElement updateButton;
			//Webinar Sponsors
			public @FindBy(xpath = "//small[text()='Sponsors']")
			WebElement sponsorsSection;
			public @FindBy(xpath = "//button[text()='Add New Sponsor']")
			WebElement addNewSponsorButton;
			// Sponsor form fields
			public @FindBy(name = "name")
			WebElement companyName;
			public @FindBy(name = "websiteUrl")
			WebElement companyWebsiteUrl;
			//Company's Logo and *Banner Image *
			public @FindBy(xpath = "//div[@class='fui-avatar-label'][contains(text(),'Select image')]")
			List<WebElement> sponsorlogoBannerImage;
			public @FindBy(name = "tier")
			WebElement sponsorTier;
			public @FindBy(name = "category")
			WebElement sponsorCategory;
			public @FindBy(css = ".notranslate.public-DraftEditor-content")
			WebElement sponsorDescription;
			//addExistingSponsors
			public @FindBy(xpath = "//button[text()='Add Existing Sponsors']")
			WebElement addExistingSponsorsButton;
			//Publish Webinar and Draft Webinar
			public @FindBy(xpath = "//button[text()='Publish']")
			WebElement publishButton;
			public @FindBy(xpath = "//button[text()='Yes, publish it!']")
			WebElement confirmPublishButton;
			public @FindBy(xpath = "//div[text()='Your event has been published successfully.']")
			WebElement eventPublishedSuccessMessage;
			public @FindBy(xpath = "//button[text()='Save as Draft']")
			WebElement saveAsDraftButton;
			public @FindBy(xpath = "//button[text()='Yes, save as draft!']")
			WebElement confirmSaveAsDraftButton;
			public @FindBy(xpath = "//div[text()='Your event has been saved as a draft.']")
			WebElement eventDraftSuccessMessage;
			//Success messages for Sponsor
			public @FindBy(xpath = "//div[text()='Sponsor added successfully.']")
			WebElement sponsorAddSuccessMessage;
			//existing Speakers
			public @FindBy(xpath = "//div[text()='Sponsors added successfully.']")
			WebElement sponsorsAddSuccessMessage;
			public @FindBy(xpath = "//div[text()='Sponsor updated successfully.']")
			WebElement sponsorUpdateSuccessMessage;
			public @FindBy(xpath = "//div[text()='Sponsor removed successfully.']")
			WebElement sponsorDeleteSuccessMessage;
			//Webinar session Video Upload
			public @FindBy(xpath = "//small[text()='Videos']")
			WebElement videosSection;
			public @FindBy(xpath = "//span[text()='Add Video']")
			WebElement addVideoButton;
			public @FindBy(xpath = "//button[text()='Upload Video']")
			WebElement uploadVideoButton;
			public @FindBy(xpath = "//button[text()='Yes, upload it!']")
			WebElement confirmUploadVideoButton;
			public @FindBy(xpath = "//div[text()='Upload completed successfully!']")
			WebElement videoUploadSuccessMessage;
			public @FindBy(xpath = "//button[@class='btn btn-sm '][@type='button']")
			WebElement deleteVideoButton;
			public @FindBy(xpath = "//button[text()='Yes']")
			WebElement confirmDeleteVideoButton;
			public @FindBy(xpath = "//div[text()='Video deleted successfully!']")
			WebElement videoDeleteSuccessMessage;
			
			public @FindBy(xpath = "//button[@type='submit']")
			WebElement submitButtonDetails;
			
			public @FindBy(xpath = "//a[5]//div[1]")
			WebElement zoomSection;
			
			public @FindBy(xpath = "//button[normalize-space()='Create New Meeting']")
			WebElement createMeetingButton;
			
			public @FindBy(xpath = "//input[@name='topicName']")
			WebElement topicName;
			
			public @FindBy(xpath = "//textarea[@name='agenda']")
			WebElement agenda;
			
			public @FindBy(xpath = "//button[@type='submit']")
			WebElement createMeetingSubmitButton;
			
			public @FindBy(xpath = "(//input[@placeholder='Select date & time'])[1]")
			WebElement startDateTime;
			
			public @FindBy(xpath = "(//input[@placeholder='Select date & time'])[2]")
			WebElement endDateTime;
			
			public @FindBy(xpath = "//small[normalize-space()='Registrants']")
			WebElement registrantSection;
			
			public @FindBy(xpath= "//button[normalize-space()='Add registrants']")
			WebElement addRegistrantButton;
			
			public @FindBy(xpath = "//div[contains(@class,'dropdown-heading-value')]")
			WebElement registrantDropdown;
			
			public @FindBy(xpath = "//button[normalize-space()='Add Registrants']")
			WebElement registrantSubmitButton;
			
			public @FindBy(xpath="//button[@class='btn-danger btn btn-secondary btn-sm']//*[name()='svg']")
			WebElement deleteRegistrantButton;
			
			
			public @FindBy(xpath = "//button[@title='Update Meeting']//*[name()='svg']")
			WebElement updateMeetingButton;
			
			public @FindBy(xpath = "//button[@title='Delete Meeting']")
		    WebElement deleteMeetingButton;
			
			
			
			
			
			
			
			
			
			
			
}

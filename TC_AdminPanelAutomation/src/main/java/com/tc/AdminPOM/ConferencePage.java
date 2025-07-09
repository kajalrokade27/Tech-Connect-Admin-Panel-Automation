package com.tc.AdminPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConferencePage {

	
	public ConferencePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public @FindBy(xpath="//img[@alt='logo-text']")
	WebElement techLogo;
	
	 public @FindBy(xpath="(//span[@class='d-block'])[1]")
	    WebElement EventDropdown;
	 
	 public @FindBy(xpath="(//span[@class='hide-mini'])[5]")
	    WebElement conferenceLink;
        
	 public @FindBy(xpath="//div[@class=' css-1hwfws3']")
	 WebElement industryTags;
	 
	 public @FindBy(xpath = "//span[text()='Webinars']")
     WebElement webinarsSection;
	 
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
	 
	 public @FindBy(xpath = "//div[@class='rdt rdtOpen']//span[contains(text(),'‹')]")
	 WebElement previousMonthButton;
	 
	 public @FindBy(xpath = "(//div[@class='mb-3'])[11]")
	 WebElement outside_Click;
	 
	 public @FindBy(xpath = "//input[@name='eventUrl']")
	 WebElement eventUrlField;
	 
	 public @FindBy(xpath = "//input[@name='zoomLink']")
	 WebElement zoomLinkField;
	 
	 public @FindBy(xpath = "//*[contains(text(), 'new conference created successfully')]")
	 WebElement successMessage;
	 
	 public @FindBy(xpath = "(//div[@class='card-body d-flex flex-column']/h6)[1]")
	 WebElement eventCardName;
	 
	 public @FindBy(xpath = "//button[contains(text(),'Publish')]")
	 WebElement publishButton;
	 
	 public @FindBy(xpath = "//button[contains(text(),'Yes, publish it!')]")
	 WebElement confirmPublishButton;
	 
	 public @FindBy(xpath = "//button[contains(text(),'Save as Draft')]")
	 WebElement saveAsDraftButton;
	 
	 public @FindBy(xpath = "//button[contains(text(),'Yes, save as draft!')]")
	 WebElement confirmSaveAsDraftButton;
	 
	 public @FindBy(xpath = "(//i[@class='bi bi-three-dots-vertical'])[1]")
	 WebElement threeDotsMenu;
	 
	 public @FindBy(xpath = "(//i[@class=\"bi bi-trash me-2\"])[1]")
	 WebElement deleteButton;
	 
	 public @FindBy(xpath = "(//button[@class='swal2-confirm swal2-styled swal2-default-outline'])")
	 WebElement confirmDeleteButton;
	 
	 public @FindBy(xpath = "//button[contains(text(),'Reset')]")
	 WebElement  resetButton;
	 
	 public @FindBy(xpath = "//div[contains(text(),'Your event has been published successfully.')]")
	 WebElement publishSuccessMessage;
	 
	 public @FindBy(xpath= "//div[contains(text(),'Your event has been saved as a draft.')]")
	 WebElement saveAsDraftSuccessMessage;
	 
	 public @FindBy(xpath = "//div[contains(text(),'Conference deleted successfully.')]")
	 WebElement conferenceDeleteSuccessMessage;
	 
	   //WebElements for the webinar details section
     //Webinars Overview Info image upload or change
     public @FindBy(xpath = "//div[@class='fui-avatar-main-container fui-avatar-border-2']")
     WebElement conferenceUpdateImage;
     public @FindBy(xpath = "//button[text()='Change']")
     WebElement changeImageButton;
     public @FindBy(xpath = "//div[text()='Conference image updated successfully.']")
     WebElement ConfImageChangeSuccess;
     public @FindBy(xpath = "//div[contains(text(),'Conference updated successfully.')]")
     WebElement conferenceUpdateSuccessMessage;
     
     @FindBy(xpath = "//div[contains(text(), 'image updated successfully.')]")
     public WebElement imageUpdatedMsg;
     
     @FindBy(xpath = "//div[contains(text(), 'updated successfully.')]")
     public WebElement eventUpdatedMsg;
     
     public @FindBy(xpath = "//button[@type='submit']")
     WebElement submitButton;
     
     public @FindBy(xpath="//a[2]//div[1]")
     WebElement detailsTab;
     
     //Create a session to get started
     public @FindBy(xpath = "//small[normalize-space()='Sessions']")
     WebElement sessionTab;
     public @FindBy(xpath = "//button[normalize-space()='Add New']")
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
     public @FindBy(xpath = "//div[@class='public-DraftStyleDefault-block public-DraftStyleDefault-ltr']")
     WebElement sessionDesc;
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
     WebElement speakerImageAdd;
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
     //Create new Speaker
     public @FindBy(xpath = "//div[text()='Speaker added successfully.']")
     WebElement speakerAddedSuccessMessage;
     
     
     //Update Speaker
     public @FindBy(xpath = "//input[@id='searchUser']")
     WebElement searchSpeakerField;
     
     public @FindBy(xpath = "//h5[@class='mb-0 text-truncate']")
     List<WebElement> speakerList;
     
     
     public @FindBy(xpath = "//div[text()='Speaker updated successfully.']")
     WebElement speakerUpdateSuccessMessage;
     public @FindBy(xpath = "//img[@width='100%']")
     WebElement speakerImageUpdate;
     //Webinar Session Speaker Update Details and Delete 
     public @FindBy(xpath = "//h5[@class='mb-0 text-truncate']")
     List<WebElement> sessionSpeakersList;
     public @FindBy(xpath = "//div[@title='Delete']")
     List<WebElement> deleteSpeakerIcon;
     //All success messages Create, Update, Delete
     public @FindBy(xpath = "//div[@class='Toastify__toast-container Toastify__toast-container--top-right']")
     WebElement successMessageContainer;
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
     
     public @FindBy(xpath = "//div[@class=' css-1hwfws3']")
     WebElement existingSponsorsDropdown;
     
     public @FindBy(xpath = "//div[contains(text(),'Sponsors added successfully.')]")
     WebElement sponsorsAddedSuccessMessage;
     
     public @FindBy(xpath="//div[contains(text(),'Sponsor added successfully.')]")
	 WebElement sponsorCreateSuccessMessage;
     
     public @FindBy(xpath = "//h5[@class='mb-0 text-truncate']")
     	 List<WebElement> sponsorsList;
     
     public @FindBy(xpath = "//img[@class='fui-avatar-image']")
     	 List<WebElement> updateSponsorImages;
	 
	 public @FindBy(xpath = "//div[contains(text(),'Sponsor updated successfully.')]")
	 WebElement sponsorUpdateSuccessMessage;
	 
     public @FindBy(xpath = "//i[@class='bi bi-trash']")
     	 List<WebElement> deleteSponsorIcon;
     
     public @FindBy(xpath = "//div[contains(text(),'Sponsor removed successfully.')]")
     	 WebElement sponsorDeleteSuccessMessage;
     
     public @FindBy(xpath = "//div[contains(text(),'Duplicate email found.')]")
     WebElement speakerDuplicateErrorMessage;
     
     
     public @FindBy(xpath = "//div[contains(text(),'Session time overlaps with existing sessions.')]")
     WebElement sessionTimeOverlapErrorMessage;
     
     public @FindBy(xpath = "//div[contains(@class, 'Toastify__toast--error')]//div[text()='Duplicate name found.']")
     WebElement duplicateSponsorNameErrorMessage;
     
     
     
     //Registration Section
     public @FindBy(xpath = "//small[normalize-space()='Registrations']")
     WebElement registrationsSection;
     
     public @FindBy(xpath = "//button[normalize-space()='Add new Ticket']")
     WebElement addNewTicketButton;
     
     public @FindBy(xpath = "//input[contains(@name,'type')]")
     WebElement ticketName;
     
     public @FindBy(xpath = "//input[@name='description']")
     WebElement ticketDesc;
     
     public @FindBy(xpath = "//input[@name='price']")
     WebElement ticketPrice;
     
     public @FindBy(xpath = "//input[@placeholder='Select date']")
     WebElement ticketExpData;
     
     public @FindBy(xpath = "//input[@name='quantity']")
     WebElement ticketQuantity;
     
     public @FindBy(xpath = "//div[contains(text(),'Ticket added successfully.')]")
     WebElement ticketAddSuccessMessage;
     
     public @FindBy(xpath = "//button[contains(@class,'btn btn-light btn-sm text-success')]//*[name()='svg']")
     WebElement editTicketButton;
     
     public @FindBy(xpath = "//div[contains(text(),'Ticket updated successfully.')]")
     WebElement ticketUpdateSuccessMessage;
     
     public @FindBy(xpath = "//*[name()='line' and contains(@x1,'10')]")
     WebElement deleteTicketButton;
     
     public @FindBy(xpath = "//div[contains(text(),'Ticket deleted successfully.')]")
     WebElement ticketDeleteSuccessMessage;
     
     public @FindBy(xpath = "//div[contains(text(),'Ticket already exists with same name.')]")
     WebElement duplicateTicketNameErrorMessage;
     
   // Video Upload
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
     
     //purchased info
     public @FindBy(xpath = "//div[@class='d-flex gap-2 justify-content-between']//*[name()='svg']")
     WebElement editPaymentStatusButton;
     
     public @FindBy(xpath = "//select[@name='status']")
     WebElement statusDropdown;
     
     public @FindBy(xpath = "//button[@type='submit']")
     WebElement changeStatus;
     
     public @FindBy(xpath = "//div[contains(text(),'Status updated successfully.')]")
     WebElement statusUpdateSuccessMessage;
     
     public @FindBy(xpath = "//small[normalize-space()='Attendees']")
     WebElement attendeesSection;
     
     public @FindBy(xpath = "//small[normalize-space()='Purchased Info']")
     WebElement purchasedInfoSection;
     
     public @FindBy(xpath = "//div[@class='card-body']")
	 List<WebElement> filter_Cards;
	
	 
	 public @FindBy(xpath = "//table[@class='align-middle table']/tbody/tr")
	 List<WebElement> attendeesList;
	 
	 public @FindBy(xpath = "//table[@class='align-middle table']/tbody/tr/td[2]")
	 List<WebElement> attendeeNames;
	 
	 public @FindBy(xpath = "//table[@class='align-middle table']/tbody/tr/td[3]")
	 List<WebElement> attendeeEmail;
	 
	 public @FindBy(xpath="//table[@class='align-middle table']/tbody/tr/td[4]")
	 List<WebElement> attendeeTicketName;
	 
	 public @FindBy(xpath="//table[@class='align-middle table']/tbody/tr/td[5]")
	 List<WebElement> AttendeeTicketPrice;
	 
	 public @FindBy(xpath="//table[@class='align-middle table']/tbody/tr/td[6]")
	 List<WebElement> amount;
	 
	 public @FindBy(xpath="//table[@class='align-middle table']/tbody/tr/td[6]")
	 List<WebElement> purchaseDate;
	 
	 
	 //Dashboard elements
	 public @FindBy(xpath = "//span[@class='ms-2']")
	 List<WebElement> dashboardElements;
	 
	 //conference page 
	 public @FindBy(xpath = "//div[@class='card-body']")
	 List<WebElement> conferenceCards;
	 
	 public @FindBy(xpath = "div[@class='card-title mb-3']")
	 List<WebElement> conferenceEvents;
	 
	 
	public @FindBy(xpath="//div[@class='d-flex flex-column']/span[2]")
	WebElement fullDateRange;
	
	public @FindBy(xpath="//a[@class='text-decoration-none text-black']")
	List<WebElement> eventInfoCards;  // session - 2 speaker 3, sponsor-4
	
	public @FindBy(xpath="//strong[@class='fs-6']")
	List<WebElement> eventNumbers;
	
	public @FindBy(xpath="//span[@class='text-muted']")
	List<WebElement> eventNumbersLabels;
	
	public @FindBy(xpath="//div[@class='card-body']")
	List<WebElement> sessionList;
	
	public @FindBy(xpath="//h5[@class='mb-0 text-truncate']")
	List<WebElement> sponsorList;
	
	public @FindBy(xpath="//span[@class='apexcharts-legend-text']")
	List<WebElement> breakdownList;
	
	public @FindBy(xpath="//div[@class='d-flex gap-2 justify-content-between']/span")
	List<WebElement> attendeeStatusList;
	
	 public @FindBy(xpath = "//div[@class='card-body d-flex flex-column']/h6")
	List< WebElement> allConferenceCards;
	
	 public @FindBy(xpath = "//button[contains(@aria-label,'Next')]//*[name()='svg']")
	 WebElement nextPageButton;
	 
	 public @FindBy(xpath = "//div[@class='card-body']/h2")
	 List<WebElement> filterNumbers;
	
	
	
	
	
	 
	 
     
     
     
     
}
	 
	 
	 
	 
	 
	 


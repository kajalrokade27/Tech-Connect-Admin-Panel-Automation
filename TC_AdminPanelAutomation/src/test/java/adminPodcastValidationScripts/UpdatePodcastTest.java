package adminPodcastValidationScripts;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.FileUtility.SheetName;
import com.TechConnect.FileUtility.UniversalDataProvider;
import com.tc.AdminPOM.CommonEventPage;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.ModeratorPage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

import AdminCommonEventActions.EventsActionsTest;

public class  UpdatePodcastTest extends AdminBaseClass
{
	 ConferencePage cp;
	 WorkshopPage wp;
	 EventsActionsTest et;
	 ModeratorPage mp;
	 CommonEventPage commonEp;
	@BeforeClass
	public void setUp() throws IOException, InterruptedException 
	{
		     wp = new WorkshopPage(driver);
		    cp = new ConferencePage(driver); 
		    et = new EventsActionsTest();
		    mp = new ModeratorPage(driver);
		    commonEp = new CommonEventPage(driver);
		    ActionClass.click(wp.EventDropdown);
		    ActionClass.click(commonEp.podcastSection);
		   et.searchEvent(GetPropertyData.propData("Podcast1").split("~")[1]);
		   ActionClass.click(cp.eventCardName);
		   ActionClass.click(cp.detailsTab);
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 3)
	  @SheetName("Podcast")
 		public void updatePodcast(String[]data) throws IOException, InterruptedException, AWTException
 		{
		    ActionClass.click(commonEp.webinarDetailsImage);
		    ActionClass.uploadFile(data[11]);
		    ActionClass.click(commonEp.changeImageButton);
		    ActionClass.verifyToastMessage1(wp.toastMessage, commonEp.cancelButton, "Webinar image updated successfully", false);
		    ActionClass.uploadFile(data[11]);
 			et.fillCommonEventUpdateForm(data);
 			ActionClass.implicitWait();
 		    ActionClass.click(commonEp.submitButtonDetails);
 			ActionClass.verifyToastMessage1(wp.toastMessage, commonEp.cancelButton, data[1], false);
 	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 4)
	  @SheetName("Podcast")
	public void addSession(String[]data) throws IOException, InterruptedException
	{
		et.addSessions("addSession");
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 5)
	  @SheetName("Podcast")
	public void updateSession(String[]data) throws IOException, InterruptedException
	{
		et.updateSession("updateSession");
		
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 6)
	  @SheetName("Podcast")
	public void deleteSession(String[]data) throws IOException, InterruptedException
	{
		et.deleteSession();
		
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 7)
	  @SheetName("Podcast")
	public void addSpeaker(String[]data) throws IOException, InterruptedException, AWTException
	{
		wp= new WorkshopPage(driver);
		et.addNewSpeakers("addSpeaker");
		
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 9)
	  @SheetName("Podcast")
	public void updateSpeaker(String[]data) throws IOException, InterruptedException, AWTException
	{
		Reporter.log("Update Webinar Speaker",true);
		et.updateSpeaker("updateSpeaker");
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 8, retryAnalyzer = com.tech_Connect.Action.ActionClass.class)
	  @SheetName("Podcast")
	public void adSpeaker(String[]data) throws IOException, InterruptedException, AWTException
	{
		et.addExistingSpeakers("existingSpeaker");
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 10)
	  @SheetName("Podcast")
	public void deleteSpeaker(String[]data) throws IOException, InterruptedException
	{
		et.deleteSpeaker();
		
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 11)
	  @SheetName("PanelDiscussion")
	public void addSponsor(String[]data) throws IOException, InterruptedException, AWTException
	{
		et.addNewSponsor("addSponsor");
		
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 12)
	  @SheetName("PanelDiscussion")
	public void addExistingSponsor(String[]data) throws IOException, InterruptedException, AWTException
	{
		et.addExistingSponsors("addSponsorName");
		
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 13)
    @SheetName("UpdateSponsor")
	public void updateSponsor(String[]data) throws IOException, InterruptedException, AWTException
	{
		et.sponsorUpdate(data);
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 14)
	  @SheetName("Podcast")
	public void deleteSponsor(String[]data) throws IOException, InterruptedException
	{
		et.deleteSponsor();
		
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 15)
	  @SheetName("Podcast")
	public void publishWebinar(String[]data) throws IOException, InterruptedException
	{
		et.performPublish();
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 16)
	  @SheetName("PanelDiscussion")
	public void saveWebinarAsDraft(String[]data) throws IOException, InterruptedException
	{
		et.performSaveAsDraft();
	}
	
	
}

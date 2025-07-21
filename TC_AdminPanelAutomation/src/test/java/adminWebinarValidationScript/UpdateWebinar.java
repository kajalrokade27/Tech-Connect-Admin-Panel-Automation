package adminWebinarValidationScript;

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

public class UpdateWebinar extends AdminBaseClass
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
		    ActionClass.click(commonEp.webinarsSection);
		   et.searchEvent(GetPropertyData.propData("Webinar1").split("~")[1]);
		   ActionClass.click(cp.eventCardName);
		   ActionClass.click(cp.detailsTab);
	}
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 3)
	  @SheetName("Webinar")
 		public void updateWebinar(String[]data) throws IOException, InterruptedException, AWTException
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
	  @SheetName("Webinar")
	public void addSession(String[]data) throws IOException, InterruptedException
	{
		et.addSessions("addSession");
		ActionClass.verifyToastMessage1(wp.toastMessage, commonEp.cancelButton, data[1], false);
	}
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 5)
	  @SheetName("Webinar")
	public void updateWebinarSession(String[]data) throws IOException, InterruptedException
	{
		et.updateSession("updateSession");
		ActionClass.verifyToastMessage1(wp.toastMessage, commonEp.cancelButton, data[1], false);
	}
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 6)
	  @SheetName("Webinar")
	public void deleteWebinarSession(String[]data) throws IOException, InterruptedException
	{
		et.deleteSession();
		ActionClass.verifyToastMessage1(wp.toastMessage, commonEp.cancelButton, data[1], false);
	}
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 7)
	  @SheetName("Webinar")
	public void addSpeaker(String[]data) throws IOException, InterruptedException, AWTException
	{
		wp= new WorkshopPage(driver);
		et.addNewSpeakers("addSpeaker");
		ActionClass.verifyToastMessage1(wp.toastMessage, commonEp.cancelButton, data[1], false);
	}
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 8)
	  @SheetName("Webinar")
	public void updateWebinarSpeaker(String[]data) throws IOException, InterruptedException, AWTException
	{
		Reporter.log("Update Webinar Speaker",true);
		et.updateSpeaker("updateSpeaker");
		Reporter.log("" + data[1] + " Speaker updated successfully", true);
		
		
	}
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 9)
	  @SheetName("Webinar")
	public void addExistingSpeaker(String[]data) throws IOException, InterruptedException, AWTException
	{
		et.addExistingSpeakers("existingSpeaker");
	}
	
	
}

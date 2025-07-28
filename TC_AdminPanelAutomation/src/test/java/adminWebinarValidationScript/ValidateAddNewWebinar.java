package adminWebinarValidationScript;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.SheetName;
import com.TechConnect.FileUtility.UniversalDataProvider;
import com.tc.AdminPOM.CommonEventPage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

import AdminCommonEventActions.EventsActionsTest;

public class ValidateAddNewWebinar extends AdminBaseClass{
	
	EventsActionsTest eventsActionsTest;
	public CommonEventPage commonEventPage;
	WorkshopPage wp;
	@BeforeClass
	public void setup() {
		eventsActionsTest = new EventsActionsTest();
		commonEventPage = new CommonEventPage(driver);
		wp = new WorkshopPage(driver);
		eventsActionsTest.setup();
		ActionClass.click(commonEventPage.webinarsSection);
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 3)
	  @SheetName("Webinar")
	public void addNewWebinar(String[] data) throws InterruptedException, IOException, AWTException 
	{
		commonEventPage = new CommonEventPage(driver);
		ActionClass.click(commonEventPage.addNewButton);
		eventsActionsTest.fillCommonEventForm(data);
		ActionClass.scrollToElement(commonEventPage.webinarImage);
		ActionClass.click(commonEventPage.webinarImage);
		ActionClass.uploadFile(data[11]);
		ActionClass.implicitWait();
		ActionClass.scrollToElement(commonEventPage.submitButton);
		ActionClass.click(commonEventPage.submitButton);
		ActionClass.verifyToastMessage1(wp.toastMessage, commonEventPage.cancelButton, data[1],false);
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 4)
	  @SheetName("Webinar")
	public void deleteWebinar(String[] data) throws IOException, InterruptedException {
		eventsActionsTest.searchEvent(data[1]);
		eventsActionsTest.performDelete();
		ActionClass.verifyToastMessage1(wp.toastMessage, commonEventPage.cancelButton, data[1],false);
	    
	}
	
   
}

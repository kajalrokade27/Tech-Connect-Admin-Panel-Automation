package adminSponsorValidationScript;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.SheetName;
import com.TechConnect.FileUtility.UniversalDataProvider;
import com.tc.AdminPOM.SpeakerPage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

public class InviteSpeakersValidation extends AdminBaseClass
{
	  SpeakerPage sp;
	  WorkshopPage wp;
	  @BeforeClass
	  public void setup() 
	  {
		  sp = new SpeakerPage(driver);
		  wp = new WorkshopPage(driver);
		  ActionClass.click(sp.speakerInvitionButton);
	  }
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 1)
	  @SheetName("SpeakerInvitation")
	public void sendSpeakersValidation(String[] testData) throws InterruptedException
	{
       ActionClass.click(sp.addNewButton);
       ActionClass.enterText(sp.speakerName, testData[0]);
       ActionClass.enterText(sp.speakerEmail, testData[1]);
       ActionClass.click(sp.submitButton);
       ActionClass.verifyToastMessage1(wp.toastMessage, sp.cancelButton, "Speaker",true);
	}
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 2)
	  @SheetName("SpeakerInvitation")
	public void resendSpeakerInvitation(String[] testData) throws InterruptedException
	{
		ActionClass.enterText(sp.searchField, testData[0]);
		ActionClass.pressEnter();
		if (sp.speakerNameList.isEmpty()) {
			throw new AssertionError("No speakers found with the name: " + testData[0]);
		}
		ActionClass.click(sp.resendButton.get(0));
		ActionClass.click(sp.confirmButton);
		ActionClass.verifyToastMessage1(wp.toastMessage, sp.cancelButton, "Speaker",false);
	}
	
	@Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 3)
	  @SheetName("SpeakerInvitation")
	public void deleteSpeakerInvitation(String[] testData) throws InterruptedException
	{
		ActionClass.enterText(sp.searchField, testData[0]);
		ActionClass.pressEnter();
		if (sp.speakerNameList.isEmpty()) {
			Reporter.log("No speakers found with the name: " + testData[0]);
		}
		ActionClass.click(sp.deleteButton.get(0));
		ActionClass.click(sp.confirmButton);
		ActionClass.verifyToastMessage1(wp.toastMessage, sp.cancelButton, "Speaker",false);
	}
	
}

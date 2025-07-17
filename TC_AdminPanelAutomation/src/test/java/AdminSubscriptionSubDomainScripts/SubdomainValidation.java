package AdminSubscriptionSubDomainScripts;
import java.io.IOException;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.SheetName;
import com.TechConnect.FileUtility.UniversalDataProvider;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.SubscriptionPlanPage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;
import AdminCommonEventActions.EventsActionsTest;

public class SubdomainValidation extends AdminBaseClass
{
	SubscriptionPlanPage sp;
	WorkshopPage wp;
	ConferencePage cp;
	 EventsActionsTest eventActions;
  @BeforeClass
  public void setup() 
  {
	sp = new SubscriptionPlanPage(driver);
    wp = new WorkshopPage(driver);
    cp = new ConferencePage(driver);
 eventActions = new EventsActionsTest();
	ActionClass.click(sp.subDomainLink);
 }  
  
  public void fillSubdomainForm(String[] testData) throws InterruptedException
  {
	  ActionClass.enterText(sp.subDomainName, testData[0]);
	  ActionClass.typeUsingActions(sp.subDomainEvent, testData[1]);
	  ActionClass.selectByVisibleText(sp.publishOnDomainDropdown, testData[2]);
	  ActionClass.click(sp.updateButton);
  }
  
  @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 1)
  @SheetName("SubDomain")
  public void addSubdomainValidation(String[] testData) throws InterruptedException
  {
	  ActionClass.click(sp.addNewButton);
	  fillSubdomainForm(testData);
	  ActionClass.verifyToastMessage1(wp.toastMessage, sp.cancelButton, testData[0], true);
  }
  @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 2)
  @SheetName("SubDomain")
  public void updateSubdomainValidation(String[] testData) throws InterruptedException
  {
	  ActionClass.enterText(sp.subDomainSearchField, testData[0]);
	  ActionClass.pressEnter();
	  if (sp.subDomainList.isEmpty()) {
		  Reporter.log("No subdomains found with the name: " + testData[0], true);
	  } else {
		  ActionClass.click(sp.subDomainList.get(0));
		  fillSubdomainForm(testData);
		  
		  ActionClass.verifyToastMessage1(wp.toastMessage, sp.cancelButton, testData[0], true);
	  }}
  
  @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 3)
  @SheetName("SubDomain")
  public void deleteSubdomainValidation(String[] testData) throws InterruptedException, IOException
  {
	      ActionClass.enterText(sp.subDomainSearchField, testData[0]);
	      ActionClass.pressEnter();
	      if (sp.subDomainList.isEmpty()) {
	    	  Reporter.log("No subdomains found with the name: " + testData[0], true);
	      }
	      else
	      {
	      ActionClass.click(sp.deleteButton.get(0));
		  ActionClass.click(cp.confirmDeleteButton);
		  ActionClass.verifyToastMessage1(wp.toastMessage, sp.cancelButton, testData[0], false);
	  }}
  }
		  


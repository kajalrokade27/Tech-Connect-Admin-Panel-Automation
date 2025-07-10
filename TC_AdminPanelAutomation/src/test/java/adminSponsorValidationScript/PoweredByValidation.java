package adminSponsorValidationScript;
import java.awt.AWTException;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.SheetName;
import com.TechConnect.FileUtility.UniversalDataProvider;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.SponsorsPage;
import com.tech_Connect.Action.ActionClass;

public class PoweredByValidation extends AdminBaseClass
{
	SponsorsPage sp; 
	ConferencePage  cp;
  @BeforeClass
  public void setup() 
  {
	sp = new SponsorsPage(driver);
	cp = new ConferencePage(driver);
	ActionClass.click(cp.EventDropdown);
	ActionClass.click(sp.poweredByLink);
  }
  
  @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 1)
  @SheetName("AddPoweredBy")
  public void addPoweredByValidation(String[] testData) throws InterruptedException, AWTException
  {
	  ActionClass.click(sp.addNewButton);
	  ActionClass.enterText(sp.poweredByNameField, testData[0]);
	  ActionClass.enterText(sp.poweredByWebsiteField, testData[1]);
	  ActionClass.click(sp.poweredByLogo);
	  ActionClass.uploadFile(testData[2]);
	  ActionClass.implicitWait();
	  ActionClass.jsClick(sp.submitButton);
	  ActionClass.waitUptoVisible(sp.toastMessage);
	  String toast = sp.toastMessage.getText().toLowerCase();
	  if (toast.contains("success") || toast.contains("duplicate") || toast.contains("already exists")) {
		  Reporter.log("Toast message: " + toast, true);
	  } else {
		  throw new AssertionError("Unexpected message: " + toast);
	  }
  }
  
  @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 2)
  @SheetName("AddPoweredBy")
  public void updatePoweredByValidation(String[] testData) throws InterruptedException, AWTException
  {
	  ActionClass.enterText(sp.searchField, testData[0]);
	  ActionClass.pressEnter();
	  ActionClass.click(sp.poweredByList.get(0));
	  ActionClass.enterText(sp.poweredByNameField, testData[0]);
	  ActionClass.enterText(sp.poweredByWebsiteField, testData[1]);
	  ActionClass.click(sp.updateCompanyLogo);
	  ActionClass.uploadFile(testData[2]);
	  ActionClass.click(sp.updateButton);
	  ActionClass.waitUptoVisible(sp.toastMessage);
	  String toast = sp.toastMessage.getText().toLowerCase();
	  if (toast.contains("success") || toast.contains("duplicate") || toast.contains("already exists")) {
		  Reporter.log("Toast message: " + toast, true);
	  } else {
		  throw new AssertionError("Unexpected message: " + toast);
	  }
	  ActionClass.implicitWait();
  }
  @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 3)
  @SheetName("AddPoweredBy")
  public void deletePoweredByValidation(String[] testData) throws InterruptedException, AWTException
  {
	  ActionClass.enterText(sp.searchField, testData[0]);
	  ActionClass.pressEnter();
	  ActionClass.click(sp.poweredByList.get(0));
	  ActionClass.click(sp.deleteButtons.get(0));
	  ActionClass.click(cp.confirmDeleteButton);
	  ActionClass.waitUptoVisible(sp.toastMessage);
	  String toast = sp.toastMessage.getText().toLowerCase();
	  if (toast.contains("success") || toast.contains("duplicate") || toast.contains("already exists")) {
		  Reporter.log("Toast message: " + toast, true);
	  } else {
		  throw new AssertionError("Unexpected message: " + toast);
	  }
  }
}

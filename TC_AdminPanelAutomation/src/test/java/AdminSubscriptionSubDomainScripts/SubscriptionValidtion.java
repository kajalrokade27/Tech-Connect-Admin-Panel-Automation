package AdminSubscriptionSubDomainScripts;

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

public class SubscriptionValidtion extends AdminBaseClass
{
	  SubscriptionPlanPage sp;
	  WorkshopPage wp;
	  ConferencePage cp;
	  
  @BeforeClass
  public void setup()
  {
	   sp = new SubscriptionPlanPage(driver);
	   wp = new WorkshopPage(driver);
	   cp = new ConferencePage(driver);
	   ActionClass.click(sp.subscriptionPlanLink);
  }
  
  public void fillSubPlanForm(String[]testData) throws InterruptedException
  {
	  	 
	  ActionClass.enterText(sp.planType, testData[0]);
	  ActionClass.enterText(sp.planDescription, testData[1]);
	  ActionClass.enterText(sp.planAmount, testData[2]);
	  ActionClass.selectByVisibleText(sp.planCurrency, testData[3]);
	  ActionClass.enterText(sp.planBenefits, testData[4]);
	  }
  
  @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 1)
  @SheetName("SubscriptionPlan")
  public void addSubscriptionPlanValidation(String[] testData) throws InterruptedException
  {
	  ActionClass.click(sp.addNewButton);
	  fillSubPlanForm(testData);
	  ActionClass.click(sp.submitButton);
	  ActionClass.verifyToastMessage1(wp.toastMessage, sp.cancelButton, testData[0], true);
  }
  @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 2)
  @SheetName("UpdatePlan")
  public void updateSubscriptionPlanValidation(String[] testData) throws InterruptedException
  {
	  ActionClass.enterText(sp.searchField, testData[0]);
	  ActionClass.pressEnter();
	  if (sp.planNameList.isEmpty()) {
		  Reporter.log("No subscription plans found with the name: " + testData[0]);
	  }
	  ActionClass.click(sp.planNameList.get(0));
	  fillSubPlanForm(testData);
	  ActionClass.click(sp.updateButton);
	  ActionClass.verifyToastMessage1(wp.toastMessage, sp.cancelButton,testData[0], true);
  }
  
  @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 3)
  @SheetName("UpdatePlan")
  public void deleteSubscriptionPlanValidation(String[] testData) throws InterruptedException
  {
	  ActionClass.enterText(sp.searchField, testData[0]);
	  ActionClass.pressEnter();
	  if (sp.planNameList.isEmpty()) {
		  Reporter.log("No subscription plans found with the name: " + testData[0]);
	  }
	  ActionClass.click(sp.deleteButton.get(0));
	  ActionClass.click(cp.confirmDeleteButton);
	  ActionClass.verifyToastMessage1(wp.toastMessage, sp.cancelButton, testData[0], false);
  }
} 

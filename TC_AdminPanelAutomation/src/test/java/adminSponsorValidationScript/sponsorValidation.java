package adminSponsorValidationScript;
import java.awt.AWTException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.SheetName;
import com.TechConnect.FileUtility.UniversalDataProvider;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.SponsorsPage;
import com.tech_Connect.Action.ActionClass;

public class sponsorValidation extends AdminBaseClass {
	SponsorsPage sp;
	ConferencePage cp ;
	@BeforeClass
	public void setup() throws IOException {
		    sp = new SponsorsPage(driver);
		    cp = new ConferencePage(driver);
	        ActionClass.click(sp.sponsors_link);
	}
    
    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 1)
    @SheetName("AddSponsor")
    public void sponsorValidationTest(String[] testData) throws InterruptedException, AWTException, IOException {
       
        ActionClass.click(sp.addSponsorButton);
        Thread.sleep(2000);
        ActionClass.enterText(sp.sponsor_fields.get(0), testData[0]);
        ActionClass.enterText(sp.sponsor_fields.get(1), testData[1]);
        ActionClass.enterText(sp.desc_field, testData[4]);
        
        // Upload files
        ActionClass.click(sp.sponsor_logo);
        ActionClass.uploadFile(testData[2]);
        ActionClass.click(sp.bannerImage);
        ActionClass.uploadFile(testData[3]);
        
        // Select drop downs
        ActionClass.selectByVisibleText(sp.spons_drop.get(0), testData[6]);
        ActionClass.selectByVisibleText(sp.spons_drop.get(1), testData[5]);
        
        // Submit and validate
        ActionClass.click(sp.submitButton);
        ActionClass.waitUptoVisible(sp.toastMessage);
        String toast = sp.toastMessage.getText().toLowerCase();
        Assert.assertTrue(toast.contains("success") || toast.contains("duplicate") || toast.contains("already exists"), 
                         "Unexpected message: " + toast);
        Reporter.log("Sponsor creation message: " + toast, true);
    }
    
    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class,priority = 2)
    @SheetName("AddSponsor")
    public void updateSponsorsValidations(String[] testData) throws InterruptedException, AWTException
    {

        ActionClass.enterText(sp.searchField, testData[0]);
        ActionClass.pressEnter();
        ActionClass.click(sp.sponsors_list.get(0));
        Thread.sleep(2000);
        ActionClass.enterText(sp.sponsor_fields.get(0), testData[0]);
        ActionClass.enterText(sp.sponsor_fields.get(1), testData[1]);
        
        
        // Upload files
        ActionClass.scrollToElement(sp.updateSponsorImages.get(1));
        ActionClass.click(sp.updateSponsorImages.get(0));
        ActionClass.uploadFile(testData[2]);
        ActionClass.click(sp.updateSponsorImages.get(1));
        ActionClass.uploadFile(testData[3]);
        
        // Select drop downs
        ActionClass.scrollToElement(sp.spons_drop.get(0));
        
        ActionClass.enterText(sp.desc_field, testData[4]);
        ActionClass.implicitWait();
        ActionClass.selectByVisibleText(sp.spons_drop.get(0), testData[6]);
        ActionClass.selectByVisibleText(sp.spons_drop.get(1), testData[5]);
        
        
        
        // Submit and validate
        ActionClass.click(sp.updateButton);
        ActionClass.waitUptoVisible(sp.toastMessage);
        
        String toast = sp.toastMessage.getText().toLowerCase();
        Assert.assertTrue(toast.contains("success") || toast.contains("duplicate") || toast.contains("already exists"), 
                         "Unexpected message: " + toast);
        Reporter.log("Sponsor creation message: " + toast, true);
         
    }
    
    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class,priority = 3)
    @SheetName("AddSponsor")
    public void deleteSponsorValidation(String[] testData) throws InterruptedException, AWTException
	{
		
		ActionClass.click(sp.sponsors_link);
		ActionClass.enterText(sp.searchField, testData[0]);
		ActionClass.pressEnter();
		Thread.sleep(2000);
		
		ActionClass.click(sp.deleteSponsorIcon.get(0));
		ActionClass.click(cp.confirmDeleteButton);
		Thread.sleep(2000);
		
		ActionClass.waitUptoVisible(sp.toastMessage);
		
		String toast = sp.toastMessage.getText().toLowerCase();
		Assert.assertTrue(toast.contains("success"), "Unexpected message: " + toast);
		Reporter.log("Sponsor deletion message: " + toast, true);
	}
    
}

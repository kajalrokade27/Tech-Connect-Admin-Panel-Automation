package adminModeratorValidation;
import java.awt.AWTException;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.SheetName;
import com.TechConnect.FileUtility.UniversalDataProvider;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.ModeratorPage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

public class ModeratorValidation extends AdminBaseClass {
    ModeratorPage mp;
    ConferencePage cp;
    WorkshopPage wp;

    @BeforeClass
    public void setup() {
        mp = new ModeratorPage(driver);
        cp = new ConferencePage(driver);
        wp = new WorkshopPage(driver);
        mp.moderatorLink.click();
    }

    private void fillModeratorForm(String[] data) throws InterruptedException, AWTException {
        ActionClass.enterText(mp.nameField, data[0]);
        ActionClass.enterText(mp.emailField, data[3]);
        ActionClass.scrollToElement(mp.categoryDropdown);
        ActionClass.enterText(mp.positionField, data[1]);
        ActionClass.enterText(mp.linkedInField, data[2]);
        
        ActionClass.selectByVisibleText(mp.categoryDropdown, data[4]);
        ActionClass.enterText(mp.aboutField, data[6]);
    }

    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 1)
    @SheetName("AddModeratorData")
    public void validateAddModeratorFunctionality(String[] data) throws InterruptedException, AWTException {
        ActionClass.click(mp.addNewButton);
        fillModeratorForm(data);
        ActionClass.scrollToElement(mp.addImage);
        ActionClass.click(mp.addImage);
        ActionClass.uploadFile(data[5]); // Assuming the 6th data is the image path
        ActionClass.implicitWait();
        ActionClass.click(mp.submitButton);
        ActionClass.verifyToastMessage1(wp.toastMessage, mp.cancelButton, data[0], true);
    }

    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 2)
    @SheetName("UpdateModeratorData")
    public void validateUpdateModeratorFunctionality(String[] data) throws InterruptedException, AWTException {
        ActionClass.enterText(mp.searchField, data[0]); // Assuming search by name
        if(mp.moderatorList.isEmpty()) {
        	Reporter.log("No moderators found with the name: " + data[0], true);
        }
        
        else {	
        ActionClass.pressEnter();
        ActionClass.click(mp.moderatorList.get(0)); // Click on the first moderator in the list
        fillModeratorForm(data);
        ActionClass.scrollToElement(mp.updateImage);
        ActionClass.click(mp.updateImage);
        ActionClass.uploadFile(data[5]); // Assuming the 6th data is the image path
        ActionClass.implicitWait();
        ActionClass.scrollToElement(mp.submitButton);
        ActionClass.click(mp.submitButton);
        ActionClass.verifyToastMessage1(wp.toastMessage, mp.cancelButton, data[0], true);
    }}
    
    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 3)
    @SheetName("UpdateModeratorData")
    public void validateDeleteModeratorFunctionality(String[] data) throws InterruptedException, AWTException {
		ActionClass.enterText(mp.searchField, data[0]); // Assuming search by name
		if(mp.moderatorList.isEmpty()) {
			Reporter.log("No moderators found with the name: " + data[0], true);
		}
		else {	
		ActionClass.pressEnter();
		ActionClass.click(mp.deleteIcons.get(0));
		ActionClass.click(cp.confirmDeleteButton);
		ActionClass.verifyToastMessage1(wp.toastMessage, mp.cancelButton, data[0], true);
	}}
}

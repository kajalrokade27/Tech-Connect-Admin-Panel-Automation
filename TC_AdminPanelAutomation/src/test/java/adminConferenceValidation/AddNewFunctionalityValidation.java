package adminConferenceValidation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

public class AddNewFunctionalityValidation extends AdminBaseClass
{

	@DataProvider(name = "eventData")
	public Object[][] getEventData() throws IOException {
	    List<Object[]> dataList = new ArrayList<>();
	    // Define keys for each event in your property file
	    String[] keys = {"Workshop1"}; // Update with your actual keys

	    for (String key : keys) {
	        String value = GetPropertyData.propData(key); // e.g., "AI Automation Testing:AI workshop:Pune:metR:Access of all the sessions"
	        String[] parts = value.split(":");
	        if (parts.length == 6) {
	            dataList.add(new Object[]{parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]});
	        }
	    }
	    return dataList.toArray(new Object[0][0]);
	}

	@Test(dataProvider = "eventData")
	public void addNewFunctionalityTest(String eventName, String description, String location,String industry,  String poweredBy, String regBenefits) throws InterruptedException, AWTException {
	    ConferencePage cp = new ConferencePage(driver);
	    WorkshopPage wp = new WorkshopPage(driver);

	    ActionClass.click(cp.EventDropdown);
	    ActionClass.click(cp.conferenceLink);
	    ActionClass.click(wp.addnew_button);
	    ActionClass.click(wp.event_cat_dropdown);
	    Select se1 = new Select(wp.event_cat_dropdown);
	    se1.selectByIndex(0);
	    ActionClass.enterText(wp.name_field, eventName);

	    Select se2 = new Select(wp.event_type_dropdown);
	    se2.selectByIndex(0);

	    Select se3 = new Select(wp.event_scope_dropdown);
	    se3.selectByIndex(1);

	    Select se4 = new Select(wp.powered_by_dropdown);
	    se4.selectByVisibleText(poweredBy);

	    ActionClass.enterText(wp.description_field, description);
	    Thread.sleep(2000);
	    ActionClass.scrollToElement(wp.location_field);
	    Thread.sleep(2000);
	    
        ActionClass.click(cp.industryTags);
        Thread.sleep(2000);
	    ActionClass.enterText(cp.industryTags, industry);
	    Thread.sleep(2000);
	    ActionClass.pressEnter();
       
	    ActionClass.enterText(wp.location_field, location);
	    ActionClass.click(wp.start_date);
	    ActionClass.click(wp.apr25);

	    ActionClass.click(wp.end_date);
	    ActionClass.click(wp.apr30);
	    Thread.sleep(2000);

	    Actions actions = new Actions(driver);
	    actions.sendKeys(Keys.ESCAPE).perform();
	    Thread.sleep(1000);

	    actions.sendKeys(Keys.ESCAPE).perform();
	    wp.workshop_image.click();
	    StringSelection filePathSelection = new StringSelection("C:\\Users\\Admin\\Pictures\\cropped-workshop images");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathSelection, null);
	    Thread.sleep(3000);

	    Robot rb = new Robot();
	    rb.keyPress(KeyEvent.VK_CONTROL);
	    rb.keyPress(KeyEvent.VK_V);
	    rb.keyRelease(KeyEvent.VK_V);
	    rb.keyRelease(KeyEvent.VK_CONTROL);

	    Thread.sleep(1000);
	    rb.keyPress(KeyEvent.VK_ENTER);
	    rb.keyRelease(KeyEvent.VK_ENTER);
	    Thread.sleep(5000);
   	    ActionClass.enterText(wp.reg_benefits, regBenefits);

	   ActionClass.click(wp.submit_button);
	}

}

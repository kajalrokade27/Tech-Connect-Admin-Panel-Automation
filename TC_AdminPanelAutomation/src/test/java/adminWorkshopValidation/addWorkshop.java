package adminWorkshopValidation;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.JavaUtility.DateClass;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;
import org.testng.annotations.*;
import java.awt.*;
import java.util.*;

public class addWorkshop extends AdminBaseClass {
	
	@DataProvider(name = "workshopData")
	public Object[][] provideWorkshopData() {
	    java.util.List<Object[]> data = new ArrayList<>();

	    for (int i = 1; ; i++) {
	        String key = "Workshop" + i;
	        try {
	            String value = GetPropertyData.propData(key);
	            if (value == null || value.trim().isEmpty()) break;

	            String[] values = value.split("~", -1); // split using ~ instead of :
	            data.add(values);
	        } catch (Exception e) {
	            break;
	        }
	    }

	    return data.toArray(new Object[0][]);
	}

	@Test(dataProvider = "workshopData")
	public void validateAddNewWorkshop(String category, String name, String type, String scope,
	                                   String poweredBy, String desc, String industryTags, String location, String benefits,
	                                   String price, String zoomLink, String imagePath)
	        throws InterruptedException, AWTException {

	    WorkshopPage wp = new WorkshopPage(driver);
	    ConferencePage cp = new ConferencePage(driver);
	    ActionClass.click(wp.EventDropdown);
	    ActionClass.click(wp.workshop);
	    Thread.sleep(2000);
	    ActionClass.click(wp.addnew_button);
	    Thread.sleep(2000);

	    ActionClass.selectByVisibleText(wp.event_cat_dropdown, category);
	    ActionClass.enterText(wp.name_field, name);
	    ActionClass.selectByVisibleText(wp.event_type_dropdown, type);
	    ActionClass.selectByVisibleText(wp.event_scope_dropdown, scope);
	    ActionClass.selectByVisibleText(wp.powered_by_dropdown, poweredBy);
	    ActionClass.enterText(wp.description_field, desc);
	    
	    ActionClass.typeUsingActions(cp.industryTags, industryTags );
	    ActionClass.pressEnter();
        ActionClass.scrollToElement(wp.location_field);
	    ActionClass.enterText(wp.location_field, location);
        ActionClass.scrollToElement(wp.workshop_image);
        ActionClass.click(wp.start_date);
        DateClass.selectDatePro(cp.start_monthElem, cp.start_nextButton, cp.previousMonthButton, cp.dateElements, "July", 2025, "25");
        ActionClass.scrollToElement(wp.workshop_image);
        ActionClass.click(wp.end_date);
        DateClass.selectDatePro(cp.end_monthElem, cp.end_nextButton, cp.previousMonthButton, cp.dateElements, "July", 2025, "30");
        ActionClass.click(cp.outside_Click);
        ActionClass.implicitWait();
        ActionClass.scrollToElement(wp.workshop_image);
        ActionClass.click(wp.workshop_image);
        ActionClass.uploadFile(imagePath);
        ActionClass.scrollToElement(wp.reg_benefits);
	    ActionClass.enterText(wp.reg_benefits, benefits);
	    ActionClass.enterText(wp.price, price);
	    ActionClass.enterText(wp.zoom_meeting, zoomLink);
	    ActionClass.scrollToElement(wp.submit_button);
	    ActionClass.click(wp.submit_button);
	    ActionClass.verifySuccessMsg(wp.success_msg, name + " Workshop created successfully");
      }
}

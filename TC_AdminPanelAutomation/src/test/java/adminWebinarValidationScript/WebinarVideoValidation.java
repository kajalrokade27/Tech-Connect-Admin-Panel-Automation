package adminWebinarValidationScript;
import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.tc.AdminPOM.CommonEventPage;
import com.tc.AdminPOM.ConferencePage;
import com.tech_Connect.Action.ActionClass;
import AdminCommonEventActions.EventsActionsTest;

public class WebinarVideoValidation extends AdminBaseClass
{
	 private ConferencePage cp;
	 private CommonEventPage commonEp;
	 EventsActionsTest et;
     @BeforeClass
      public void setup() throws IOException, InterruptedException {
      et = new EventsActionsTest();
      cp = new ConferencePage(driver);
      commonEp = new CommonEventPage(driver);
      ActionClass.click(cp.EventDropdown);
      ActionClass.click(cp.webinarsSection);
      et.searchEvent(GetPropertyData.propData("Webinar1").split("~")[1]);
      ActionClass.click(cp.eventCardName);
      ActionClass.click(cp.videosSection);
      
  }
  
  @Test(priority = 1)
  public void validateAddVideo() throws InterruptedException, AWTException, IOException {
     et.validateAddVideo(GetPropertyData.propData("videoFilePath"));
  }
  
  @Test(priority = 2)
  public void validateDeleteVideo() throws InterruptedException, AWTException, IOException {
        et.validateDeleteVideo();
  } 
}

package adminWorkshopValidation;
import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

public class WorkshopVideoValidation extends AdminBaseClass {
	private ConferencePage cp;
	WorkshopPage wp;
    @BeforeClass
     public void setup() throws IOException {
     cp = new ConferencePage(driver);
     wp = new WorkshopPage(driver);
     ActionClass.click(cp.EventDropdown);
     ActionClass.click(wp.workshop);
     ActionClass.enterText(cp.searchEventField, GetPropertyData.propData("Workshop1").split("~")[1]);
     ActionClass.pressEnter();
     ActionClass.click(cp.eventCardName);
     ActionClass.click(cp.videosSection);
 }
 
 @Test
 public void validateAddVideo() throws InterruptedException, AWTException, IOException {
     ActionClass.click(cp.addVideoButton);
     ActionClass.uploadFile(GetPropertyData.propData("videoFilePath"));
     ActionClass.click(cp.uploadVideoButton);
     ActionClass.click(cp.confirmUploadVideoButton);
     ActionClass.verifySuccessMsg(cp.videoUploadSuccessMessage, "Video uploaded successfully");
 }
 
 @Test
 public void validateDeleteVideo() throws InterruptedException, AWTException, IOException {
    ActionClass.waitUptoClickable(cp.deleteVideoButton);
 	ActionClass.click(cp.deleteVideoButton);
     ActionClass.click(cp.confirmDeleteVideoButton);
     ActionClass.verifySuccessMsg(cp.videoDeleteSuccessMessage, "Video deleted successfully");
 }
}

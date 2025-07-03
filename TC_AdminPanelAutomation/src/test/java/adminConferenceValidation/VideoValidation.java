package adminConferenceValidation;
import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.tc.AdminPOM.ConferencePage;
import com.tech_Connect.Action.ActionClass;

public class VideoValidation extends AdminBaseClass {
    private ConferencePage cp;
       @BeforeClass
        public void setup() {
        cp = new ConferencePage(driver);
        ActionClass.click(cp.EventDropdown);
        ActionClass.click(cp.conferenceLink);
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

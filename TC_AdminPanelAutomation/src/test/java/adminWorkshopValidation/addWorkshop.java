package adminWorkshopValidation;

import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.tc.AdminPOM.WorkshopPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.List;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.*;

public class addWorkshop extends AdminBaseClass {

    WorkshopPage wp;

    @BeforeMethod
    public void initPage() {
        wp = new WorkshopPage(driver);
    }

    @DataProvider(name = "workshopData")
    public Object[][] getWorkshopData() throws Exception {
        String workshop = GetPropertyData.propData("Workshop1");

        // Format: category:name:type:poweredBy:description:industry:location:start:end:regBenefits:zoom
        String[] data = workshop.split(":");

        java.util.List<String> fieldData = Arrays.asList(
            data[0],  // Event Category
            data[1],  // Event Name
            data[2],  // Event Type
            data[3],  // Powered By
            data[4],  // Description
            data[5],  // Industry Tag
            data[6],  // Location
            data[7],  // Start Date
            data[8],  // End Date
            "",       // Placeholder for file input
            data[9],  // Registration Benefits
            "0",      // Price
            data[10]  // Zoom Link
        );

        return new Object[][] {
            { fieldData, "C:\\path\\to\\image.jpg" }
        };
    }

    @Test(dataProvider = "workshopData")
    public void addWorkshop(java.util.List<String> fieldData, String imagePath) throws Exception {
        // Navigate to Workshop > Add New
        wp.EventDropdown.click();
        wp.workshop.click();
        Thread.sleep(1000);
        wp.addnew_button.click();
        Thread.sleep(1000);

        // Fill form dynamically from list
        for (int i = 0; i < fieldData.size(); i++) {
            WebElement container = wp.formFields.get(i);
            try {
                WebElement element = container.findElement(By.xpath(".//select | .//textarea | .//input"));

                String tag = element.getTagName();
                if ("select".equals(tag)) {
                    new Select(element).selectByVisibleText(fieldData.get(i));
                } else if ("textarea".equals(tag)) {
                    element.sendKeys(fieldData.get(i));
                } else if ("input".equals(tag)) {
                    String type = element.getAttribute("type");
                    if ("file".equals(type)) {
                        element.click();
                        uploadFileUsingRobot(imagePath);
                    } else {
                        element.clear();
                        element.sendKeys(fieldData.get(i));
                    }
                }
            } catch (Exception e) {
                System.out.println("Skipping index " + i + ": " + e.getMessage());
            }
            Thread.sleep(300); // optional stability
        }

        wp.submit_button.click();
    }

    public void uploadFileUsingRobot(String filePath) throws Exception {
        StringSelection sel = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}

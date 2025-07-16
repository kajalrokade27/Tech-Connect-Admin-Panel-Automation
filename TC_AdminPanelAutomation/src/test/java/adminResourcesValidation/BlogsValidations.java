package adminResourcesValidation;

import java.awt.AWTException;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.TechConnect.Base.AdminBaseClass;
import com.TechConnect.FileUtility.SheetName;
import com.TechConnect.FileUtility.UniversalDataProvider;
import com.tc.AdminPOM.BlogsPage;
import com.tc.AdminPOM.ConferencePage;
import com.tc.AdminPOM.WorkshopPage;
import com.tech_Connect.Action.ActionClass;

public class BlogsValidations extends AdminBaseClass 
{
    BlogsPage bp;
    ConferencePage cp;
    WorkshopPage wp;

    @BeforeClass
    public void setup() throws InterruptedException {
        bp = new BlogsPage(driver);
        cp = new ConferencePage(driver);
        wp = new WorkshopPage(driver);
        ActionClass.click(bp.resources_dropdown);
        ActionClass.click(bp.blogs_link);
    }
    private void goTo(String type) {
        switch (type.toLowerCase()) {
            case "blog":
                ActionClass.click(bp.blogs_link); break;
            case "article":
                ActionClass.click(bp.articles_link); break;
            case "magazine":
                ActionClass.click(bp.magazines_link); break;
        }
    }
    private void fillForm(String[] data) throws InterruptedException, AWTException {
        ActionClass.enterText(bp.blogTitleField, data[0]);
        ActionClass.enterText(bp.blogAuthorField, data[1]);
        ActionClass.enterText(bp.blogContentField, data[2]);
        ActionClass.scrollToElement(bp.blogTags);
        ActionClass.typeUsingActions(bp.blogTags, data[4]);
        ActionClass.click(bp.blogAddImage);
        ActionClass.uploadFile(data[3]);
        ActionClass.implicitWait();
    }
    
    // ========== GENERIC METHOD TO RUN ANY ACTION ==========
    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 10)
    @SheetName("AddBlogData") // Change sheet name as needed
    public void runResourceAction(String[] data) throws InterruptedException, AWTException {
        String actionType = "add"; // set to "add", "update", or "delete"
        String resourceType = "blog"; // set to "blog", "article", or "magazine"
        goTo(resourceType);

        switch (actionType) {
            case "add":
                ActionClass.click(bp.addBlogButton);
                fillForm(data);
                ActionClass.click(bp.submitButton);
                ActionClass.verifyToastMessage1(wp.toastMessage, bp.submitButton, capitalize(resourceType), false);
                break;
            case "update":
                ActionClass.enterText(bp.searchField, data[0]);
                ActionClass.pressEnter();
                if (bp.blogs_list.isEmpty()) {
                    Reporter.log("No " + capitalize(resourceType) + " found with title: " + data[0]);
                    return;
                }
                ActionClass.click(bp.editBlogIcon);
                fillForm(data);
                ActionClass.click(bp.submitButton);
                ActionClass.verifyToastMessage1(wp.toastMessage, bp.submitButton, capitalize(resourceType), false);
                break;
            case "delete":
                ActionClass.enterText(bp.searchField, data[0]);
                ActionClass.pressEnter();
                if (bp.blogs_list.isEmpty()) {
                    Reporter.log("No " + capitalize(resourceType) + " found with title: " + data[0]);
                    return;
                }
                ActionClass.click(bp.blogs_list.get(0));
                ActionClass.click(bp.deleteBlogIcons.get(0));
                ActionClass.click(cp.confirmDeleteButton);
                ActionClass.verifyToastMessage1(wp.toastMessage, bp.submitButton, capitalize(resourceType), false);
                break;
        }
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}

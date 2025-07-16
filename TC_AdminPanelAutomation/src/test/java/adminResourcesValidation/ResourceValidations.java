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

public class ResourceValidations extends AdminBaseClass {

    BlogsPage bp;
    ConferencePage cp;
    WorkshopPage wp;

    @BeforeClass
    public void setup() throws InterruptedException {
        bp = new BlogsPage(driver);
        cp = new ConferencePage(driver);
        wp = new WorkshopPage(driver);
        ActionClass.click(bp.resources_dropdown);
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
        ActionClass.implicitWait();
    }

    // ========== INDIVIDUAL ADD METHODS ==========
    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 1)
    @SheetName("AddBlogData")
    public void addBlog(String[] data) throws InterruptedException, AWTException {
        goTo("blog");
        ActionClass.click(bp.addBlogButton);
        fillForm(data);
        ActionClass.scrollToElement(bp.blogTags);
        ActionClass.typeUsingActions(bp.blogTags, data[4]);
        ActionClass.click(bp.blogAddImage);
        ActionClass.uploadFile(data[3]); // Assuming the 4th data is the image path
        ActionClass.implicitWait();
        ActionClass.implicitWait();
        ActionClass.click(bp.submitButton);
        ActionClass.verifyToastMessage1(wp.toastMessage, bp.submitButton, data[0], false);
    }

    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 2)
    @SheetName("AddArticleData")
    public void addArticle(String[] data) throws InterruptedException, AWTException {
        goTo("article");
        ActionClass.click(bp.addBlogButton);
        fillForm(data);
        ActionClass.implicitWait();
        ActionClass.scrollToElement(bp.blogTags);
        ActionClass.typeUsingActions(bp.blogTags, data[4]);
        ActionClass.click(bp.blogAddImage);
        ActionClass.uploadFile(data[3]); // Assuming the 4th data is the image path
        ActionClass.implicitWait();
        ActionClass.implicitWait();
        ActionClass.click(bp.submitButton);
        ActionClass.verifyToastMessage1(wp.toastMessage, bp.submitButton, data[0], false);
    }

    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 3)
    @SheetName("AddMagazineData")
    public void addMagazine(String[] data) throws InterruptedException, AWTException {
        goTo("magazine");
        ActionClass.click(bp.addBlogButton);
        fillForm(data);
        ActionClass.implicitWait();
        ActionClass.scrollToElement(bp.blogTags);
        ActionClass.typeUsingActions(bp.blogTags, data[4]);
        ActionClass.click(bp.blogAddImage);
        ActionClass.uploadFile(data[3]); // Assuming the 4th data is the image path
        ActionClass.implicitWait();
        ActionClass.implicitWait();
        ActionClass.click(bp.submitButton);
        ActionClass.verifyToastMessage1(wp.toastMessage, bp.submitButton, data[0], false);
    }

    // ========== INDIVIDUAL UPDATE METHODS ==========
    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 4)
    @SheetName("UpdateBlogData")
    public void updateBlog(String[] data) throws InterruptedException, AWTException {
        goTo("blog");
        updateFlow(data, "Blog");
    }

    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 5)
    @SheetName("UpdateArticleData")
    public void updateArticle(String[] data) throws InterruptedException, AWTException {
        goTo("article");
        updateFlow(data, "Article");
    }

    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 6)
    @SheetName("UpdateMagazineData")
    public void updateMagazine(String[] data) throws InterruptedException, AWTException {
        goTo("magazine");
        updateFlow(data, "Magazine");
    }

    private void updateFlow(String[] data, String type) throws InterruptedException, AWTException {
        ActionClass.enterText(bp.searchField, data[0]);
        ActionClass.pressEnter();
        if (bp.blogs_list.isEmpty()) {
            Reporter.log("No " + type + " found with title: " + data[0]);
            return;
        }
        ActionClass.click(bp.editBlogIcon);
        fillForm(data);
        ActionClass.scrollToElement(bp.blogTags);
        ActionClass.click(bp.blogUploadImage);
        ActionClass.uploadFile(data[3]); // Assuming the 4th data is the image path
        ActionClass.implicitWait();
       // ActionClass.typeUsingActions(bp.blogTags, testData[4]);
        ActionClass.click(bp.submitButton);
        ActionClass.verifyToastMessage1(wp.toastMessage, bp.submitButton, data[0], false);
    }

    // ========== INDIVIDUAL DELETE METHODS ==========
    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 7)
    @SheetName("DeleteBlogData")
    public void deleteBlog(String[] data) throws InterruptedException {
        goTo("blog");
        deleteFlow(data, "Blog");
    }

    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 8)
    @SheetName("DeleteArticleData")
    public void deleteArticle(String[] data) throws InterruptedException {
        goTo("article");
        deleteFlow(data, "Article");
    }

    @Test(dataProvider = "dynamicData", dataProviderClass = UniversalDataProvider.class, priority = 9)
    @SheetName("DeleteMagazineData")
    public void deleteMagazine(String[] data) throws InterruptedException {
        goTo("magazine");
        deleteFlow(data, "Magazine");
    }

    private void deleteFlow(String[] data, String type) throws InterruptedException {
        ActionClass.enterText(bp.searchField, data[0]);
        ActionClass.pressEnter();
        if (bp.blogs_list.isEmpty()) {
            Reporter.log("No " + type + " found with title: " + data[0]);
            return;
        }
        ActionClass.click(bp.blogs_list.get(0));
        ActionClass.click(bp.deleteBlogIcons.get(0));
        ActionClass.click(cp.confirmDeleteButton);
        ActionClass.verifyToastMessage1(wp.toastMessage, bp.submitButton, data[0], false);
    }

   
}

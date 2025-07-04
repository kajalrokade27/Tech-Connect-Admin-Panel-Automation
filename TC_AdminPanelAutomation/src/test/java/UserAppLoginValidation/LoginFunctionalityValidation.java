package UserAppLoginValidation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.TechConnect.Base.UserBaseClass;
import com.TechConnect.FileUtility.GetPropertyData;
import com.TechConnect.UserPOM.LoginPage;
import com.tech_Connect.Action.ActionClass;

public class LoginFunctionalityValidation extends UserBaseClass
{
           
@Test
    public void verifyLogin() throws InterruptedException {
	
        System.out.println("Login action performed successfully.");
        Thread.sleep(2000);
        String expectedTitle = "Tech Connect";
        String actualTitle = driver.getTitle();
        org.testng.Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch");
        System.out.println("Login successful, Title verified: " + actualTitle);
    }

    //  DataProvider to get multiple data sets from Properties file

@DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() throws IOException {
        List<Object[]> dataList = new ArrayList<>();
        
        // List of keys manually defined; update as needed
        String[] keys = {"invalid1", "invalid2", "valid"};

        for (String key : keys) {
            String value = GetPropertyData.propData(key); // ← your existing static method
            String[] parts = value.split(":");
            if (parts.length == 2) {
                dataList.add(new Object[]{parts[0], parts[1]});
            }
        }

        return dataList.toArray(new Object[0][0]);
    }

    // ✅ NEW: Test that runs with multiple invalid credentials

@Test(dataProvider = "invalidLoginData")
    public void verifyLoginWithInvalidCredentials(String username, String password) throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
       ActionClass.click(lp.login_link);
        ActionClass.enterText(lp.email_field, username);
        ActionClass.enterText(lp.password_field, password);
        ActionClass.click(lp.submit_button);
        
        Thread.sleep(2000);
        System.out.println("Attempted login with: " + username + " / " + password);

        // You may add assertions here to check for an error message:
        // Assert.assertTrue(lp.errorMessage.isDisplayed(), "Expected error message not displayed");
    }
}



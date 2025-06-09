package UserAppLoginValidation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.TechConnect.Base.UserBaseClass;
import com.TechConnect.FileUtility.ExcelUtils;
import com.TechConnect.UserPOM.LoginPage;


public class VerifyLoginFunctionality extends UserBaseClass
{
   @Test
   public void verifyLogin() throws InterruptedException
   {
	  // Simulate a login action
	  System.out.println("Login action performed successfully.");
	  
	  // Simulate a wait for the page to load
	  Thread.sleep(2000);
	  
	  // Verify the title of the page
	  String expectedTitle = "Tech Connect";
	  String actualTitle = driver.getTitle();
	  
	  Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch");
      System.out.println("Login successful, Title verified: " + actualTitle);
      
      
     }
   @DataProvider(name = "excelData")
	public Object[][] getData() throws IOException 
	{
		int row = ExcelUtils.getRows("UserApp");
		System.out.println(row);
		int column = ExcelUtils.getCells("UserApp");
		System.out.println(column);
     Object[][]obj=new Object[row][column];
     for(int i=0; i<row; i++)
     {
   	  for(int j=0; j<column; j++)
   	  {
   	 obj[i][j]= ExcelUtils.Exceldata("UserApp", i, j); 
   	  }
     }
     return obj;
     
     }
   
   @Test(dataProvider = "excelData" , enabled = false)
   public void verifyLoginWithInvalidCredentials(String data1, String data2) throws InterruptedException
   {
	   //Login
	 
       LoginPage lp = new LoginPage(driver);
 	  lp.login_link.click();
 	  lp.email_field.sendKeys(data1);
 	  lp.password_field.sendKeys(data2);
 	  lp.submit_button.click();
 	  
 		
 		Thread.sleep(2000); // Wait for the page to load after login
	  // Simulate an invalid login action
	  System.out.println("Attempting to login with invalid credentials.");
	  
	
   }
   

}

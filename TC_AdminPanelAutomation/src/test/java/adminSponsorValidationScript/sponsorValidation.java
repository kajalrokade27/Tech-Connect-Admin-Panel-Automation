package adminSponsorValidationScript;

import org.testng.annotations.Test;

import com.TechConnect.Base.AdminBaseClass;
import com.tc.AdminPOM.SponsorsPage;
import com.tech_Connect.Action.ActionClass;



public class sponsorValidation extends AdminBaseClass 
{
   @Test
   public void sponsorValidationTest() throws InterruptedException
   {
	
	  SponsorsPage sp = new SponsorsPage(driver);
	   ActionClass.click(sp.sponsors_link);
	   ActionClass.click(sp.addSponsorButton);
	   Thread.sleep(2000); 
	   ActionClass.enterText(sp.sponsor_fields.get(0), "TechConnect");
	   ActionClass.enterText(sp.sponsor_fields.get(1), "https://techconnect.com");
	   
	  
	  
   }
}
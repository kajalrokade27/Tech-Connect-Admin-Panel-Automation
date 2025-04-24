package DashBoardValidationScripts;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TechConnect.Base.BaseClass;
import com.TechConnect.POM.DashBoardPage;
import com.TechConnect.POM.SpeakerPage;
import com.TechConnect.POM.SponsorsPage;
import com.TechConnect.POM.UsersPage;
@Listeners(com.TechConnect.Listeners.ListenersClass.class)
public class DashboardValidation extends BaseClass
{
  @Test
  public void redirectingElementValidation() throws InterruptedException
  {
	  DashBoardPage db = new DashBoardPage(driver);
	  SpeakerPage sp = new SpeakerPage(driver);
	  UsersPage up = new UsersPage(driver);
	  SponsorsPage so = new SponsorsPage(driver);
	  
	 for(int i=0; i<db.redirecting_elements.size()-1; i++)
	 {
		 Thread.sleep(4000);
		 String count = db.redirectingElements_count.get(i).getText();
		 int expectedCount = Integer.parseInt(count);
		
		 System.out.println(expectedCount);
		
		 System.out.println(count);
		 db.redirecting_elements.get(i).click();
		 Thread.sleep(2000);
		 if(i==0)
		 {
			 assertEquals(expectedCount, sp.speakers_list.size());
			 System.out.println("Dashboard is redirected and showing correct speaker count");
		 }
		 if(i==1)
		 {
			 assertEquals(expectedCount, up.users_list.size());
			 System.out.println("Dashboard showing correct user count");
		 }
		 if(i==3)
		 {
			 assertEquals(expectedCount, so.sponsors_list.size());
			 System.out.println("Dashboard showing correct sponsor count");
		 }
		 
		 
		 driver.navigate().back();
		 
		 
	 }
	  
  }
}

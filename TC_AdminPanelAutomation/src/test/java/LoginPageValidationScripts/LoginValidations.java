package LoginPageValidationScripts;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TechConnect.Base.BaseClass;
@Listeners(com.TechConnect.Listeners.ListenersClass.class)
public class LoginValidations extends BaseClass
{
  @Test
  public void loginValidation()
  {
	  System.out.println("login is successfully done");
  }
}

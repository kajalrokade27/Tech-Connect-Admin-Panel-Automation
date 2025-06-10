package com.TechConnect.UserPOM;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage
{
   
   public LoginPage(org.openqa.selenium.WebDriver driver)
   {
	  org.openqa.selenium.support.PageFactory.initElements(driver, this);
   }
   
   public @FindBy(xpath="//a[@class='auth-link']")
   WebElement login_link;
   
   public @FindBy(xpath="//input[@name='email']")
   WebElement email_field;
   
   public @FindBy(xpath="//input[@name='password']")
   	WebElement password_field;
   
   public  @FindBy(xpath="//button[@class='btn w-100 mb-3 mt-3']")
   WebElement submit_button;
   
   public @FindBy(xpath="//i[@class='fa-solid fa-user']")
   WebElement user_icon;
   
   public @FindBy(xpath="//a[contains(text(),'Logout')]")
   WebElement logout_link;
   
   public @FindBy(xpath="//button[@class='swal2-confirm swal2-styled swal2-default-outline']")
   WebElement confirm_logout_button;
   
   public @FindBy(xpath="//a[@class='fw-medium']")
   WebElement Register_link;
   
   public @FindBy(xpath="//*[@class='shadow-sm p-2 mt-1 form-control']")
   List<WebElement> register_fields;
   
   public @FindBy(xpath="//div[@class='recaptcha-checkbox-border']")
   WebElement recaptcha_checkbox;
   
   
   
   
   
   

   
   
   
   
   
}

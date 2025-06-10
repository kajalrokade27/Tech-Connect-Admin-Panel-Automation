package com.TechConnect.UserPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
   public HomePage(org.openqa.selenium.WebDriver driver)
   {
	   PageFactory.initElements(driver, this);
   }
   
   public @FindBy(xpath="//a[@class='auth-link']")
   WebElement Reg_button;
   
   public @FindBy(xpath="//button[@class='btn w-100 mb-3']")
   WebElement signUp_button;
   
   
   
}

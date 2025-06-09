package com.tc.AdminPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
       public LoginPage(WebDriver driver)
       {
    	   PageFactory.initElements(driver, this);
       }
       
       public @FindBy(xpath="//div[@class='mb-3']")
       List<WebElement> login_elements;
       
       public @FindBy(xpath="//input[@name='email']")
       WebElement email_field;
       
       public @FindBy(xpath="//input[@name='password']")
       WebElement password_field;
       
       
       public @FindBy(xpath="//button[@class='me-2 btn btn-primary']")
       WebElement submit_button;
       
       
       
       
}

package com.tc.AdminPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UsersPage {
	public UsersPage(WebDriver driver)
    {
 	   PageFactory.initElements(driver, this);
    }
    
	 public @FindBy(xpath="//h5[@class='mb-0 text-truncate']")
	 List<WebElement> users_list;
	 
	 public @FindBy(xpath="(//div[@class='d-flex align-items-center'])[3]")
	 WebElement users;
	 
	 //Add user
	 public @FindBy(xpath="(//button[@class='btn mb-3 btn btn-success'])[1]")
	 WebElement add_users;
	 
	 public @FindBy(xpath="//div[@class='mb-2 col-lg-6']")
	 List<WebElement> add_users_form;
	 
	 public @FindBy(xpath="//input[@name='name']")
	 WebElement u_name;
	 
	 public @FindBy(xpath="//input[@name='email']")
	 WebElement u_email;
	 
	 public @FindBy(xpath="//input[@name='company']")
	 WebElement u_company;
	 
	 public @FindBy(xpath="//input[@name='jobTitle']")
	 WebElement  u_jobTitle;
	 
	 public @FindBy(xpath="//button[@class='btn-info btn btn-secondary']")
	 WebElement submitButton;
	 
	 public @FindBy(xpath="//div[contains(text(),'User added successfully')]\r\n")
	 WebElement success_msg;
	 
	 public @FindBy(xpath="(//div[@title='Delete'])[1]")
	 WebElement delete_user;
	 
	 //import user from excel file
	 public @FindBy(xpath="//button[@class='btn me-3 mb-3 btn btn-success']")
	 WebElement import_user;
	 
	 public @FindBy(xpath="//div[@class='d-flex flex-column align-items-center text-center ']")
	 WebElement upload_file;
	 
	 public @FindBy(xpath="//button[@class='btn-info btn btn-secondary']")
	 WebElement submit_button;
	 
	   
	 
	 
	 
	 
	 
	 
	 
}

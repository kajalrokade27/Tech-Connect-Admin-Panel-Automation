package com.tc.AdminPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SponsorsPage

{
	public SponsorsPage(WebDriver driver)
    {
 	   PageFactory.initElements(driver, this);
    }
    
	  public @FindBy(xpath="(//div[@class='d-flex align-items-center'])[4]")
	  WebElement sponsors_link;
	 
	 public @FindBy(xpath="//h5[@class='mb-0 text-truncate']")
	 List<WebElement> sponsors_list;
	 
	 public @FindBy(xpath="(//button[@class='btn mb-3 btn btn-success'])[1]")
	 WebElement addSponsorButton;
	 
	 public @FindBy(xpath="//input[@class='form-control']")
	 List<WebElement> sponsor_fields;
	 
	 
	 
	 
	 
	 
	 
}

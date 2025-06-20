package com.tc.AdminPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BlogsPage 
{
   public BlogsPage(WebDriver driver)
   {
	   // Initialize the PageFactory elements for this page
	  PageFactory.initElements(driver, this);
   }
   
  public  @FindBy(xpath="//div[@class='d-md-flex']")
   WebElement resources_dropdown;
  
  public @FindBy(xpath="(//span[@class='hide-mini'])[7]")
   WebElement blogs_link;
  
  public @FindBy(xpath="//h5[@class='mb-0 mt-2']")
   List<WebElement> blogs_list;
   
  
}
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
   
  public  @FindBy(xpath="//span[contains(text(),'Resources')]")
   WebElement resources_dropdown;
  
  public @FindBy(xpath="(//span[@class='hide-mini'])[7]")
   WebElement blogs_link;
  
  public @FindBy(xpath="//h5[@class='mb-0 mt-2']")
   List<WebElement> blogs_list;
  
  public @FindBy(xpath="//button[@class='btn btn btn-success']")
   WebElement addBlogButton;
  
  public @FindBy(xpath="//input[@name='title']")
   WebElement blogTitleField;
  
  public @FindBy(xpath="//input[@name='author']")
   WebElement blogAuthorField;
  
  public @FindBy(xpath="//div[@class='ce-paragraph cdx-block']")
   WebElement blogContentField;
  
  public @FindBy(xpath="//img[@class='fui-avatar-image']")
   WebElement blogUploadImage;
  
  public @FindBy(xpath="//div[@class='fui-avatar-label']")
  WebElement blogAddImage;
  
  public @FindBy(xpath="//div[@class=' css-1hwfws3']")
   WebElement blogTags;
  
  public @FindBy(xpath="//button[@type='submit']")
   WebElement submitButton;
  
  public @FindBy(xpath="//tbody//tr//td//a//i[@id='TooltipExample']")
   WebElement editBlogIcon;
  
  public @FindBy(xpath="//input[@type='text']")
   WebElement searchField;
  
  public @FindBy(xpath="//i[@title='Delete']")
   List<WebElement> deleteBlogIcons;
  
  public @FindBy(xpath="//span[contains(text(),'Articles')]")
   WebElement articles_link;
  
  public @FindBy(xpath="//span[contains(text(),'Magazines')]")
   WebElement magazines_link;
  
  
   
  
}
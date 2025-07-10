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
	 
	 public @FindBy(xpath="(//div[@class='fui-avatar-label'])[1]")
	 WebElement sponsor_logo;
	 
	 public @FindBy(xpath="(//div[@class='fui-avatar-label'])[2]")
	 WebElement banner_image;
	 
	 public @FindBy(xpath="(//div[@class='fui-avatar-label'][contains(text(),'Select image')])[2]")
	 WebElement bannerImage;
	 
	public @FindBy(xpath="//select[@class='form-select']")
	List<WebElement> spons_drop;

	public @FindBy(xpath="//div[@class='mb-2 col-lg-12']//div[@aria-label='Edit text']")
	 WebElement desc_field;
	 
	 public @FindBy(xpath="//button[normalize-space()='Submit']")
	 WebElement submitButton;
	 
	 public @FindBy(xpath="//div[contains(text(),'Sponsor created successfully.')]")
	 WebElement success_msg;
	 
	 public @FindBy(xpath="//div[@class='Toastify__toast-body']")
	 WebElement toastMessage;
	 
	 public @FindBy(xpath="//input[@id='searchUser']")
	 WebElement searchField;
	 
	  public @FindBy(xpath = "//img[@class='fui-avatar-image']")
  	 List<WebElement> updateSponsorImages;
	 
	 public @FindBy(xpath = "//div[contains(text(),'Sponsor updated successfully.')]")
	 WebElement sponsorUpdateSuccessMessage;
	 
  public @FindBy(xpath = "//i[@class='bi bi-trash']")
  	 List<WebElement> deleteSponsorIcon;
  
  public @FindBy(xpath = "//div[contains(text(),'Sponsor removed successfully.')]")
  	 WebElement sponsorDeleteSuccessMessage;
	 
	 public @FindBy(xpath="//button[@type='submit']")
	 WebElement updateButton;
	 
	 //poweredby elements
	 public @FindBy(xpath="//span[contains(text(),'Powered By')]")
	 WebElement poweredByLink;
	 
	 public @FindBy(xpath="//button[normalize-space()='Add New']")
	 WebElement addNewButton;
	 
	 public @FindBy(xpath="//input[@name='name']")
	 WebElement poweredByNameField;
	 
	 public @FindBy(xpath="//input[@name='website']")
	 WebElement poweredByWebsiteField;
	 
	 public @FindBy(xpath="//div[@class='fui-avatar-label']")
	 WebElement poweredByLogo;
	 
	 public @FindBy(xpath="//h5[@class='mb-0 text-truncate']")
	 List<WebElement> poweredByList;
	 
	 public @FindBy(xpath="//img[@class='fui-avatar-image']")
	 WebElement updateCompanyLogo;
	 
	 public @FindBy(xpath="//i[@class='bi bi-trash']")
	 List<WebElement> deleteButtons;
	 
	 
	 
	 
}

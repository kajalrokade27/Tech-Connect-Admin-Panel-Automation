package com.tc.AdminPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConferencePage {

	
	public ConferencePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	 public @FindBy(xpath="(//span[@class='d-block'])[1]")
	    WebElement EventDropdown;
	 
	 public @FindBy(xpath="(//span[@class='hide-mini'])[5]")
	    WebElement conferenceLink;
        
	 public @FindBy(xpath="//div[@class=' css-1hwfws3']")
	 WebElement industryTags;
	 
	 public @FindBy(xpath = "//span[text()='Webinars']")
     WebElement webinarsSection;
	 
	 public @FindBy(xpath = "(//th[@class='rdtSwitch'])[1]")
	 WebElement start_monthElem;
	 
	 public @FindBy(xpath = "(//th[@class='rdtNext'])[1]")
	 WebElement start_nextButton;
	 
	 public @FindBy(xpath = "//td[@class='rdtDay']")
	 List<WebElement> dateElements;
	 
	 public @FindBy(xpath = "(//th[@class='rdtSwitch'])[2]")
	 WebElement end_monthElem;
	 
	 public @FindBy(xpath = "(//th[@class='rdtNext'])[2]")
	 WebElement end_nextButton;
	 
	 public @FindBy(xpath = "(//div[@class='mb-3'])[11]")
	 WebElement outside_Click;
	 
	 public @FindBy(xpath = "//input[@name='eventUrl']")
	 WebElement eventUrlField;
	 
	 public @FindBy(xpath = "//input[@name='zoomLink']")
	 WebElement zoomLinkField;
	 
	 public @FindBy(xpath = "//*[contains(text(), 'new conference created successfully')]")
	 WebElement successMessage;
	 
	 public @FindBy(xpath = "(//div[@class='card-body d-flex flex-column']/h6)[1]")
	 WebElement eventCardName;
	 
	 public @FindBy(xpath = "//button[contains(text(),'Publish')]")
	 WebElement publishButton;
	 
	 public @FindBy(xpath = "//button[contains(text(),'Yes, publish it!')]")
	 WebElement confirmPublishButton;
	 
	 public @FindBy(xpath = "//button[contains(text(),'Save as Draft')]")
	 WebElement saveAsDraftButton;
	 
	 public @FindBy(xpath = "//button[contains(text(),'Yes, save as draft!')]")
	 WebElement confirmSaveAsDraftButton;
	 
	 public @FindBy(xpath = "(//i[@class='bi bi-three-dots-vertical'])[1]")
	 WebElement threeDotsMenu;
	 
	 public @FindBy(xpath = "(//i[@class=\"bi bi-trash me-2\"])[1]")
	 WebElement deleteButton;
	 
	 public @FindBy(xpath = "(//button[@class='swal2-confirm swal2-styled swal2-default-outline'])")
	 WebElement confirmDeleteButton;
	 
	 public @FindBy(xpath = "//button[contains(text(),'Reset')]")
	 WebElement  resetButton;
	 
	 
	 
	 
	 
	 
	 
}

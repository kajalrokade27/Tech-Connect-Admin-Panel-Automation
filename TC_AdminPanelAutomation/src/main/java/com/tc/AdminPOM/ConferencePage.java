package com.tc.AdminPOM;

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
	 
	 
}

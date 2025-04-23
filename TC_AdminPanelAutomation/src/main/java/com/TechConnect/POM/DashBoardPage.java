package com.TechConnect.POM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage {
	public DashBoardPage(WebDriver driver)
    {
 	   PageFactory.initElements(driver, this);
    }
    
    public @FindBy(xpath="//div[@class='d-md-flex']")
    List<WebElement> redirecting_elements;
    
    public @FindBy(xpath="//h2[@class='fs-1']")
    List<WebElement> redirectingElements_count;
    
    
    
    
}

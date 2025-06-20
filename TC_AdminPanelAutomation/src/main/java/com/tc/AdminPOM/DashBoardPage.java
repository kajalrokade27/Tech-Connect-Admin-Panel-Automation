package com.tc.AdminPOM;

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
    
    public @FindBy(xpath="//h2[@class='fs-2 fw-medium mt-2 text-success mb-0 ms-auto']")
    List<WebElement> redirectingElements_count;
    
    
    
    
}

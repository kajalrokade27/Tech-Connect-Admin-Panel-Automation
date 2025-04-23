package com.TechConnect.POM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpeakerPage
{
	public SpeakerPage(WebDriver driver)
    {
 	   PageFactory.initElements(driver, this);
    }
    
    public @FindBy(xpath="//h5[@class='mb-0 text-truncate']")
    List<WebElement> speakers_list;
    
    
    
    
}

package com.tc.AdminPOM;

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
    
    public @FindBy(xpath="//span[contains(text(),'Speaker Invitations')]")
    WebElement speakerInvitionButton;
    
    public @FindBy(xpath="//button[normalize-space()='Add New']")
    WebElement addNewButton;
    
    public @FindBy(xpath="//input[@name='name']")
    WebElement speakerName;
    
    public @FindBy(xpath="//input[@name='email']")
    WebElement speakerEmail;
    
    public @FindBy(xpath="//button[@type='submit']")
    WebElement submitButton;
    
    
    public @FindBy(xpath="//button[normalize-space()='Cancel']")
    WebElement cancelButton;
    
    public @FindBy(xpath="//input[@placeholder='Search speaker name...']")
    WebElement searchField;
    
    public @FindBy(xpath="//h5[@class='mb-0 mt-2']")
    List<WebElement> speakerNameList;
    
    public @FindBy(xpath="//i[@title='Resend']")
    List<WebElement> resendButton;
    
    public @FindBy(xpath="//button[@class='swal2-confirm swal2-styled swal2-default-outline']")
    WebElement confirmButton;
    
    public @FindBy(xpath="//i[@title='Delete']")
    List<WebElement> deleteButton;
    
    
    
    
    
}

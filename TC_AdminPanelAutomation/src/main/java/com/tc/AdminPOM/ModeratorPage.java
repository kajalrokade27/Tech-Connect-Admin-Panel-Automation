package com.tc.AdminPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TechConnect.Base.AdminBaseClass;

public class ModeratorPage extends AdminBaseClass {
	
	public ModeratorPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public @FindBy(xpath="//span[contains(text(),'Moderators')]")
	WebElement moderatorLink;
	
	public @FindBy(xpath="//button[normalize-space()='Add New']")
	WebElement addNewButton;
	
	public @FindBy(xpath="//input[@name='name']")
	WebElement nameField;
	
	public @FindBy(xpath="//input[@name='position']")
	WebElement positionField;
	
	public @FindBy(xpath="//input[@name='linkedInUrl']")
	WebElement linkedInField;
	
	public @FindBy(xpath="//input[@name='email']")
	WebElement emailField;
	
	public @FindBy(xpath="//select[@name='who']")
	WebElement categoryDropdown;
	
	public @FindBy(xpath="//div[@class='fui-avatar-label']")
	WebElement addImage;
	
	public @FindBy(xpath="//textarea[@name='about']")
	WebElement aboutField;
	
	public @FindBy(xpath="//button[@type='submit']")
	WebElement submitButton;
	
	public @FindBy(xpath="//button[normalize-space()='Cancel']")
	WebElement cancelButton;
	
	public @FindBy(xpath="//input[@id='searchUser']")
    WebElement searchField;
	

	
	public @FindBy(xpath="//h5[@class='mb-0 text-truncate']")
	List<WebElement> moderatorList;
	
	public @FindBy(xpath="//img[@class='fui-avatar-image']")
	WebElement updateImage;
	
	public @FindBy(xpath="//div[@title='Delete']")
	List<WebElement> deleteIcons;
	
	
	
	
	
	
	
	
	

}

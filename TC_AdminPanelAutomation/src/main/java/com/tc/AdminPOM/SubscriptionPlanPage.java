package com.tc.AdminPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TechConnect.Base.AdminBaseClass;

public class SubscriptionPlanPage extends AdminBaseClass {

	public SubscriptionPlanPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public @FindBy(xpath="//span[contains(text(),'Subscription Plans')]")
	WebElement subscriptionPlanLink;
	
	public @FindBy(xpath="//button[normalize-space()='Add New']")
	WebElement addNewButton;
	
	public @FindBy(xpath="//input[@id='type']")
	WebElement planType;
	
	public @FindBy(xpath="//input[@id='amount']")
	WebElement planAmount;
	
	public @FindBy(xpath="//select[@id='currency']")
     	WebElement planCurrency;
	
	public @FindBy(xpath="//input[@id='description']")
	WebElement planDescription;
	
	public @FindBy(xpath="//div[@aria-label='Edit text']")
	WebElement planBenefits;
	
	public @FindBy(xpath="//button[normalize-space()='Submit']")
	WebElement submitButton;
	
	public @FindBy(xpath="//button[@type='button'][normalize-space()='Cancel']")
	WebElement cancelButton;
    
	public @FindBy(xpath="//input[@id='searchUser']")
	WebElement searchField;
	
	public @FindBy(xpath="//h5[@class='mb-0 text-truncate']")
	List<WebElement> planNameList;
	
	public @FindBy(xpath="//div[@title='Delete']")
	List<WebElement> deleteButton;
	
	public @FindBy(xpath="//button[@type='submit']")
	WebElement updateButton;
	
	//subdomain elements
	
	public @FindBy(xpath="//span[contains(text(),'Sub Domains')]")
	WebElement subDomainLink;
	
	public @FindBy(xpath="//input[@name='domain']")
	WebElement subDomainName;
	
	public @FindBy(xpath="//div[@class=' css-1hwfws3']")
	WebElement subDomainEvent;
	
	public @FindBy(xpath="//select[@name='published']")
	WebElement publishOnDomainDropdown;
	
	public @FindBy(xpath="//h5[@class='mb-0 text-truncate']")
	List<WebElement> subDomainList;
	
	public @FindBy(xpath="//i[@class='bi bi-trash']")
	List<WebElement> deleteSubDomainButton;
	
	public @FindBy(xpath="//input[@id='searchDomain']")
	WebElement subDomainSearchField;
	
	
	
	
	
	
}

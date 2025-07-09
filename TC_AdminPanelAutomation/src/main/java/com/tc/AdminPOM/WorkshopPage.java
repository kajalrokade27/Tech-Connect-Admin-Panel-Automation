package com.tc.AdminPOM;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WorkshopPage {
	public WorkshopPage(WebDriver driver)
    {
 	   PageFactory.initElements(driver, this);
    }
	public @FindBy(xpath="//div[@class='mb-3']")
	List<WebElement> formFields;
    
    public @FindBy(xpath="(//span[@class='d-block'])[1]")
    WebElement EventDropdown;
    
    public @FindBy(xpath="(//span[@class='hide-mini'])[4]")
    WebElement workshop;
    
    public @FindBy(xpath="//span[contains(text(),'Webinars')]")
    WebElement webinarLink;
    
    public @FindBy(xpath="//span[contains(text(),'Panel Discussion')]")
    WebElement panelDiscussionLink;
    
    public @FindBy(xpath="//span[contains(text(),'Podcasts')]")
    WebElement podcastLink;
    
    public @FindBy(xpath="//button[@class='btn btn btn-success']")
    WebElement addnew_button;
    
    public @FindBy(xpath="(//select[@class='form-control'])[1]")
    WebElement event_cat_dropdown;
    
    public @FindBy(xpath="(//input[@class='form-control'])[1]")
    WebElement name_field;
    
    public @FindBy(xpath="(//select[@class='form-control'])[2]")
    WebElement event_type_dropdown;
    
    public @FindBy(xpath="(//select[@class='form-control'])[3]")
    WebElement event_scope_dropdown;
    
    public @FindBy(xpath="(//select[@class='form-control'])[4]")
    WebElement powered_by_dropdown;
    
    public @FindBy(xpath="(//input[@class='form-control'])[2]")
    WebElement location_field;
    
    public @FindBy(xpath="(//input[@class='form-control'])[3]")
    WebElement start_date;
    
    public @FindBy(xpath="(//td[@data-value='25'])[1]")
    WebElement apr25;
    
    public @FindBy(xpath="(//input[@class='form-control'])[4]")
    WebElement end_date;
    
    public @FindBy(xpath="(//td[@data-value='30'])[4]")
    WebElement apr30;
    
    public @FindBy(xpath="//div[@class=' css-1hwfws3']")
    WebElement related_topics_dropdown;
    
    public @FindBy(xpath="(//div[contains(@class,'fui-avatar-label')])[1]")
    WebElement workshop_image;
    
    public @FindBy(xpath="(//div[@class='notranslate public-DraftEditor-content'])[1]")
    WebElement description_field;
    
    public @FindBy(xpath="(//div[@class='notranslate public-DraftEditor-content'])[2]")
    WebElement reg_benefits;
    
    public @FindBy(xpath="//button[@class='button btn-info me-3 btn btn-secondary']")
    WebElement submit_button;
    
    public @FindBy(xpath="//button[@class=\"btn btn-info me-3 btn btn-secondary\"]")
    WebElement updateWorkshop;
    
    public @FindBy(xpath="(//img[@class='card-img-top rounded-top'])[1]")
    WebElement re_created_workshop;
    
    public @FindBy(xpath="//button[@class='btn btn-success btn btn-success']")
    WebElement publish_button;
    
    public @FindBy(xpath="//button[@class='swal2-confirm swal2-styled swal2-default-outline']")
    WebElement yes_pubIt;
    
    public @FindBy(xpath="//div[contains(text(),'Your event has been published successfully.')]")
    WebElement publish_msg;
    
    //details module
    public @FindBy(xpath="(//div[@class='text-center menu-tab false'])[1]")
    WebElement details_tab;
    
    //session submodule
    public @FindBy(xpath="(//div[@class='d-flex align-items-center sub-menu-tab false'])[1]")
    WebElement sessions;
    
    public @FindBy(xpath="//button[@class='btn btn-success']")
    WebElement addnew_session;
    
    public @FindBy(xpath="(//input[@class='form-control'])[1]")
    WebElement session_title;
    
    public @FindBy(xpath="(//input[@class='form-control'])[2]")
    WebElement session_start_date;
    
    public @FindBy(xpath="(//td[@data-value='27'])[1]")
    WebElement apr27;
    
    
    
    public @FindBy(xpath="(//input[@class='form-control'])[3]")
    WebElement session_end_date;
    
    public @FindBy(xpath="(//td[@data-value='29'])[2]")
    WebElement apr29;
    
    public @FindBy(xpath="//select[@name='speakerId']")
    WebElement speaker_id;
    
    public @FindBy(xpath="//div[@class='notranslate public-DraftEditor-content']")
    WebElement session_description;
    
    public @FindBy(xpath="//button[@class='button btn-info btn btn-secondary']")
    WebElement se_submitbutton;
    
    public @FindBy(xpath="//button[@class='btn btn-light btn-sm text-danger']")
    WebElement delete_session;
    
    public @FindBy(xpath="//button[@class='swal2-confirm swal2-styled swal2-default-outline']")
    WebElement yes_remove_session;
    
    public @FindBy(xpath="//div[contains(text(),'Session deleted successfully.')]")
    WebElement del_session_msg;
    //speaker
    public @FindBy(xpath="(//div[@class='d-flex align-items-center sub-menu-tab false'])[2]")
    WebElement speakers;
    
    //Create a new speaker
    public @FindBy(xpath="(//button[@class='btn mb-3 btn btn-success'])[1]")
    WebElement add_new_speaker;
    
    public @FindBy(xpath="(//input[@class='form-control'])[1]")
    WebElement sp_name;
    
    public @FindBy(xpath="(//input[@class='form-control'])[2]")
    WebElement sp_position;
    
    public @FindBy(xpath="(//input[@class='form-control'])[3]")
    WebElement sp_linkedin;
    
    public @FindBy(xpath="//select[@class='form-select']")
    WebElement sp_category;
    
    public @FindBy(xpath="//div[@class='fui-avatar-label']")
    WebElement sp_profilephoto;
    
    public @FindBy(xpath="//textarea[@name='about']")
    WebElement sp_aboutSpeaker;
    
    public @FindBy(xpath="//button[@class='button btn-info btn btn-secondary']")
    WebElement sp_new_submitButton;
    
    //add existing speaker
    public @FindBy(xpath="(//button[@class='btn mb-3 btn btn-success'])[2]")
    WebElement add_existing_speaker;
    
    public @FindBy(xpath="//div[@class=' css-1hwfws3']")
    WebElement select_speaker;
    
    public @FindBy(xpath="//button[@class='button btn-info btn btn-secondary']")
    WebElement submit_ext_button;
    
    
    public @FindBy(xpath="(//div[@class='d-flex align-items-center sub-menu-tab false'])[3]")
    WebElement sponsors;
    
     
    
    public @FindBy(xpath="(//div[@class='text-center menu-tab false'])[2]")
    WebElement Regstr_tab;
    
    public @FindBy(xpath="(//div[@class='text-center menu-tab false'])[3]")
    WebElement videos_tab;
    
    //update workshop
    public @FindBy(xpath="//input[@name='eventUrl']")
    WebElement eventUrl_field;
    
    public @FindBy(xpath="//button[@class='btn btn-info me-3 btn btn-secondary']")
    WebElement update_button;
    
    public @FindBy(xpath="//div[contains(text(),'Workshop updated successfully.')]")
    WebElement update_msg;
    
    public @FindBy(xpath="//input[@name='price']")
    WebElement price;
    
    public @FindBy(xpath="//input[@name='zoomLink']")
    WebElement zoom_meeting;
    
    
    public @FindBy(xpath="//div[contains(text(),'new workshop created successfully.')]")
    WebElement success_msg;
    
    public @FindBy(xpath = "//div[@class='Toastify__toast-body']")
    WebElement toastMessage;
    
    public @FindBy(xpath="//table[@class='align-middle table']/tbody/tr/td[4]")
    List<WebElement> purchasedAmount;
    
   
    
    }

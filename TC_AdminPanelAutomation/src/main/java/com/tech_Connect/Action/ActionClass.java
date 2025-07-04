
package com.tech_Connect.Action;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.TechConnect.Base.BaseDriver;

public class ActionClass extends BaseDriver {
    // WebDriverWait for explicit waits
    private static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    // JavascriptExecutor for JS-based actions
    private static final JavascriptExecutor js = (JavascriptExecutor) driver;
    // Actions class for advanced user interactions
    private static final Actions actions = new Actions(driver);
     Select select;

    // Apply a colored border to a WebElement (for debugging/highlighting)
    public static void applyBorder(WebElement element, String color) {
        js.executeScript("arguments[0].style.border='3px solid " + color + "'", element);
    }

    // Click an element, fallback to JS click if normal click fails
    public static void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        try {
        	applyBorder(element, "green");
            element.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    // Enter text into an input field after waiting for visibility
    public static void enterText(WebElement element, String value) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            // Scroll to the elements
            Thread.sleep(1000);
            element.click();               // Ensure field is focused
            element.clear();               // Clear it
            Thread.sleep(100);             // Small wait in case JS is triggered
            element.sendKeys(Keys.CONTROL + "a");  // Select all text
            element.sendKeys(Keys.DELETE);
          //  element.clear();
           
            applyBorder(element, "blue");
            
            element.sendKeys(value);
        } catch (Exception e) {
            System.err.println("Unable to enter value: " + e.getMessage());
        }
    }
    public static boolean isElementVisible(WebElement element, int timeout) {
        try {
        	waitUptoVisible(element);
            return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Wait until the element is clickable
    public static void waitUptoClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.err.println("Element not clickable: " + e.getMessage());
        }
    }

    // Get text from an element after waiting for visibility
    public static String getText(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.getText();
        } catch (Exception e) {
            System.err.println("Unable to get text: " + e.getMessage());
            return "";
        }
    }

    // Wait for the page to load completely
    public static void waitForPageLoad(int timeOutInSec) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec))
                .until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
         
        } catch (Exception e) {
            System.err.println("Page did not load: " + e.getMessage());
        }
    }

    // Wait until the element is visible
    public static void waitUptoVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.err.println("Element not visible: " + e.getMessage());
        }
    }

    // Scroll to a specific element using JavaScript
    public static void scrollToElement(WebElement element) {
        try {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            System.err.println("Unable to scroll to element: " + e.getMessage());
        }
    }

    // Check if an element is displayed after waiting for visibility
    public static boolean isDisplayed(WebElement element) {
        try {
            waitUptoVisible(element);
            return element.isDisplayed();
        } catch (Exception e) {
            System.err.println("Element not displayed: " + e.getMessage());
            return false;
        }
    }

    // Set implicit wait for the driver
    public static void implicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70000));
    }

    // Click an element using JavaScript after waiting for it to be clickable
    public static void jsClick(WebElement element) {
        try {
            waitUptoClickable(element);
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.err.println("Unable to JS click: " + e.getMessage());
        }
    }
    
    public static void setValueUsingJS( WebElement element, String value) 
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", element, value);
    }

    // Simulate pressing the Enter key using Robot class
    public static void pressEnter() {
        try {
            Robot rb = new Robot();
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            System.err.println("Unable to press Enter: " + e.getMessage());
        }
    }

    // Double-click on an element
    public static void doubleClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            actions.doubleClick(element).perform();
        } catch (Exception e) {
            System.err.println("Unable to double click: " + e.getMessage());
        }
    }

    // Right-click (context click) on an element
    public static void rightClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            actions.contextClick(element).perform();
        } catch (Exception e) {
            System.err.println("Unable to right click: " + e.getMessage());
        }
    }

    // Drag and drop from source element to target element
    public static void dragAndDrop(WebElement source, WebElement target) {
        try {
            wait.until(ExpectedConditions.visibilityOf(source));
            actions.dragAndDrop(source, target).perform();
        } catch (Exception e) {
            System.err.println("Unable to drag and drop: " + e.getMessage());
        }
    }

    // Hover mouse over an element
    public static void hoverOverElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            actions.moveToElement(element).perform();
        } catch (Exception e) {
            System.err.println("Unable to hover: " + e.getMessage());
        }
    }
    
    // Type text into an element using Actions class
    public static void typeUsingActions( WebElement element, String text) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().sendKeys(text).perform();
    }

    // Scroll to the bottom of the page
    public static void scrollToBottom() {
        try {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        } catch (Exception e) {
            System.err.println("Unable to scroll to bottom: " + e.getMessage());
        }
    }

    // Scroll to the top of the page
    public static void scrollToTop() {
        try {
            js.executeScript("window.scrollTo(0, 0);");
        } catch (Exception e) {
            System.err.println("Unable to scroll to top: " + e.getMessage());
        }
    }

    // Refresh the current page
    public static void refreshPage() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            System.err.println("Unable to refresh page: " + e.getMessage());
        }
    }

    // Set the value of an input element using JavaScript and dispatch input event
    public static void setInputValue(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript(
            "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
            element, value
        );
    }
    
    public static void selectByIndex(WebElement element, int index) {
        wait.until(ExpectedConditions.visibilityOf(element));
        new Select(element).selectByIndex(index);
    }

    public static void selectByVisibleText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        new Select(element).selectByVisibleText(text);
    }
    
    public static void uploadFile(String filePath) throws InterruptedException, AWTException {
    	 StringSelection filePathSelection = new StringSelection(filePath);
 	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathSelection, null);
 	    Thread.sleep(3000);

 	    Robot rb = new Robot();
 	    rb.keyPress(KeyEvent.VK_CONTROL);
 	    rb.keyPress(KeyEvent.VK_V);
 	    rb.keyRelease(KeyEvent.VK_V);
 	    rb.keyRelease(KeyEvent.VK_CONTROL);

 	    Thread.sleep(1000);
 	    rb.keyPress(KeyEvent.VK_ENTER);
 	    rb.keyRelease(KeyEvent.VK_ENTER);
	}
    public static void verifySuccessMsg(org.openqa.selenium.WebElement element, String msg) {
    	try {
      ActionClass.waitUptoVisible(element);
        assertTrue(element.isDisplayed(), msg + " message not displayed");
        Reporter.log(msg + ".", true);
    	}
    	catch (Exception e) {
			Reporter.log("Verification failed: " + msg, true);
			throw e; // Re-throw the exception to fail the test
		}
    }
}

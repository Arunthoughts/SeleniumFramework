package mylearnings.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mylearnings.abstractcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessageOnScreen;
	
	By Message = By.cssSelector(".hero-primary");
	
	
	public boolean isconfirmationMessageMatch(String confirmationMessage) {
		WaitUntillElementLocated(Message);
		return confirmationMessageOnScreen.getText().equalsIgnoreCase(confirmationMessage);
	}


}

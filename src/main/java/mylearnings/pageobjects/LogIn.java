package mylearnings.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mylearnings.abstractcomponents.AbstractComponent;

public class LogIn extends AbstractComponent{
	
	WebDriver driver;
	
	public LogIn(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="[class*=flyInOut]")
	WebElement errorMessage;
	
	public void navigateTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public productCatalogue loginWithCredentials(String UserNameToBeEntered, String passwordToBeEntered) {
		userName.sendKeys(UserNameToBeEntered);
		password.sendKeys(passwordToBeEntered);
		loginButton.click();
		return new productCatalogue(driver);
		
	}
	
	public String loginWithInvalidCredentials(String wrongUserNameToBeEntered,String wrongPasswordToBeEntered) {
		userName.sendKeys(wrongUserNameToBeEntered);
		password.sendKeys(wrongPasswordToBeEntered);
		loginButton.click();
		WaitUntillWebElementLocated(errorMessage);
		return errorMessage.getText();
	}

}

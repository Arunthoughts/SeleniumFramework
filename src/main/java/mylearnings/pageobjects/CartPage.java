package mylearnings.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mylearnings.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProductsList;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	public List<WebElement> cartProducts() {
		WaitUntillElementLocated(By.cssSelector(".cartSection h3"));
		return cartProductsList;
	}
	
	public boolean isMatch(String productName) {
		return cartProducts().stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
	}
	
	public CheckoutPage navigateToCheckoutPage() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}


}

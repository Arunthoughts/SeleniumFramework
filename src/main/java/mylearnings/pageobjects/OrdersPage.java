package mylearnings.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mylearnings.abstractcomponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{
	
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ordersProductsList;
	
	public List<WebElement> orderedProducts() {
		WaitUntillWebElementsLocated(ordersProductsList);
		return ordersProductsList;
	}
	
	public boolean isMatch(String productName) {
		return orderedProducts().stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
	}

}

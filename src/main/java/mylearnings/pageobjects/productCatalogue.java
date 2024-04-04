package mylearnings.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mylearnings.abstractcomponents.AbstractComponent;

public class productCatalogue extends AbstractComponent{
	
	WebDriver driver;

	public productCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy = By.cssSelector(".mb-3");
	
	By addProduct = By.cssSelector(".card-body button:last-of-type");
	
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList(){
		WaitUntillElementLocated(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		return getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	}
	
	public void addProductToCart(String productName) {
		if (getProductByName(productName)!=null) {
			System.out.println("item is present and adding to the cart");
			getProductByName(productName).findElement(addProduct).click();
			System.out.println("Item is successfully added to cart");
		}else {
			System.out.println("item is not present" );
		}
		
		WaitUntillElementLocated(toastMessage);
		WaitForElementToDisappear(spinner);
		
	}


}

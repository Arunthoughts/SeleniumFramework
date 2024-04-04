package mylearnings.tests;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mylearnings.pageobjects.*;
import mylearnings.testcomponents.BaseTest;

public class EndToEndTest extends BaseTest
{
	//String productName = "ZARA COAT 3";
	
	@Test(dataProvider="testData",groups= {"Purchase"})
	public void placeOrder(HashMap<String , String> input) throws IOException {
    	// TODO Auto-generated method stub
		
		
		/*
		 * Login using valid credentials username = Arunkumar@gmail.com password =
		 * Arun@1234
		 */

//		String userNameToBeEntered = "Arunkumar@gmail.com";
//		String passwordToBeEntered = "Arun@1234";
		String countryName = "india";
		String confirmationMessage = "THANKYOU FOR THE ORDER.";
		
		//Invoking the URL of page and logging in using the credentials given
		productCatalogue productCatalogue = logInObject.loginWithCredentials(input.get("userNameToBeEntered"), input.get("passwordToBeEntered"));

		// adding that product to the cart
		productCatalogue.addProductToCart(input.get("productName"));
		
		//Navigating to the cartPage
		CartPage cartPage = productCatalogue.navigateToCartPage();

		// Validating TC using Assertions
		Assert.assertTrue(cartPage.isMatch(input.get("productName")));

		// proceeding to checkout page
		CheckoutPage checkoutPage = cartPage.navigateToCheckoutPage();

		// selecting country in checkout page
		checkoutPage.selectCountry(countryName);

		// clicking on place order button
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();

		// validating confirmation message on confirmation page
		Assert.assertTrue(confirmationPage.isconfirmationMessageMatch(confirmationMessage));
		
	}
	
	@Test(dependsOnMethods= {"placeOrder"},dataProvider="testData")
	public void verifyOrderedProduct(HashMap<String , String> input) {
//		String userNameToBeEntered = "Arunkumar@gmail.com";
//		String passwordToBeEntered = "Arun@1234";
		productCatalogue productCatalogue = logInObject.loginWithCredentials(input.get("userNameToBeEntered"), input.get("passwordToBeEntered"));
		OrdersPage ordersPageObject = productCatalogue.navigateToOrdersPage();
		Assert.assertTrue(ordersPageObject.isMatch(input.get("productName")));
		
	}
		
	@DataProvider
	public Object[][] testData() throws IOException{
		
		List<HashMap<String, String>> data = getjsonData(System.getProperty("user.dir")+"\\src\\test\\java\\mylearnings\\testdata\\PurchaseOrderTestData.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
//	@DataProvider
//	public Object[][] testData(){
//		HashMap <String,String> dataSet1 = new HashMap<String, String>();
//		dataSet1.put("userNameToBeEntered", "Arunkumar@gmail.com");
//		dataSet1.put("passwordToBeEntered", "Arun@1234");
//		dataSet1.put("productName", "ZARA COAT 3");
//		
//		HashMap <String,String> dataSet2 = new HashMap<String, String>();
//		dataSet2.put("userNameToBeEntered", "anshika@gmail.com");
//		dataSet2.put("passwordToBeEntered", "Iamking@000");
//		dataSet2.put("productName", "IPHONE 13 PRO");
//		
//		return new Object[][] {{dataSet1},{dataSet2}};
//	}
	
//	@DataProvider
//	public Object[][] testData(){
//		return new Object[][] {{"Arunkumar@gmail.com","Arun@1234","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","IPHONE 13 PRO"}};
//	}
 
}

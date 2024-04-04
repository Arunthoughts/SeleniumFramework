package mylearnings.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import mylearnings.pageobjects.CartPage;
import mylearnings.pageobjects.CheckoutPage;
import mylearnings.pageobjects.ConfirmationPage;
import mylearnings.pageobjects.productCatalogue;
import mylearnings.testcomponents.BaseTest;

public class ErrorValidationTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"})  //retryAnalyzer= Retry.class --> to use re execute if it is failed
	public void loginErrorValidationTest() {
		String wrongUserNameToBeEntered = "Arunkumar123@gmail.com";
		String wrongPasswordToBeEntered = "Arun123@1234";
		
		String errorMessage = logInObject.loginWithInvalidCredentials(wrongUserNameToBeEntered, wrongPasswordToBeEntered);
		Assert.assertEquals(errorMessage, "Incorrect password.");  //Expected value - "Incorrect email or password."
	}
	
	@Test
	public void ProductErrorValidation() throws IOException {
    	// TODO Auto-generated method stub
		
		
		/*
		 * Login using valid credentials username = Arunkumar@gmail.com password =
		 * Arun@1234
		 */
		String productName = "UNKNOWN PRODUCT";
		String userNameToBeEntered = "anshika@gmail.com";
		String passwordToBeEntered = "Iamking@000";
		
		//Invoking the URL of page and logging in using the credentials given
		productCatalogue productCatalogue = logInObject.loginWithCredentials(userNameToBeEntered, passwordToBeEntered);

		// adding that product to the cart
		productCatalogue.addProductToCart(productName);
	}
}

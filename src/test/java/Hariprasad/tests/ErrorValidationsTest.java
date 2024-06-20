package Hariprasad.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Hariprasad.TestComponents.BaseTest;
import Hariprasad.TestComponents.Retry;
import Hariprasad.pageobjects.CartPage;
import Hariprasad.pageobjects.CheckoutPage;
import Hariprasad.pageobjects.ConfirmationPage;
import Hariprasad.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)

	public void LoginErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		// LandingPage landingPage = launchApplication();
		landingPage.loginApplication("Prasadvpp1@gmail.com", "Hari@12");
		// landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test

	public void ProductErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		// LandingPage landingPage = launchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");

		// rahulshetty@gmail.com", "Hari@123")

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}

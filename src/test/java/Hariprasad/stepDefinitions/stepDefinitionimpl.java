package Hariprasad.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Hariprasad.TestComponents.BaseTest;
import Hariprasad.pageobjects.CartPage;
import Hariprasad.pageobjects.CheckoutPage;
import Hariprasad.pageobjects.ConfirmationPage;
import Hariprasad.pageobjects.LandingPage;
import Hariprasad.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionimpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page") // it's static expression
	public void I_landed_on_Ecommerce_Page() throws IOException {

		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$") // here "^" and "$" are used because of it's regular
																// expression for parameterised

	public void logged_in_username_and_password(String username, String password) {

		productCatalogue = landingPage.loginApplication(username, password); // cucumber takes the data from feature
																				// file in example section
	}

	// When I add product <productName> to the cart

	@When("^I add product (.+) to cart$")

	public void i_add_product_to_cart(String productName) {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on confirmationPage") // "THANKYOU FOR THE ORDER." is String type so put
																// {string}
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();

	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) throws Throwable {

		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}

}

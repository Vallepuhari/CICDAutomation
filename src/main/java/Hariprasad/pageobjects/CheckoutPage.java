package Hariprasad.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hariprasad.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement submit;

	By results = By.cssSelector(".ta-results");

	@FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")
	WebElement selectCountry;

	public void selectCountry(String countryName) {

		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);
		return new ConfirmationPage(driver);

	}

}

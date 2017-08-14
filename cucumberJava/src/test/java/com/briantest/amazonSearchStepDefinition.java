package com.briantest;

import java.util.concurrent.TimeUnit;

import cucumber.api.PendingException;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.openqa.selenium.By.cssSelector;

public class amazonSearchStepDefinition {


	protected WebDriver driver;


	 @Before
	    public void setup() {
	        driver = new ChromeDriver();
	}

	@Given("^I open amazon.com$")
	public void I_open_amazon_com() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.amazon.com");
	}

	@Given("^I search for \"([^\"]*)\"$")
	public void I_search_for(String searchTerm) {
		WebElement googleTextBox = driver.findElement(By.id("twotabsearchtextbox"));
		googleTextBox.sendKeys(searchTerm);
		WebElement searchButton = driver.findElement(cssSelector("#nav-search > form > div.nav-right > div > input"));
		searchButton.click();
	}

	@Given("^I receive results with prices$")
	public void I_receive_results_with_prices() {
		Boolean isPresent = driver.findElements(cssSelector("#s-results-list-atf")).size() > 0;
		Assert.assertTrue(isPresent);
	}

	@When("^I sort by price ascending$")
	public void I_sort_by_price_ascending() {
		WebElement sort = driver.findElement(By.id("sort"));
			sort.click();
		WebElement sortPriceLowToHigh = driver.findElement(By.cssSelector("option[value='price-asc-rank']"));
			sortPriceLowToHigh.click();

		String firstResultPriceDollars = driver.findElement(cssSelector("#result_0 > div > div.a-fixed-left-grid > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) > div.a-column.a-span7 > div.a-row.a-spacing-none > a > span > span > span")).getText();
		String firstResultPriceCents = driver.findElement(cssSelector("#result_0 > div > div.a-fixed-left-grid > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) > div.a-column.a-span7 > div.a-row.a-spacing-none > a > span > span > sup.sx-price-fractional")).getText();

		String secondResultPriceDollars = driver.findElement(cssSelector("#result_1 > div > div.a-fixed-left-grid > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) > div.a-column.a-span7 > div.a-row.a-spacing-none > a > span > span > span")).getText();
		String secondResultPriceCents = driver.findElement(cssSelector("#result_1 > div > div.a-fixed-left-grid > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) > div.a-column.a-span7 > div.a-row.a-spacing-none > a > span > span > sup.sx-price-fractional")).getText();

		String thirdItemPrice = driver.findElement(cssSelector("#result_2 > div > div > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) > div.a-column.a-span7 > div > div > a > span.a-size-base.a-color-base")).getText();

		String fourthItemPrice = driver.findElement(cssSelector("#result_3 > div > div > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) > div.a-column.a-span7 > div > div > a > span.a-size-base.a-color-base")).getText();

		String fifthResultPriceDollars = driver.findElement(cssSelector("#result_4 > div > div.a-fixed-left-grid > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) > div.a-column.a-span7 > div.a-row.a-spacing-none > a > span > span > span")).getText();
		String fifthResultPriceCents = driver.findElement(cssSelector("#result_4 > div > div.a-fixed-left-grid > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) > div.a-column.a-span7 > div.a-row.a-spacing-none > a > span > span > sup.sx-price-fractional")).getText();


		String firstItemPrice = "$" + firstResultPriceDollars + "." + firstResultPriceCents;
		String secondItemPrice = "$" + secondResultPriceDollars + "." + secondResultPriceCents;
		String fifthItemPrice = "$" + fifthResultPriceDollars + "." + fifthResultPriceCents;


		System.out.println(firstItemPrice);

		System.out.println(secondItemPrice);

		System.out.println(thirdItemPrice);

		System.out.println(fourthItemPrice);

		System.out.println(fifthItemPrice);

		Assert.assertTrue(firstItemPrice == "$0.01");
		Assert.assertTrue(secondItemPrice == "$0.01");
		Assert.assertTrue(thirdItemPrice == "$0.01");
		Assert.assertTrue(fourthItemPrice == "$0.01");
		Assert.assertTrue(fifthItemPrice == "$0.01");

	}

	@Then("^I can add the cheapest item to my basket$")
	public void I_can_add_the_cheapest_item_to_my_basket() {
		WebElement cheapestItem = driver.findElement(By.cssSelector("#result_0 > div > div > div > div.a-fixed-left-grid-col.a-col-right > div.a-row.a-spacing-small > div:nth-child(1) > a > h2"));
			cheapestItem.click();

		WebElement addToCart = driver.findElement(By.cssSelector("#add-to-cart-button"));
			addToCart.click();

		String confirmation = driver.findElement(By.cssSelector("#huc-v2-order-row-confirm-text > h1")).getText();

		Assert.assertTrue(confirmation == "Added to Cart");

	}

	@After
	    public void closeBrowser() {
	        driver.quit();
	 }

}



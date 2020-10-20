package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.google.common.io.Files;

import context.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import objectManagers.PageObjectManager;
import objectManagers.WebDriverManager;

import org.junit.Assert;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.PlaceOrderPage;
import pageObjects.ProductDetailsPage;
import utils.WebElementUtils;

public class BlazeDefinition {
	WebDriver driver;
	HomePage homePage;
	CartPage cartPage;
	PlaceOrderPage placeOrderPage;
	ProductDetailsPage productDetailsPage;
	WebElementUtils utils;
	PageObjectManager pageObjectManager;
	WebDriverManager webDriverManager;
	private static String productAmount;

	TestContext testContext;

	public BlazeDefinition(TestContext context) {
		testContext = context;
	}

	@Before("@UI")
	public void setUp() {
		webDriverManager = new WebDriverManager();
		driver = webDriverManager.getDriver();
	}

	@Given("Navigate through the product categories")
	public void navigate_through_the_product_categories() throws InterruptedException {
		pageObjectManager = new PageObjectManager(driver);
		homePage = pageObjectManager.getHomePage();
		homePage.click_Category("Phones");
		homePage.click_Category("Laptops");
		homePage.click_Category("Monitors");
		homePage.click_Category("Laptops");
	}

	@Then("I Add given {string} in the cart")
	public void i_add_given_in_the_cart(String string, DataTable dataTable) throws Exception {
		cartPage = pageObjectManager.getCartPage();
		productDetailsPage = pageObjectManager.getProductListingPage();
		List<List<String>> data = dataTable.asLists(String.class);

		for (List<String> columns : data) {
			homePage.click_Product(columns.get(0));
			productAmount = productDetailsPage.getProductPrice();
			cartPage.click_AddCartBtn();
			homePage.click_ProductStore();
			homePage.click_Category("Laptops");
		}
	}

	@Then("Navigate to cart and Delete a product")
	public void navigate_to_cart_and_delete_a_product(io.cucumber.datatable.DataTable dataTable) throws Exception {
		List<List<String>> data = dataTable.asLists(String.class);
		cartPage.click_Cart("Cart");
		for (List<String> columns : data) {
			System.out.println(columns.get(0));
			cartPage.deleteFromCart(columns.get(0));
		}
	}

	@Then("click on place order")
	public void click_on_place_order() throws InterruptedException {
		cartPage.clickPlaceOrder();
	}

	@Then("Fill the details and click on purchase")
	public void fill_the_details_and_click_on_purchase() {
		placeOrderPage = pageObjectManager.getPlaceOrderPage();
		placeOrderPage.enterDetailsAndPurchase();
	}

	@Then("Verify the purchase amount")
	public void verify_the_purchase_amount() {
		utils = new WebElementUtils(driver);
		String orderDetails = placeOrderPage.getDetails();
		String orderId = utils.getValue(orderDetails, "Id");
		System.out.println(orderId);
		String orderAmountTemp = utils.getValue(orderDetails, "Amount").trim();
		String orderAmount = orderAmountTemp.split(" ")[0];
		Assert.assertEquals("Amount didn't matched", productAmount, orderAmount);
		placeOrderPage.clickOk();
	}

	@After("@UI")
	public void tearDown() {
		driver.quit();
	}

	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				File sourcePath = ((TakesScreenshot) webDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
				File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/"
						+ screenshotName + ".png");
				Files.copy(sourcePath, destinationPath);
				//Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (IOException e) {
			}
		}
	}

}

package objectManagers;

import org.openqa.selenium.WebDriver;

import pageObjects.CartPage;

import pageObjects.PlaceOrderPage;

import pageObjects.HomePage;

import pageObjects.ProductDetailsPage;;

public class PageObjectManager {

	private WebDriver driver;

	private ProductDetailsPage productDetailsPage;

	private CartPage cartPage;

	private HomePage homePage;

	private PlaceOrderPage placeOrderPage;

	public PageObjectManager(WebDriver driver) {

		this.driver = driver;

	}

	public HomePage getHomePage() {

		return (homePage == null) ? homePage = new HomePage(driver) : homePage;

	}

	public ProductDetailsPage getProductListingPage() {

		return (productDetailsPage == null) ? productDetailsPage = new ProductDetailsPage(driver) : productDetailsPage;

	}

	public CartPage getCartPage() {

		return (cartPage == null) ? cartPage = new CartPage(driver) : cartPage;

	}

	public PlaceOrderPage getPlaceOrderPage() {

		return (placeOrderPage == null) ? placeOrderPage = new PlaceOrderPage(driver) : placeOrderPage;

	}
}

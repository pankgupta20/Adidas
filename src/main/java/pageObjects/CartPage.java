package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utils.WebElementUtils;

public class CartPage {
	WebElementUtils utils;
	private WebDriver driver;

	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.CSS, using = ".btn.btn-success.btn-lg")
	private WebElement addcart_Btn;

	@FindAll(@FindBy(how = How.CSS, using = ".navbar-nav.ml-auto>li>a"))
	private List<WebElement> homePage_List;

	@FindBy(how = How.XPATH, using = "//*[@id='tbodyid']/tr")
	private List<WebElement> productListCartRows;

	@FindBy(how = How.CSS, using = "[data-target='#orderModal']")
	private WebElement placeOrder;

	public void click_AddCartBtn() throws Exception {
		utils = new WebElementUtils(driver);
		utils.clinkLink(addcart_Btn);
	}

	public void reloadElements() {
		PageFactory.initElements(driver, this);
	}
	
	public void deleteFromCart(String eleName) throws Exception {
		for (WebElement row : productListCartRows) {
			List<WebElement> ele = row.findElements(By.tagName("td"));
			if (utils.CheckElementinList(ele, eleName)) {
				WebElement delElement = row.findElement(By.tagName("a"));
				if (delElement.isEnabled()) {
					delElement.click();
					utils.WaitforPageLoad();
					break;
				}
			}
		}
	}

	public void clickPlaceOrder() throws InterruptedException {
		reloadElements();
		placeOrder.click();
		utils.WaitforPageLoad();
	}

	public void click_Cart(String productName) throws InterruptedException {
		utils.clickLinkFromList(homePage_List, productName);
	}
}

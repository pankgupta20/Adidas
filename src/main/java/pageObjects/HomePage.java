package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utils.WebElementUtils;

public class HomePage {
	private WebDriver driver;
	WebElementUtils utils;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindAll(@FindBy(how = How.CSS, using = "#itemc"))
	private List<WebElement> category_List;

	@FindAll(@FindBy(how = How.CSS, using = ".hrefch"))
	private List<WebElement> product_List;
	
	@FindBy(how = How.CSS, using = "#nava")
	private WebElement productStore_Link;

	public void click_Category(String eleName) throws InterruptedException {
		utils = new WebElementUtils(driver);
		utils.clickLinkFromList(category_List, eleName);
	}

	public void click_Product(String productName) throws InterruptedException {
		utils.clickLinkFromList(product_List, productName);
	}
	
	public void click_ProductStore() throws Exception {
		utils.clinkLink(productStore_Link);
	}
}

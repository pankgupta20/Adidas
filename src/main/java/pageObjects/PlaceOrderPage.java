package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utils.WebElementUtils;

public class PlaceOrderPage {
	private WebDriver driver;

	public PlaceOrderPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.CSS, using = "#name")
	private WebElement name;

	@FindBy(how = How.CSS, using = "#country")
	private WebElement country;
	
	@FindBy(how = How.CSS, using = "#city")
	private WebElement city;

	@FindBy(how = How.CSS, using = "#card")
	private WebElement card;
	
	@FindBy(how = How.CSS, using = "#month")
	private WebElement month;

	@FindBy(how = How.CSS, using = "#year")
	private WebElement year;

	@FindBy(how = How.CSS, using = "[onclick='purchaseOrder()']")
	private WebElement purchase_btn;
	
	@FindBy(how = How.CSS, using = ".lead.text-muted")
	private WebElement orderDetails;
	
	@FindBy(how = How.CSS, using = ".confirm.btn.btn-lg.btn-primary")
	private WebElement ok_btn;
	
	public void enterDetailsAndPurchase() {
		name.sendKeys("name");
		country.sendKeys("country");
		city.sendKeys("city");
		card.sendKeys("card");
		month.sendKeys("10");
		year.sendKeys("2022");
		purchase_btn.click();
	}

	public String getDetails() {
		return orderDetails.getText();
	}
	
	public void clickOk() {
		ok_btn.click();
	}
}

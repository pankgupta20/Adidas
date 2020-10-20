package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
	private WebDriver driver;

	public ProductDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.CSS, using = "#tbodyid>h3")
	private WebElement product_Price;

	public String getProductPrice() {
		return product_Price.getText().substring(1).split(" ")[0];
	}
}

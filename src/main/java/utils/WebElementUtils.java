package utils;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementUtils {
	private WebDriver driver;

	public WebElementUtils(WebDriver driver) {
		this.driver = driver;
	}

	public void clickLinkFromList(List<WebElement> eleList, String eleName) throws InterruptedException {
		for (WebElement ele : eleList) {
			if (ele.getText().equalsIgnoreCase(eleName)) {
				ele.click();
				WaitforPageLoad();
				break;
			}
		}
	}

	public WebElement GetElementFromList(List<WebElement> eleList, String eleName) throws Exception {
		for (WebElement ele : eleList) {
			if (ele.getText().equalsIgnoreCase(eleName)) {
				return ele;
			}
		}
		throw new Exception("Element not found");
	}

	public boolean CheckElementinList(List<WebElement> eleList, String eleName) {
		for (WebElement ele : eleList) {
			if (ele.getText().equalsIgnoreCase(eleName)) {
				return true;
			}
		}
		return false;
	}

	public void clinkLink(WebElement ele) throws Exception {
		try {
			if (ele.isDisplayed()) {
				ele.click();
				Thread.sleep(2000);
				acceptAlertIfPresent();
			}
		} catch (Exception ex) {
			throw new Exception("element not clickable" + ex.getStackTrace());
		}
	}

	public void WaitforPageLoad() throws InterruptedException {
		int count = 0;
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		if ((Boolean) executor.executeScript("return window.jQuery != undefined")) {
			while (!(Boolean) executor.executeScript("return jQuery.active == 0")) {
				Thread.sleep(4000);
				if (count > 4)
					break;
				count++;
			}
		}
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public void acceptAlertIfPresent() {
		if (!isAlertPresent())
			return;
		driver.switchTo().alert().accept();
	}

	public void switchToWindow() {
		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		Iterator<String> I1 = s.iterator();

		while (I1.hasNext()) {
			String child_window = I1.next();

			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				System.out.println(driver.switchTo().window(child_window).getTitle());
			}
		}
	}

	public String getValue(String Details, String name) {
		String lines[] = Details.split("\\r?\\n");
		for (String data : lines) {
			if (data.startsWith(name))
				return data.split(":")[1];
		}
		return "";
	}

}
